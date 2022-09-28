package ru.practicum.shareit.booking.dto;

import javax.validation.Constraint;

/**
 * // TODO .
 *
 * @author Vladimir Ivanov (ivanov.vladimir.l@gmail.com)
 */
@Constraint(validatedBy = StartBeforeEndValidator.class)
public @interface StartBeforeEnd {
}
