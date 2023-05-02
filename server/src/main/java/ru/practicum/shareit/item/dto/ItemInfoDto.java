package ru.practicum.shareit.item.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * // TODO .
 *
 * @author Vladimir Ivanov (ivanov.vladimir.l@gmail.com)
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ItemInfoDto {
	private Long id;
	private String name;
	private String description;
	private Boolean available;
	private BookingDto lastBooking;
	private BookingDto nextBooking;

	@Getter
	@AllArgsConstructor
	@NoArgsConstructor
	public static class BookingDto {
		Long id;
		LocalDateTime start;
		LocalDateTime end;
		Long bookerId;
	}
}
