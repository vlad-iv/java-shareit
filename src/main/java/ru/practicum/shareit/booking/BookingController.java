package ru.practicum.shareit.booking;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.booking.dto.BookingDto;

import java.util.Collections;
import java.util.List;

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
        BookingState state = BookingState.from(stateParam);
        if (state == null) {
            throw new IllegalArgumentException("Unknown state: " + stateParam);
        }
        return Collections.emptyList();
    }
}
