package ru.practicum.shareit.user;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;

import lombok.RequiredArgsConstructor;


@Transactional
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@DisplayName("User service")
@TestMethodOrder(OrderAnnotation.class)
class UserServiceImplITest {

	private final EntityManager em;
	private final UserServiceImpl service;

	@BeforeEach
	void beforeEach() {
	}

	@AfterEach
	void afterEach() {
	}

	@Test
	@Order(value = 1)
	@DisplayName("should create user from dto")
//	@Rollback(false)
	void should_create_user() {
		System.out.println("should create user from dto");
		UserDto userDto = new UserDto();
		userDto.setName("test");
		userDto.setEmail("test@email");

		userDto = service.createUser(userDto);

		TypedQuery<User> query = em.createQuery("Select i from User i where i.id = :id", User.class);
		User user = query.setParameter("id", userDto.getId()).getSingleResult();

		assertThat(user.getId(), equalTo(userDto.getId()));
		assertThat(user.getName(), equalTo(userDto.getName()));
		assertThat(user.getEmail(), equalTo(userDto.getEmail()));
	}

	@Test
	@Order(value = 2)
	@DisplayName("should return all users")
	void should_return_all_users() {
		System.out.println("should return all users");
		PageRequest pageRequest = PageRequest.ofSize(10);

		List<UserDto> userDtos = service.getAll(pageRequest);

		userDtos.forEach(u -> System.out.println(u.getId() + ": " + u.getName()));

//		TypedQuery<User> query = em.createQuery("Select i from User i", User.class);
//		List<User> users = query.getResultList();

		assertThat(userDtos.size(), equalTo(1));
		// TODO
	}

}