package com.stacksimplify.restservices.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Configuration;

import com.stacksimplify.restservices.DTOs.UserMsDto;
import com.stacksimplify.restservices.Entities.User;

@Mapper(componentModel = "Spring")
public interface UserMapper {
	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

	UserMsDto userToUserMsDto(User user);

	List<UserMsDto> usersToUserMsDtos(List<User> users);
}
