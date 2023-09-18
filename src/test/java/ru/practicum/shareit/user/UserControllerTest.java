package ru.practicum.shareit.user;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.practicum.shareit.common.MyPageRequest;

/**
 * Test UserController.
 *
 * @author Vladimir Ivanov (ivanov.vladimir.l@gmail.com)
 */
@DisplayName("User controller")
@WebMvcTest(UserController.class)
@AutoConfigureMockMvc
class UserControllerTest {

	@MockBean
	UserService userService;

	@Autowired
	MockMvc mockMvc;

	@Autowired
	private ObjectMapper mapper;

	@Test
	@DisplayName("should return all users")
	void should_return_all_users() throws Exception {
		int from = 0;
		int size = 5;
		PageRequest pageRequest = new MyPageRequest(from, size, null);
		List<UserDto> expectedResult = Collections.emptyList();
		when(userService.getAll(pageRequest))
				.thenReturn(expectedResult);


		mockMvc.perform(get("/users")
						.header("X-Sharer-User-Id", userId)
						.param("from", String.valueOf(from))
						.param("size", String.valueOf(size)))
				.andExpect(status().isOk())
				.andExpect(content().json(mapper.writeValueAsString(expectedResult)));

		verify(userService, times(1))
				.getAll(pageRequest);
	}

	public static final long userId = 1L;
}