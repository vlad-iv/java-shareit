package ru.practicum.shareit.item;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.User;
import ru.practicum.shareit.user.UserRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * // TODO .
 *
 * @author Vladimir Ivanov (ivanov.vladimir.l@gmail.com)
 */
@DataJpaTest
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

	@Test
	void findByOwner() {
		final Page<Item> byOwner = itemRepository.findByOwner_Id(user1.getId(), Pageable.unpaged());

		assertNotNull(byOwner);
		assertEquals(1, byOwner.getTotalElements());
		// ...
	}

	@AfterEach
	void afterEach() {
		itemRepository.deleteAll();
		userRepository.deleteAll();
	}
}