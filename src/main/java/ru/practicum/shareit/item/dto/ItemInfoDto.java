package ru.practicum.shareit.item.dto;

import java.util.List;

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
	private List<CommentDto> comments;
	private BookingDto lastBooking;
	private BookingDto nextBooking;

	public static class BookingDto {
		Long id;
//		LocalDateTime start;
//		LocalDateTime end;
		Long bookerId;
	}

	public static class CommentDto {
		Long id;
	}
}
