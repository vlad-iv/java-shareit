package ru.practicum.shareit.user;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

public interface UserService {
	List<UserDto> getAll(PageRequest pageRequest);

	@Transactional
	UserDto createUser(UserDto userDto);

	UserDto updateUser(UserDto userDto);

	UserDto getUser(long userId);

	void deleteUser(long userId);
}
