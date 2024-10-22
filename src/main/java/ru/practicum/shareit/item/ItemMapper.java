package ru.practicum.shareit.item;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Component;

import ru.practicum.shareit.booking.Booking;
import ru.practicum.shareit.item.dto.ItemCreateDto;
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
				Collections.emptyList(),
				new ItemInfoDto.BookingDto(lastBooking.getId(), lastBooking.getBooker().getId()),
				new ItemInfoDto.BookingDto(nextBooking.getId(), nextBooking.getBooker().getId()));
	}

	public ItemInfoDto toItemInfoDto(Item item, List<Booking> bookings) {
		Booking lastBooking = getLastBooking(bookings);
		Booking nextBooking = getNextBooking(bookings);
		return new ItemInfoDto(item.getId(), item.getName(),
				item.getDescription(), item.getAvailable(),
				Collections.emptyList(),
				new ItemInfoDto.BookingDto(lastBooking.getId(), lastBooking.getBooker().getId()),
				new ItemInfoDto.BookingDto(nextBooking.getId(), nextBooking.getBooker().getId()));
	}

	private Booking getNextBooking(List<Booking> bookings) {
		return null;
	}

	private Booking getLastBooking(List<Booking> bookings) {
		return null;
	}

	public Item toModel(ItemCreateDto itemDto, User owner) {
		return new Item(itemDto.getId(), itemDto.getName(), itemDto.getDescription(), itemDto.getAvailable(), owner);
	}

	public Item toModel(ItemDto itemDto, User owner) {
		return new Item(itemDto.getId(), itemDto.getName(), itemDto.getDescription(), itemDto.getAvailable(), owner);
	}

	public ItemDto toDto(Item item) {
		return new ItemDto(0, item.getId(), item.getName(), item.getDescription(), item.getAvailable(), null);
	}
}
