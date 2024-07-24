package ru.practicum.shareit.item;

import org.springframework.stereotype.Component;

import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.User;

/**
 * // TODO .
 *
 * @author Vladimir Ivanov (ivanov.vladimir.l@gmail.com)
 */
@Component
public class ItemMapper {
	public Item toModel(ItemDto itemDto, User owner) {
		return new Item(itemDto.getId(), itemDto.getName(), itemDto.getDescription(), itemDto.getAvailable(), owner);
	}

	public ItemDto toDto(Item item) {
		return new ItemDto(item.getId(), item.getName(), item.getDescription(), item.getAvailable(), null, null);
	}
}
