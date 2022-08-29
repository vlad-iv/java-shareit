package ru.practicum.shareit.item;

import java.util.List;

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
	public Item toModel(ItemDto itemDto, User owner) {
		return new Item(itemDto.getId(), itemDto.getName(), itemDto.getDescription(), itemDto.getAvailable(), owner);
	}

	public ItemDto toDto(Item item) {
		return new ItemDto(item.getOwner().getId(), item.getId(), item.getName(), item.getDescription(), item.getAvailable(), null, null);
	}

	public ItemInfoDto toItemInfoDto(Item item, Booking lastBooking, Booking nextBooking) {
		return new ItemInfoDto(item.getId(), item.getName(),
				item.getDescription(), item.getAvailable(),
				new ItemInfoDto.BookingDto(lastBooking.getId(),.. ),
		new ItemInfoDto.BookingDto(nextBooking.getId(),.. ))
	}

	public ItemInfoDto toItemInfoDto(Item item, List<Booking> bookings) {
		Booking lastBooking  = bookings.stream().filter().findFirst(); // start < now() || end < now()
		Booking nextBooking = bookings.stream().filter().findFirst(); // start > now()


		return new ItemInfoDto(item.getId(), item.getName(),
				item.getDescription(), item.getAvailable(),
				new ItemInfoDto.BookingDto(lastBooking.getId(),.. ),
		new ItemInfoDto.BookingDto(nextBooking.getId(),.. ))
	}
}
