package ru.practicum.shareit.user;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.web.servlet.MockMvc;

/**
 * // TODO .
 *
 * @author Vladimir Ivanov (ivanov.vladimir.l@gmail.com)
 */
@WebMvcTest(UserController.class)
@AutoConfigureMockMvc
class UserControllerTest {

	@MockBean
	UserService userService;

	@Autowired
	MockMvc mockMvc;

	@Test
	void getAllUsers() throws Exception {
		when(userService.getAll(PageRequest.ofSize(10)))
				.thenReturn(Collections.emptyList());

		mockMvc.perform(get("/users"))
				.andExpect(status().isOk())
				.andExpect(content().json("[]"));

		verify(userService, times(1))
				.getAll(PageRequest.ofSize(10));
	}
}