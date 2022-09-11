package ru.practicum.shareit.user;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.practicum.shareit.common.Create;
import ru.practicum.shareit.common.Update;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {
	private final UserService userService;

	@GetMapping
	public List<UserDto> getAllUsers() {
		log.info("Get all users");
		return userService.getAll();
	}

	@PostMapping
	public UserDto createUser(@RequestBody @Validated(Create.class) UserDto userDto) {
		log.info("Creating user {}", userDto);
		return userService.createUser(userDto);
	}

	@PatchMapping("{userId}")
	public UserDto update(@PathVariable long userId, @RequestBody @Validated(Update.class) UserDto userDto) {
		userDto.setId(userId);
		log.info("Updating user {}", userDto);
		return userService.updateUser(userDto);
	}

	@GetMapping("{userId}")
	public UserDto get(@PathVariable long userId) {
		log.info("Get user id={}", userId);
		return userService.getUser(userId);
	}

	@DeleteMapping("{userId}")
	public void delete(@PathVariable long userId) {
		log.info("Delete user id={}", userId);
		userService.deleteUser(userId);
	}
}
