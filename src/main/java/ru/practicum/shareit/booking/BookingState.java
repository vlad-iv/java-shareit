package ru.practicum.shareit.booking;

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
                return value;
            }

        }
        return null;
    }
}
