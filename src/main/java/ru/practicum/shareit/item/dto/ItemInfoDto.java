package ru.practicum.shareit.item.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import ru.practicum.shareit.Create;
import ru.practicum.shareit.booking.Booking;
import ru.practicum.shareit.booking.dto.BookingDto;

/**
 * // TODO .
 *
 * @author Vladimir Ivanov (ivanov.vladimir.l@gmail.com)
 */
public class ItemInfoDto {
	private Long id;
	private String name;
	private String description;
	private Boolean available;
	private BookingDto lastBooking;
	private BookingDto nextBooking;

	public static class BookingDto {
		Long id;
//		LocalDateTime start;
//		LocalDateTime end;
		Long bookerId;
	}
}
