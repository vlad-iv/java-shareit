package ru.practicum.shareit.item;

import org.springframework.stereotype.Component;

import ru.practicum.shareit.booking.Booking;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.dto.ItemInfoDto;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.User;

/**
 * // TODO .
 *
 * @author Vladimir Ivanov (ivanov.vladimir.l@gmail.com)
 */
@Component
public class ItemMapper {
	public ItemInfoDto toItemInfoDto(Item item, Booking lastBooking, Booking nextBooking) {
		return new ItemInfoDto(item.getId(), item.getName(),
				item.getDescription(), item.getAvailable(),
				new ItemInfoDto.BookingDto(lastBooking.getId(), lastBooking.getBooker().getId()),
				new ItemInfoDto.BookingDto(nextBooking.getId(), nextBooking.getBooker().getId()));
	}

	public Item toModel(ItemDto itemDto, User owner) {
		return new Item(itemDto.getId(), itemDto.getName(), itemDto.getDescription(), itemDto.getAvailable(), owner);
	}

	public ItemDto toDto(Item item) {
		return new ItemDto(item.getId(), item.getName(), item.getDescription(), item.getAvailable(), null, null);
	}
}
