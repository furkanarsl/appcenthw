package com.furkanarslan.appcenthw.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TodoListOutDetailDto {
    private Long id;
    private String name;
    private List<TaskDto> tasks;
}
