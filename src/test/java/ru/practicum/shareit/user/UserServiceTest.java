package ru.practicum.shareit.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

/**
 * Test user service.
 *
 * @author Vladimir Ivanov (ivanov.vladimir.l@gmail.com)
 */
@DisplayName("User service")
class UserServiceTest {

	UserServiceImpl userService;
	UserRepository userRepository;

	@BeforeEach
	void beforeEach() {
		userRepository = mock(UserRepository.class);
		userService = new UserServiceImpl(userRepository);
	}

	@Test
	@DisplayName("should find all users")
	void should_find_all_users() {
		User user = new User(1L, "user 1", "user1@email");
		final PageImpl<User> userPage = new PageImpl<>(Collections.singletonList(user));
		when(userRepository.findAll(PageRequest.ofSize(10)))
				.thenReturn(userPage);

		final List<UserDto> userDtos = userService.getAll(PageRequest.ofSize(10));

		assertEquals(1, userDtos.size());
		// TODO
	}

}