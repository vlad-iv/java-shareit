package ru.practicum.shareit.booking;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BookingRepository extends JpaRepository<Booking, Long> {
	@Override
	@EntityGraph(value = "Booking.userAndItem")
//	@EntityGraph(attributePaths = {"items", "users"})
	Optional<Booking> findById(Long id);
//    @Query("select b from Booking b" +
//            " where b.status = 'WAITING' " +
//            " and b.start > ?" +
//            " and b.end > ?")
//    List<Booking> searchBookings(LocalDateTime start, LocalDateTime end);

	@EntityGraph(value = "Booking.userAndItem")
	List<Booking> findBookingByItems(List items);

	@Query("select b from Booking b" +
			" where b.status = 'WAITING' " +
			" and b.start > :start " +
			" and b.end > :end ")
	List<Booking> searchBookings(LocalDateTime start, LocalDateTime end);
}