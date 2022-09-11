package ru.practicum.shareit.item;

import ru.practicum.shareit.booking.Booking;
import ru.practicum.shareit.item.dto.ItemInfoDto;
import ru.practicum.shareit.item.model.Item;

/**
 * // TODO .
 *
 * @author Vladimir Ivanov (ivanov.vladimir.l@gmail.com)
 */
public class ItemMapper {
	public static ItemInfoDto toItemInfoDto(Item item, Booking lastBooking, Booking nextBooking) {
		return new ItemInfoDto(item.getId(), item.getName(),
				item.getDescrioption(), item.getAvailable(),
				lastBooking != null ? new ItemInfoDto.BookingDto(lastBooking.getId(),
						lastBooking.getStart(),
						lastBooking.getEnd(),
						lastBooking.getBooker().getId()) : null,
				nextBooking != null ? new ItemInfoDto.BookingDto(nextBooking.getId(),
						nextBooking.getStart(),
						nextBooking.getEnd(),
						nextBooking.getBooker().getId()) : null);
	}
}
