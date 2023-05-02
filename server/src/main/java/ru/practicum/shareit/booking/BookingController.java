package ru.practicum.shareit.booking;

import java.util.Collections;
import java.util.List;

import javax.validation.constraints.Positive;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import ru.practicum.shareit.booking.dto.BookingDto;
import ru.practicum.shareit.common.MyPageRequest;

/**
 * TODO Sprint add-bookings.
 */
@RestController
@RequestMapping(path = "/bookings")
@Slf4j
@Validated
public class BookingController {

	@GetMapping
	public List<BookingDto> getBookings(@RequestHeader("X-Sharer-User-Id") long userId,
			@RequestParam(name = "state", defaultValue = "all") String stateParam,
			@RequestParam(name = "from", defaultValue = "0") Integer from,
			@Positive @RequestParam(name = "size", defaultValue = "10") Integer size) {
		log.info("getBookings");
			BookingState state = BookingState.from(stateParam);
		if (state == null) {
			throw new IllegalArgumentException("Unknown state: " + stateParam);
		}
		System.out.println("test");
		repository.findAll(PageRequest.of(from / size, size));
		repository.findAll(new MyPageRequest(from, size, Sort.unsorted()));
		return Collections.emptyList();
	}
}
