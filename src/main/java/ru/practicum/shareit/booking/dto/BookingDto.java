package ru.practicum.shareit.booking.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import lombok.Getter;

/**
 * TODO Sprint add-bookings.
 */
@StartBeforeEnd
@Getter
public class BookingDto implements StartEnd {
	@FutureOrPresent
	private LocalDateTime start;
	@Future
	private LocalDateTime end;

	@AssertTrue
	boolean isStartBeforeEnd() {
		return start.isBefore(end);
	}
}
