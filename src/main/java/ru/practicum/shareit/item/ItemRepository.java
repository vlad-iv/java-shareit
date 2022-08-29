package ru.practicum.shareit.item;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ru.practicum.shareit.item.model.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {
    @EntityGraph("item-booking-graph")
    List<Item> findByOwner_Id(Long ownerId);

    Optional<Item> findByIdAndOwner_Id(Long id, Long ownerId);

    @Query("select item from Item item" +
            " where item.available = true " +
            " and item.name like ?1" +
            " and item.description like ?1")
    List<Item> search(String text);

    @Query("select i from Item i" +
            " join i.bookings b " +
            " join b.user u" +
            " where u.email = ?1")
    List<Item> findByBookings_User_Email(String email);

}