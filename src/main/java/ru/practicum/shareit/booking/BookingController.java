package ru.practicum.shareit.booking;

import java.util.Collections;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import ru.practicum.shareit.booking.dto.BookingDto;

/**
 * TODO Sprint add-bookings.
 */
@RestController
@RequestMapping(path = "/bookings")
@Slf4j
public class BookingController {


	@GetMapping
	public List<BookingDto> getBookings(@RequestHeader("X-Sharer-User-Id") long userId,
			@RequestParam(name = "state", defaultValue = "ALL") String stateParam) {
		log.info("getBookings");
		BookingState state = BookingState.from(stateParam);
		if (state == null) {
			throw new IllegalArgumentException("Unknown state: " + stateParam);
		}
		return Collections.emptyList();
	}
}
