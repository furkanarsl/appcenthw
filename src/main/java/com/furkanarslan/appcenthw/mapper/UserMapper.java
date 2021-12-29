package com.furkanarslan.appcenthw.mapper;

import com.furkanarslan.appcenthw.dto.AppUserDto;
import com.furkanarslan.appcenthw.model.AppUser;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")

public interface UserMapper {
    AppUserDto AppUserToUserDto(AppUser user);
}
