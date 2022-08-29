package ru.practicum.shareit.user;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * // TODO .
 *
 * @author Vladimir Ivanov (ivanov.vladimir.l@gmail.com)
 */
@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {
	final UserRepository userRepository;

	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	@Transactional
	public UserDto createUser(UserDto dto) {
		final User user = UserMapper.toUser(dto);
		final User save = userRepository.save(user);// ОБЯЗАТЕЛЬНО
//		dto.setId(save.getId());
//		return dto;
		return UserMapper.toUserDto(save);
	}
	@Override
	@Transactional
	public UserDto updateUser(UserDto dto) {
		final User u = userRepository.findById(dto.getId())
				.orElseThrow(() -> new RuntimeException("User not found " + dto.getId()));
		final User user = UserMapper.toUser(dto);
		if (user.getName() != null && !user.getName().isBlank()) {
			u.setName(user.getName());
		}
		//// TODO
		userRepository.save(u);
		return UserMapper.toUserDto(u);
	}
}
