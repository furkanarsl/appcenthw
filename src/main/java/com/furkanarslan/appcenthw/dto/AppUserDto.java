package com.furkanarslan.appcenthw.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppUserDto {
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    Long id;
    String username;
    String name;

}
