package ru.practicum.shareit.item.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * // TODO .
 *
 * @author Vladimir Ivanov (ivanov.vladimir.l@gmail.com)
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ItemInfoDto {
	private Long id;
	private String name;
	private String description;
	private Boolean available;
	private List<CommentDto> comments;
//	private String ownerName;
	private BookingDto lastBooking;
	private BookingDto nextBooking;

	public record BookingDto(Long id, Long bookerId) {
	}

	public record UserDto(Long id, String name) {
	}
}
