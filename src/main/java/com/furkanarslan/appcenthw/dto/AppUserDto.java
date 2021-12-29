package com.furkanarslan.appcenthw.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppUserDto {
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    Long id;
    String username;
    String name;

}
