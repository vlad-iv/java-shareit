package ru.practicum.shareit.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import ru.practicum.shareit.Update;

/**
 * TODO Sprint add-controllers.
 */
@RestController
@RequestMapping(path = "/users")
@Slf4j
public class UserController {
	@Autowired
	UserService userService;

	@PatchMapping("{userId}")
	public UserDto update(@PathVariable long userId, @RequestBody @Validated(Update.class) UserDto userDto) {
		userDto.setId(userId);
		log.info("Updating user {}", userDto);
		return userService.updateUser(userDto);
	}
}
