package com.furkanarslan.appcenthw.dto;

import com.furkanarslan.appcenthw.model.AppUser;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TodoListInDto {
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;
    private String name;
}
