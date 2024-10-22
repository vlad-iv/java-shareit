package ru.practicum.shareit.booking.dto;

import jakarta.validation.Constraint;

/**
 * // TODO .
 *
 * @author Vladimir Ivanov (ivanov.vladimir.l@gmail.com)
 */
@Constraint(validatedBy = StartBeforeEndValidator.class)
public @interface StartBeforeEnd {
}
