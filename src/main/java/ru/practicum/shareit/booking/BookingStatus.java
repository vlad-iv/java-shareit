package ru.practicum.shareit.booking;

import java.util.Optional;

public enum BookingStatus {
	/**
	 * Новое бронирование, ожидает одобрения
	 */
	WAITING,
	/**
	 * Бронирование подтверждено владельцем
	 */
	APPROVED,
	/**
	 * Бронирование отклонено владельцем
	 */
	REJECTED,
	/**
	 * Бронирование отменено создателем
	 */
	CANCELED;

	public static Optional<BookingStatus> from(String stringState) {
		for (BookingStatus state : values()) {
			if (state.name().equalsIgnoreCase(stringState)) {
				return Optional.of(state);
			}
		}
		return Optional.empty();
	}
}
