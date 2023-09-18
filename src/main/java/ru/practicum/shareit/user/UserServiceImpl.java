package ru.practicum.shareit.user;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import ru.practicum.shareit.exception.NotFoundException;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	UserRepository userRepository;

	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public List<UserDto> getAll(PageRequest pageRequest) {
		return userRepository.findAll(pageRequest)
				.stream()
				.map(UserMapper::toUserDto)
				.collect(Collectors.toList());
	}

	@Override
	@Transactional
	public UserDto createUser(UserDto userDto) {
		final User user = UserMapper.toUser(userDto);
		final User save = userRepository.save(user);
		return UserMapper.toUserDto(save);
	}

	@Override
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

	@Override
	public UserDto getUser(long userId) {
		final User user = userRepository.findById(userId)
				.orElseThrow(() -> new NotFoundException("User with id=" + userId + " not found"));
		return UserMapper.toUserDto(user);
	}

	@Override
	public void deleteUser(long userId) {
		userRepository.findById(userId)
				.ifPresent(user -> userRepository.delete(user));
	}
}
