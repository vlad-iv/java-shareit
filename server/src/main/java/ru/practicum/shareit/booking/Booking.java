package ru.practicum.shareit.booking;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import ru.practicum.shareit.item.model.Item;
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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "booking_id")
	private Long id;

	@Column(name = "start_date_time", nullable = false)
	private LocalDateTime start;

	@Column(name = "end_date_time", nullable = false)
	private LocalDateTime end;

	@ManyToOne
	@JoinColumn(name = "item_id")
	private Item item;

	@ManyToOne
	@JoinColumn(name = "booker_id")
	private User booker;

	@Enumerated(EnumType.STRING)
	@Column(name = "status", nullable = false)
	private BookingStatus status;

}
