package com.furkanarslan.appcenthw.controller;

import com.furkanarslan.appcenthw.dto.TaskDto;
import com.furkanarslan.appcenthw.dto.TaskInDto;
import com.furkanarslan.appcenthw.mapper.TaskMapper;
import com.furkanarslan.appcenthw.mapper.TodoListMapper;
import com.furkanarslan.appcenthw.model.AppUser;
import com.furkanarslan.appcenthw.model.Task;
import com.furkanarslan.appcenthw.service.TaskService;
import com.furkanarslan.appcenthw.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "Tasks")
@RequestMapping("/task")
public class TaskController {
    private final TaskService taskService;
    private final UserService userService;
    private final TaskMapper taskMapper;
    private final TodoListMapper todoListMapper;

    @Operation(summary = "Get all tasks", description = "Get all tasks for the currently logged in user.")
    @ApiResponse(responseCode = "200", description = "All tasks for the user.")
    @GetMapping("")
    public ResponseEntity<List<TaskDto>> getTasks(Authentication authentication) {
        AppUser owner = userService.getUser(authentication.getName());
        List<TaskDto> tasks = taskMapper.TasksToTaskDtos(taskService.getTasksForUser(owner));
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @Operation(summary = "Get a task detail.", description = "Get a task detail for the currently logged in user.")
    @ApiResponse(responseCode = "200", description = "Task with the given id if the user owns the content.")
    @ApiResponse(responseCode = "403", description = "If user doesn't own the content.", content = @Content)
    @ApiResponse(responseCode = "404", description = "Task not found.", content = @Content)
    @GetMapping("/{id}")
    public ResponseEntity<TaskDto> getTask(@PathVariable Long id, Authentication authentication) throws AccessDeniedException, ResponseStatusException {
        AppUser owner = userService.getUser(authentication.getName());
        Task task = taskService.getTaskForUser(id, owner);
        TaskDto taskDto = taskMapper.TaskToTaskDto(task);
        return new ResponseEntity<>(taskDto, HttpStatus.OK);
    }

    @Operation(summary = "Create task.", description = "Create a new task and save it for the currently logged in user.")
    @ApiResponse(responseCode = "200", description = "Returns the newly created task object.")
    @PostMapping("")
    public ResponseEntity<TaskDto> createTask(@Valid @RequestBody TaskInDto task, Authentication authentication) {
        AppUser owner = userService.getUser(authentication.getName());
        Task newTask = taskMapper.TaskInDtoToTask(task);
        newTask.setOwner(owner);
        taskService.saveTask(newTask);
        return new ResponseEntity<>(taskMapper.TaskToTaskDto(newTask), HttpStatus.CREATED);
    }

    @Operation(summary = "Update task.", description = "Update details of a task and save it for the currently logged in user.")
    @ApiResponse(responseCode = "200", description = "Returns the newly updated task object.")
    @PutMapping("/{id}")
    public ResponseEntity<TaskDto> updateTask(@PathVariable Long id, Authentication authentication, @RequestBody TaskDto task) {
        AppUser owner = userService.getUser(authentication.getName());
        task.setId(id);
        Task currentTask = taskService.getTaskForUser(id, owner);
        taskMapper.update(currentTask, task);
        return new ResponseEntity<>(taskMapper.TaskToTaskDto(currentTask), HttpStatus.OK);
    }

    @Operation(summary = "Delete task.", description = "Delete a task for the currently logged in user.")
    @ApiResponse(responseCode = "204", description = "Returns nothing with a status code of 204.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeTask(@PathVariable Long id, Authentication authentication) {
        AppUser owner = userService.getUser(authentication.getName());
        Task task = taskService.getTaskForUser(id, owner);
        taskService.removeTask(id);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
}
