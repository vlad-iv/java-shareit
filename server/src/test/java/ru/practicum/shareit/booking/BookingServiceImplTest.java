package ru.practicum.shareit.booking;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ru.practicum.shareit.booking.dto.BookItemRequestDto;
import ru.practicum.shareit.booking.dto.BookingDto;
import ru.practicum.shareit.booking.dto.BookingState;
import ru.practicum.shareit.exception.NotFoundException;
import ru.practicum.shareit.item.ItemRepository;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.User;
import ru.practicum.shareit.user.UserRepository;

class BookingServiceImplTest {

	private BookingServiceImpl bookingService;

	private BookingRepository bookingRepository;
	private UserRepository userRepository;
	private ItemRepository itemRepository;

	private User user;

	@BeforeEach
	void setUp() {
		bookingRepository = mock(BookingRepository.class);
		when(bookingRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));
		userRepository = mock(UserRepository.class);
		itemRepository = mock(ItemRepository.class);
		bookingService = new BookingServiceImpl(bookingRepository, userRepository, itemRepository);
		user = new User();
		user.setId(1L);
		user.setName("John");
		user.setEmail("test@google.com");
	}

	@Test
	void getBookingsAll() {
		when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
		when(bookingRepository.findByBooker_Id(any(), any())).thenReturn(Page.empty());
        List<BookingDto> result = bookingService.getBookings(1L, BookingState.ALL, Pageable.unpaged());
		Assertions.assertNotNull(result);
		Assertions.assertTrue(result.isEmpty());
		verify(bookingRepository, times(1)).findByBooker_Id(any(), any());
	}

	@Test
	void getBookingsPast() {
		when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
		when(bookingRepository.findByBooker_IdAndEndIsBefore(any(), any(), any())).thenReturn(Page.empty());
        List<BookingDto> result = bookingService.getBookings(1L, BookingState.PAST, Pageable.unpaged());
		Assertions.assertNotNull(result);
		Assertions.assertTrue(result.isEmpty());
		verify(bookingRepository, times(1)).findByBooker_IdAndEndIsBefore(any(), any(), any());
	}

	@Test
	void getBookingsFuture() {
		when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
		when(bookingRepository.findByBooker_IdAndStartIsAfter(any(), any(), any())).thenReturn(Page.empty());
        List<BookingDto> result = bookingService.getBookings(1L, BookingState.FUTURE, Pageable.unpaged());
		Assertions.assertNotNull(result);
		Assertions.assertTrue(result.isEmpty());
		verify(bookingRepository, times(1)).findByBooker_IdAndStartIsAfter(any(), any(), any());
	}

	@Test
	void getBookingsCurrent() {
		when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
		when(bookingRepository.findCurrentForDate(any(), any(), any())).thenReturn(Page.empty());
        List<BookingDto> result = bookingService.getBookings(1L, BookingState.CURRENT, Pageable.unpaged());
		Assertions.assertNotNull(result);
		Assertions.assertTrue(result.isEmpty());
		verify(bookingRepository, times(1)).findCurrentForDate(any(), any(), any());
	}

	@Test
	void getBookingsWaiting() {
		when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
		when(bookingRepository.findPending(any(), any())).thenReturn(Page.empty());
        List<BookingDto> result = bookingService.getBookings(1L, BookingState.WAITING, Pageable.unpaged());
		Assertions.assertNotNull(result);
		Assertions.assertTrue(result.isEmpty());
		verify(bookingRepository, times(1)).findPending(any(), any());
	}

	@Test
	void getBookingsRejected() {
		when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
		when(bookingRepository.findCanceled(any(), any())).thenReturn(Page.empty());
        List<BookingDto> result = bookingService.getBookings(1L, BookingState.REJECTED, Pageable.unpaged());
		Assertions.assertNotNull(result);
		Assertions.assertTrue(result.isEmpty());
		verify(bookingRepository, times(1)).findCanceled(any(), any());
	}

	@Test
	void getOwnerBookingsAll() {
		when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
		when(bookingRepository.findByItem_Owner_Id(any(), any())).thenReturn(Page.empty());
        List<BookingDto> result = bookingService.getOwnerBookings(1L, BookingState.ALL, Pageable.unpaged());
		Assertions.assertNotNull(result);
		Assertions.assertTrue(result.isEmpty());
		verify(bookingRepository, times(1)).findByItem_Owner_Id(any(), any());
	}

	@Test
	void getOwnerBookingsPast() {
		when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
		when(bookingRepository.findByItem_Owner_IdAndEndIsBefore(any(), any(), any())).thenReturn(Page.empty());
        List<BookingDto> result = bookingService.getOwnerBookings(1L, BookingState.PAST, Pageable.unpaged());
		Assertions.assertNotNull(result);
		Assertions.assertTrue(result.isEmpty());
		verify(bookingRepository, times(1)).findByItem_Owner_IdAndEndIsBefore(any(), any(), any());
	}

	@Test
	void getOwnerBookingsFuture() {
		when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
		when(bookingRepository.findByItem_Owner_IdAndStartIsAfter(any(), any(), any())).thenReturn(Page.empty());
        List<BookingDto> result = bookingService.getOwnerBookings(1L, BookingState.FUTURE, Pageable.unpaged());
		Assertions.assertNotNull(result);
		Assertions.assertTrue(result.isEmpty());
		verify(bookingRepository, times(1)).findByItem_Owner_IdAndStartIsAfter(any(), any(), any());
	}

	@Test
	void getOwnerBookingsCurrent() {
		when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
		when(bookingRepository.findOwnerCurrentForDate(any(), any(), any())).thenReturn(Page.empty());
        List<BookingDto> result = bookingService.getOwnerBookings(1L, BookingState.CURRENT, Pageable.unpaged());
		Assertions.assertNotNull(result);
		Assertions.assertTrue(result.isEmpty());
		verify(bookingRepository, times(1)).findOwnerCurrentForDate(any(), any(), any());
	}

	@Test
	void getOwnerBookingsWaiting() {
		when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
		when(bookingRepository.findOwnerPending(any(), any())).thenReturn(Page.empty());
        List<BookingDto> result = bookingService.getOwnerBookings(1L, BookingState.WAITING, Pageable.unpaged());
		Assertions.assertNotNull(result);
		Assertions.assertTrue(result.isEmpty());
		verify(bookingRepository, times(1)).findOwnerPending(any(), any());
	}

	@Test
	void getOwnerBookingsRejected() {
		when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
		when(bookingRepository.findOwnerCanceled(any(), any())).thenReturn(Page.empty());
        List<BookingDto> result = bookingService.getOwnerBookings(1L, BookingState.REJECTED, Pageable.unpaged());
		Assertions.assertNotNull(result);
		Assertions.assertTrue(result.isEmpty());
		verify(bookingRepository, times(1)).findOwnerCanceled(any(), any());
	}

	@Test
	void bookItem() {
        long userId = 1L;
        User user = new User();
		user.setId(2L);
		when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        BookItemRequestDto requestDto = new BookItemRequestDto(1L, LocalDateTime.now(), LocalDateTime.now());
		Item item = new Item();
		item.setId(3L);
		item.setOwner(user);
		item.setAvailable(true);
		when(itemRepository.findById(requestDto.getItemId())).thenReturn(Optional.of(item));
        BookingDto result = bookingService.bookItem(userId, requestDto);
		Assertions.assertNotNull(result);
	}

	@Test
	void getBooking() {
        long bookingId = 2L;
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
		when(bookingRepository.findById(bookingId)).thenReturn(Optional.empty());
		Assertions.assertThrows(NotFoundException.class, () -> bookingService.getBooking(user.getId(), bookingId));
	}

	@Test
	void setBookingApproveStateApproved() {
        long bookingId = 2L;
        boolean approved = true;
		Item item = new Item();
		item.setId(3L);
		item.setOwner(user);
		item.setAvailable(true);
        Booking booking = new Booking();
		booking.setId(3L);
		booking.setStatus(BookingStatus.WAITING);
		booking.setStart(LocalDateTime.now().plusDays(1));
		booking.setBooker(user);
		booking.setItem(item);
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
		when(bookingRepository.findByIdAndItem_Owner_Id(bookingId, user.getId())).thenReturn(Optional.of(booking));
        BookingDto result = bookingService.setBookingApproveState(user.getId(), bookingId, approved);
		Assertions.assertNotNull(result);
		Assertions.assertEquals(BookingStatus.APPROVED, result.getStatus());
	}

	@Test
	void setBookingApproveStateRejected() {
        long bookingId = 2L;
        boolean approved = false;
		Item item = new Item();
		item.setId(3L);
		item.setOwner(user);
		item.setAvailable(true);
        Booking booking = new Booking();
		booking.setId(3L);
		booking.setStatus(BookingStatus.WAITING);
		booking.setStart(LocalDateTime.now().plusDays(1));
		booking.setBooker(user);
		booking.setItem(item);
		when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
		when(bookingRepository.findByIdAndItem_Owner_Id(bookingId, user.getId())).thenReturn(Optional.of(booking));
        BookingDto result = bookingService.setBookingApproveState(user.getId(), bookingId, approved);
		Assertions.assertNotNull(result);
		Assertions.assertEquals(BookingStatus.REJECTED, result.getStatus());
	}
}