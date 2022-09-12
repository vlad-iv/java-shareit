package ru.practicum.shareit.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

/**
 * // TODO .
 *
 * @author Vladimir Ivanov (ivanov.vladimir.l@gmail.com)
 */
class UserServiceTest {

	UserService userService;
	UserRepository userRepository;

	@BeforeEach
	void beforeEach() {
		userRepository = mock(UserRepository.class);
		userService = new UserService();
		userService.userRepository = userRepository;

	}

	@Test
	void getAll() {
		User user = new User(1L, "user 1", "user1@email");
		final PageImpl<User> userPage = new PageImpl<>(Collections.singletonList(user));
		when(userRepository.findAll(PageRequest.ofSize(10)))
				.thenReturn(userPage);

		final List<UserDto> userDtos = userService.getAll(PageRequest.ofSize(10));

		assertNotNull(userDtos);
		assertEquals(1, userDtos.size());
		// TODO
	}

}