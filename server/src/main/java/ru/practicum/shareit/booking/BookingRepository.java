package ru.practicum.shareit.booking;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * // TODO .
 *
 * @author Vladimir Ivanov (ivanov.vladimir.l@gmail.com)
 */
public interface BookingRepository extends JpaRepository<Booking, Long> {

	@Query("select b from Booking b" +
			" where b.status = 'WAITING' " +
			" and b.start > :start " +
			" and b.end > :end ")
	List<Booking> searchBookings(LocalDateTime start, LocalDateTime end);
}
