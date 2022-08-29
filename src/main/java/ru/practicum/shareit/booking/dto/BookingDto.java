package ru.practicum.shareit.booking.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;

import ru.practicum.shareit.item.dto.ItemDto;

/**
 * TODO Sprint add-bookings.
 */
@StartBeforeEnd
public class BookingDto {
	@FutureOrPresent
	private LocalDateTime start;
	@Future
	private LocalDateTime end;
	private BookerDto booker;
	private ItemDto item;
	@AssertTrue
	boolean isValidStartAfterEnd() {
		return start.isAfter(end);
	}
}
