package ru.practicum.shareit.booking.dto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class StartBeforeEndValidator implements ConstraintValidator<StartBeforeEnd, BookingDto> {
    @Override
    public boolean isValid(BookingDto value, ConstraintValidatorContext context) {
        return false;
    }
}
