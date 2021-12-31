package com.furkanarslan.appcenthw.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskInDto {
    private String content;
    @Schema(defaultValue = "false")
    private Boolean isCompleted;

    @NotNull
    private Date dueDate;

    @NotNull(message = "Task requires a todo list id")
    private TodoListTaskInDto list;
}
