package ru.practicum.shareit.user;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import ru.practicum.shareit.exception.NotFoundException;

@Service
@RequiredArgsConstructor
public class UserService {

	@Autowired
	UserRepository userRepository;

	public List<UserDto> getAll() {
		return userRepository.findAll()
				.stream()
				.map(UserMapper::toUserDto)
				.collect(Collectors.toList());
	}

	public UserDto createUser(UserDto userDto) {
		final User user = UserMapper.toUser(userDto);
		final User save = userRepository.save(user);
		return UserMapper.toUserDto(save);
	}

	public UserDto updateUser(UserDto userDto) {
		final User newUser = UserMapper.toUser(userDto);

		final User user = userRepository.findById(newUser.getId())
				.orElseThrow(() -> new NotFoundException("User with id=" + newUser.getId() + " not found"));

		if (newUser.getName() == null) {
			newUser.setName(user.getName());
		}
		if (newUser.getEmail() == null) {
			newUser.setEmail(user.getEmail());
		}
		userRepository.save(newUser);
		return UserMapper.toUserDto(newUser);
	}

	public UserDto getUser(long userId) {
		final User user = userRepository.findById(userId)
				.orElseThrow(() -> new NotFoundException("User with id=" + userId + " not found"));
		return UserMapper.toUserDto(user);
	}

	public void deleteUser(long userId) {
		userRepository.findById(userId)
				.ifPresent(user -> userRepository.delete(user));
	}
}
