package ru.practicum.shareit.booking.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;

/**
 * TODO Sprint add-bookings.
 */
@StartBeforeEnd
public class BookingDto {
	@FutureOrPresent
	private LocalDateTime start;
	@Future
	private LocalDateTime end;

}
