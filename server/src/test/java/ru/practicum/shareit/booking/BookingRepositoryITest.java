package ru.practicum.shareit.booking;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import lombok.RequiredArgsConstructor;
import ru.practicum.shareit.item.ItemRepository;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.User;
import ru.practicum.shareit.user.UserRepository;

@DataJpaTest
@RequiredArgsConstructor(onConstructor_ = @Autowired)
class BookingRepositoryITest {
    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final ItemRepository itemRepository;

    private Item item;
    private User booker;

    @BeforeEach
    void setUp() {
        this.item = createItem("Pencil", "Basic drawing tool", false, createUser("Mike Turtle", "mikelangelo@tmnt.com"));
        this.booker = createUser("Mr. Smith", "find.neo@matrix.com");
    }

    private Item createItem(String name, String description, boolean available, User owner) {
        Item item = new Item();
        item.setAvailable(available);
        item.setName(name);
        item.setDescription(description);
        item.setOwner(owner);
        return itemRepository.save(item);
    }

    private User createUser(String name, String email) {
        User owner = new User();
        owner.setName(name);
        owner.setEmail(email);
        return userRepository.save(owner);
    }

    private Booking createBooking(BookingStatus status, Item item, User booker, LocalDateTime start, LocalDateTime end) {
        Booking booking = new Booking();
        booking.setStatus(status);
        booking.setItem(item);
        booking.setBooker(booker);
        booking.setStart(start);
        booking.setEnd(end);
        return bookingRepository.save(booking);
    }

    @Test
    void testFindApprovedItems() {
        Booking booking = createBooking(BookingStatus.APPROVED, item, booker, LocalDateTime.now(), LocalDateTime.now());
        List<Booking> result = bookingRepository.findApprovedForItems(Collections.singleton(item), Sort.unsorted());
        Assertions.assertNotNull(result);
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals(booking.getId(), result.get(0).getId());
        Assertions.assertEquals(booking.getStatus(), result.get(0).getStatus());
    }

    @Test
    void testFindPendingItems() {
        Booking booking = createBooking(BookingStatus.WAITING, item, booker, LocalDateTime.now(), LocalDateTime.now());
        Page<Booking> result = bookingRepository.findPending(booker.getId(), Pageable.unpaged());
        Assertions.assertNotNull(result);
        Assertions.assertEquals(1, result.getTotalElements());
        Assertions.assertEquals(booking.getId(), result.getContent().get(0).getId());
        Assertions.assertEquals(booking.getStatus(), result.getContent().get(0).getStatus());
    }

    @Test
    void testFindOwnerPendingItems() {
        Booking booking = createBooking(BookingStatus.WAITING, item, booker, LocalDateTime.now(), LocalDateTime.now());
        Page<Booking> result = bookingRepository.findOwnerPending(item.getOwner().getId(), Pageable.unpaged());
        Assertions.assertNotNull(result);
        Assertions.assertEquals(1, result.getTotalElements());
        Assertions.assertEquals(booking.getId(), result.getContent().get(0).getId());
        Assertions.assertEquals(booking.getStatus(), result.getContent().get(0).getStatus());
    }

    @Test
    void testFindCurrentForDate() {
        LocalDateTime start = LocalDateTime.now().plusDays(-1);
        LocalDateTime end = LocalDateTime.now().plusDays(1);
        Booking booking = createBooking(BookingStatus.WAITING, item, booker, start, end);
        Page<Booking> result = bookingRepository.findCurrentForDate(booker.getId(), LocalDateTime.now(), Pageable.unpaged());
        Assertions.assertNotNull(result);
        Assertions.assertEquals(1, result.getTotalElements());
        Assertions.assertEquals(booking.getId(), result.getContent().get(0).getId());
        Assertions.assertEquals(booking.getStatus(), result.getContent().get(0).getStatus());
    }

    @Test
    void testFindOwnerForDate() {
        LocalDateTime start = LocalDateTime.now().plusDays(-1);
        LocalDateTime end = LocalDateTime.now().plusDays(1);
        Booking booking = createBooking(BookingStatus.WAITING, item, booker, start, end);
        Page<Booking> result = bookingRepository.findOwnerCurrentForDate(item.getOwner().getId(), LocalDateTime.now(), Pageable.unpaged());
        Assertions.assertNotNull(result);
        Assertions.assertEquals(1, result.getTotalElements());
        Assertions.assertEquals(booking.getId(), result.getContent().get(0).getId());
        Assertions.assertEquals(booking.getStatus(), result.getContent().get(0).getStatus());
    }

    @Test
    void testFindCanceledItems() {
        Booking booking = createBooking(BookingStatus.CANCELED, item, booker, LocalDateTime.now(), LocalDateTime.now());
        Page<Booking> result = bookingRepository.findCanceled(booker.getId(), Pageable.unpaged());
        Assertions.assertNotNull(result);
        Assertions.assertEquals(1, result.getTotalElements());
        Assertions.assertEquals(booking.getId(), result.getContent().get(0).getId());
        Assertions.assertEquals(booking.getStatus(), result.getContent().get(0).getStatus());
    }

    @Test
    void testFindRejectedItems() {
        Booking booking = createBooking(BookingStatus.REJECTED, item, booker, LocalDateTime.now(), LocalDateTime.now());
        Page<Booking> result = bookingRepository.findCanceled(booker.getId(), Pageable.unpaged());
        Assertions.assertNotNull(result);
        Assertions.assertEquals(1, result.getTotalElements());
        Assertions.assertEquals(booking.getId(), result.getContent().get(0).getId());
        Assertions.assertEquals(booking.getStatus(), result.getContent().get(0).getStatus());
    }

    @Test
    void testFindOwnerCanceledItems() {
        Booking booking = createBooking(BookingStatus.CANCELED, item, booker, LocalDateTime.now(), LocalDateTime.now());
        Page<Booking> result = bookingRepository.findOwnerCanceled(item.getOwner().getId(), Pageable.unpaged());
        Assertions.assertNotNull(result);
        Assertions.assertEquals(1, result.getTotalElements());
        Assertions.assertEquals(booking.getId(), result.getContent().get(0).getId());
        Assertions.assertEquals(booking.getStatus(), result.getContent().get(0).getStatus());
    }

    @Test
    void testFindOwnerRejectedItems() {
        Booking booking = createBooking(BookingStatus.REJECTED, item, booker, LocalDateTime.now(), LocalDateTime.now());
        Page<Booking> result = bookingRepository.findOwnerCanceled(item.getOwner().getId(), Pageable.unpaged());
        Assertions.assertNotNull(result);
        Assertions.assertEquals(1, result.getTotalElements());
        Assertions.assertEquals(booking.getId(), result.getContent().get(0).getId());
        Assertions.assertEquals(booking.getStatus(), result.getContent().get(0).getStatus());
    }

    @Test
    void testIsAvailableForBooking() {
        LocalDateTime start = LocalDateTime.now().plusDays(-2);
        LocalDateTime end = LocalDateTime.now().plusDays(2);
        Booking booking = createBooking(BookingStatus.APPROVED, item, booker, start, end);
        Assertions.assertTrue(bookingRepository.isAvailableForBooking(item.getId(), LocalDateTime.now().plusDays(-1), LocalDateTime.now().plusDays(1)));
        Assertions.assertFalse(bookingRepository.isAvailableForBooking(item.getId(), LocalDateTime.now().plusDays(-3), LocalDateTime.now().plusDays(3)));
    }

    @AfterEach
    void tearDown() {
        bookingRepository.deleteAll();
        itemRepository.deleteAll();
        userRepository.deleteAll();
    }
}