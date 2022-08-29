package ru.practicum.shareit.booking;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

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
@NamedEntityGraph(name = "Booking.userAndItem", attributeNodes = {
		@NamedAttributeNode("item"), @NamedAttributeNode("user") })
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "booker_id")
    private User user;

	@NotNull
	@Column(name = "start_date_time", nullable = false)
	private Instant startDateTime;

	@NotNull
	@Column(name = "end_date_time", nullable = false)
	private Instant endDateTime;

	@NotNull
	@Column(name = "approved", nullable = false)
	private Boolean approved = false;

	@NotNull
	@Column(name = "canceled", nullable = false)
	private Boolean canceled = false;

}
