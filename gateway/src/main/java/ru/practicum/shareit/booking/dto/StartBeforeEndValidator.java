package ru.practicum.shareit.booking.dto;

import java.lang.annotation.Annotation;
import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * // TODO .
 *
 * @author Vladimir Ivanov (ivanov.vladimir.l@gmail.com)
 */
@Component
public class StartBeforeEndValidator implements ConstraintValidator<StartBeforeEndValidator, StartEnd>, Annotation {
	@Override
	public void initialize(final StartBeforeEndValidator constraintAnnotation) {
	}

	@Override
	public boolean isValid(final StartEnd value, final ConstraintValidatorContext context) {
		final LocalDateTime start = value.getStart();
		return false; // TODO
	}

	@Override
	public Class<? extends Annotation> annotationType() {
		return StartBeforeEnd.class;
	}
}
