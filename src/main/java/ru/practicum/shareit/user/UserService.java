package ru.practicum.shareit.user;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * // TODO .
 *
 * @author Vladimir Ivanov (ivanov.vladimir.l@gmail.com)
 */
@Service
public class UserService {
	@Autowired
	UserRepository userRepository;
	public UserDto updateUser(UserDto dto) {
		final User user = UserMapper.toUser(dto);
		final User u = userRepository.findById(dto.getId())
				.orElseThrow(() -> new RuntimeException("User not found " + dto.getId()));
		if (user.getName() != null) {
			u.setName(user.getName());
		}
		//// TODO
		userRepository.save(u);
		return UserMapper.toUserDto(u);
	}
}
