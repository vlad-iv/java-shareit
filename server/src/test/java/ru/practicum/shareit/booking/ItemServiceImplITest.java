package ru.practicum.shareit.booking;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import ru.practicum.shareit.exception.NotFoundException;
import ru.practicum.shareit.item.ItemService;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.User;

@Transactional
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@SpringBootTest
class ItemServiceImplITest {

	private final EntityManager em;
	private final ItemService service;
	private User user;

	@BeforeEach
	void beforeEach() {
		user = new User();
		user.setName("UserName");
		user.setEmail("user@mail.ru");
		em.persist(user);
	}

	@AfterEach
	void afterEach() {
		em.createNativeQuery("truncate table items");
	}

	@Test
	void saveItem() {
		ItemDto itemDto = new ItemDto();
		itemDto.setAvailable(true);
		itemDto.setName("test");
		itemDto.setDescription("test description");
		itemDto = service.createItem(user.getId(), itemDto);

		TypedQuery<Item> query = em.createQuery("Select i from Item i where i.id = :id", Item.class);
		Item item = query.setParameter("id", itemDto.getId()).getSingleResult();

		assertThat(item.getId(), equalTo(itemDto.getId()));
		assertThat(item.getName(), equalTo(itemDto.getName()));
		assertThat(item.getDescription(), equalTo(itemDto.getDescription()));
		assertThat(item.isAvailable(), equalTo(itemDto.getAvailable()));
		assertThat(item.getOwner(), equalTo(user));
	}

}