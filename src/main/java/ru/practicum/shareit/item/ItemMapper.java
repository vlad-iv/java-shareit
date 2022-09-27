package ru.practicum.shareit.item;

import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.User;

/**
 * // TODO .
 *
 * @author Vladimir Ivanov (ivanov.vladimir.l@gmail.com)
 */
public class ItemMapper {
	public static Item toItem(ItemDto itemDto, User owner) {
		return new Item(itemDto.getId(), ... , owner)
	}
}
