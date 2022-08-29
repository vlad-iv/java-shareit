package ru.practicum.shareit.booking;

import lombok.Getter;
import lombok.Setter;
import ru.practicum.shareit.user.User;

/**
 * TODO Sprint add-bookings.
 */
@Getter
@Setter
public class Booking {
	private long id;
	private User booker;
}
