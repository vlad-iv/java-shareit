package ru.practicum.shareit.item;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import ru.practicum.shareit.item.model.Item;

/**
 * // TODO .
 *
 * @author Vladimir Ivanov (ivanov.vladimir.l@gmail.com)
 */
public interface ItemRepository extends JpaRepository<Item, Long> {
	Page<Item> findByOwner_Id(Long ownerId, Pageable pageable);
//	@Query("select item from Item item" +
//			" where item.available = true " +
//			" and item.name like ?1" +
//			" and item.descrioption like ?1")
//	List<Item> search(String text);
//
//	@Query("select b from Booking b" +
//			" where b.status = 'WAITING' " +
//			" and b.start > ?" +
//			" and b.end > ?")
//	List<Booking> searchBookings(LocalDateTime start, LocalDateTime end);
}
