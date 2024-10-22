package ru.practicum.shareit.booking;

import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedAttributeNode;
import jakarta.persistence.NamedEntityGraph;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
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
		@NamedAttributeNode("item"), @NamedAttributeNode("booker") })
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
    private User booker;

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
