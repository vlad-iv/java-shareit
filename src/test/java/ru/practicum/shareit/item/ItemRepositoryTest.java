package ru.practicum.shareit.item;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.User;
import ru.practicum.shareit.user.UserRepository;

/**
 * Test ItemRepository.
 *
 * @author Vladimir Ivanov (ivanov.vladimir.l@gmail.com)
 */
@DataJpaTest
@DisplayName("Item repository")
class ItemRepositoryTest {
	@Autowired
	UserRepository userRepository;
	@Autowired
	ItemRepository itemRepository;

	User user1;
	Item item1;
	User user2;
	Item item2;

	@BeforeEach
	void beforeEach() {
		user1 = userRepository.save(new User(1L, "user 1", "user1@email"));
		item1 = itemRepository.save(new Item(1L, "item 1", "item 1 description", true, user1));

		user2 = userRepository.save(new User(2L, "user 2", "user2@email"));
		item2 = itemRepository.save(new Item(2L, "item 2", "item 2 description", true, user2));
	}

	@DisplayName("should find item by owner")
	@Test
	void should_find_item_by_owner() {
		final Page<Item> byOwner = itemRepository.findByOwner_Id(user1.getId(), Pageable.unpaged());

		assertEquals(1, byOwner.getTotalElements());
		// TODO
		}

	@DisplayName("should find all items")
	@Test
	void should_find_all_items() {
		List<Item> items = itemRepository.findAll();

		assertEquals(2, items.size());

		List<User> users = userRepository.findAll();

		assertEquals(2, users.size());
		// TODO
	}

}