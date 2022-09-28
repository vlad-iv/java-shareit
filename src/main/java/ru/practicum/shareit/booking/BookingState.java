package ru.practicum.shareit.booking;

import java.util.Optional;

/**
 * // TODO .
 *
 * @author Vladimir Ivanov (ivanov.vladimir.l@gmail.com)
 */
public enum BookingState {
	// Все
	ALL,
	// Текущие
	CURRENT,
	// Будущие
	FUTURE,
	// Завершенные
	PAST,
	// Отклоненные
	REJECTED,
	// Ожидающие подтверждения
	WAITING;

	static BookingState from(String state) {
		for (BookingState value : BookingState.values()) {
			if (value.name().equals(state)) {
				return Optional.of(value);
			}

		}
		return null;
	}
}
