package ru.practicum.shareit.user;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ru.practicum.shareit.booking.Booking;
import ru.practicum.shareit.item.model.Item;

/**
 * // TODO .
 *
 * @author Vladimir Ivanov (ivanov.vladimir.l@gmail.com)
 */
public interface UserRepository extends JpaRepository<User, Long> {
	@Query("select item from Item item" +
			" where item.available = true " +
			" and item.name like ?1" +
			" and item.descrioption like ?1")
	List<Item> search(String text);

	@Query("select b from Booking b" +
			" where b.status = 'WAITING' " +
			" and b.start > ?" +
			" and b.end > ?")
	List<Booking> searchBookings(LocalDateTime start, LocalDateTime end);
}
