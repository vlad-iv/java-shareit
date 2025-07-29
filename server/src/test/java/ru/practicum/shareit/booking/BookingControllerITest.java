package ru.practicum.shareit.booking;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import ru.practicum.shareit.booking.dto.BookingDto;
import ru.practicum.shareit.booking.dto.BookingState;
import ru.practicum.shareit.common.FromSizeRequest;

@WebMvcTest(BookingController.class)
class BookingControllerITest {
	@MockBean
	private BookingService bookingService;

	@Autowired
	private MockMvc mockMvc;

	@Test
	void testGetBookings() throws Exception {
		// given
		String state = "past";
		long userId = 1L;
		int from = 0;
		int size = 20;
		FromSizeRequest pageable = FromSizeRequest.of(from, size, Sort.by(Sort.Direction.DESC, "start"));
		when(bookingService.getBookings(userId, BookingState.PAST, pageable)).thenReturn(Collections.emptyList());

		// when + then
		mockMvc.perform(get("/bookings")
						.header("X-Sharer-User-Id", userId)
						.param("from", String.valueOf(from))
						.param("size", String.valueOf(size))
						.param("state", state))
				.andExpect(status().isOk())
				.andExpect(content().json("[]"));
		verify(bookingService, times(1)).getBookings(userId, BookingState.PAST, pageable);
	}

	@Test
	void shouldReturnFailOnUnknownStatus() throws Exception {
		// given
		String state = "new";
		long userId = 1L;
		int from = 0;
		int size = 20;
		FromSizeRequest pageable = FromSizeRequest.of(from, size, Sort.by(Sort.Direction.DESC, "start"));
//        when(bookingService.getBookings(userId, BookingState.PAST, pageable)).thenReturn(Collections.emptyList());

		// when + then
		mockMvc.perform(get("/bookings")
						.header("X-Sharer-User-Id", userId)
						.param("from", String.valueOf(from))
						.param("size", String.valueOf(size))
						.param("state", state))
				.andExpect(status().is5xxServerError())
				.andExpect(content().json("{\"error\":\"Unknown state: " + state + "\"}"));
		verify(bookingService, times(0)).getBookings(anyLong(), any(), any());
	}

	@Test
	void testGetOwnerBookings() throws Exception {
		// given
		String state = "past";
		long userId = 1L;
		int from = 0;
		int size = 20;
		FromSizeRequest pageable = FromSizeRequest.of(from, size, Sort.by(Sort.Direction.DESC, "start"));
		when(bookingService.getBookings(userId, BookingState.PAST, pageable)).thenReturn(Collections.emptyList());

		// when + then
		mockMvc.perform(get("/bookings/owner")
						.header("X-Sharer-User-Id", userId)
						.param("from", String.valueOf(from))
						.param("size", String.valueOf(size))
						.param("state", state))
				.andExpect(status().isOk())
				.andExpect(content().json("[]"));
		verify(bookingService, times(1)).getOwnerBookings(userId, BookingState.PAST, pageable);
	}

	@Test
	void testBookItem() throws Exception {
		// given
		long userId = 1L;
		long itemId = 2L;
		BookingDto bookingDto = new BookingDto(3L, null, null, null, null, BookingStatus.APPROVED);
		when(bookingService.bookItem(anyLong(), any())).thenReturn(bookingDto);

		// when + then
		mockMvc.perform(post("/bookings")
						.header("X-Sharer-User-Id", userId)
						.content(String.format("{\"itemId\": %s,\"start\": \"%s\", \"end\":\"%s\"}", itemId, LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(2)))
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(bookingDto.getId()));
	}

	@Test
	void testGetBooking() throws Exception {
		// given
		long userId = 1L;
		BookingDto bookingDto = new BookingDto(3L, null, null, null, null, BookingStatus.APPROVED);
		when(bookingService.getBooking(userId, bookingDto.getId())).thenReturn(bookingDto);

		// when + then
		mockMvc.perform(get("/bookings/" + bookingDto.getId())
						.header("X-Sharer-User-Id", userId))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(3))
				.andExpect(jsonPath("$.status").value("APPROVED"));
		verify(bookingService, times(1)).getBooking(userId, bookingDto.getId());
	}

	@Test
	void testApproveBooking() throws Exception {
		// given
		long userId = 1L;
		long bookingId = 2L;
		Boolean approved = Boolean.TRUE;
		BookingDto bookingDto = new BookingDto(bookingId, null, null, null, null, BookingStatus.APPROVED);
		when(bookingService.setBookingApproveState(userId, bookingId, approved)).thenReturn(bookingDto);

		// when + then
		mockMvc.perform(patch("/bookings/" + bookingDto.getId())
						.header("X-Sharer-User-Id", userId)
						.param("approved", approved.toString())
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(bookingDto.getId()));
	}

}