package ru.practicum.shareit.booking;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import ru.practicum.shareit.user.User;

/**
 * TODO Sprint add-bookings.
 */
@Entity
@Table(name = "bookings")
@Getter
@Setter
public class Booking {
	@Id
	private long id;

	private String status;
	private LocalDateTime start;
	private LocalDateTime end;

	private transient User booker;
}
