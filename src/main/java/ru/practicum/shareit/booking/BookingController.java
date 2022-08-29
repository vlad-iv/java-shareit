package ru.practicum.shareit.booking;

import java.util.Collections;
import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import ru.practicum.shareit.Create;
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
                                        @RequestParam(name = "state", defaultValue = "all") String stateParam) {
        log.info("getBookings");
        // НЕДОПУСТИМО
        try {
            BookingState bookingState = BookingState.valueOf(stateParam);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Unknown state: " + stateParam);
        }

        // null
        // Optional
        // ПРАВИЛЬНО
        BookingState state = BookingState.from(stateParam);
        if (state == null) {
            throw new IllegalArgumentException("Unknown state: " + stateParam);
        }

        // Optional<BookingState> state = BookingState.from(stateParam);
//        BookingState state = BookingState.from(stateParam)
//                .orElseThrow(() -> new IllegalArgumentException("Unknown state: " + stateParam));
        return Collections.emptyList();
    }

    @PostMapping
    BookingDto createBooking(@Validated({Create.class}) @RequestBody BookingDto bookingDto) {
        return bookingDto;
    }

}
