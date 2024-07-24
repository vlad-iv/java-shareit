package ru.practicum.shareit.item.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.practicum.shareit.user.User;

/**
 * TODO Sprint add-controllers.
 */
// ITEMS
@Data
@AllArgsConstructor
public class Item {
	private Long id;
	private String name;
	private String description;
	private Boolean available;

	// ITEMS.OWNER_ID -> USERS
	private User owner;
}
