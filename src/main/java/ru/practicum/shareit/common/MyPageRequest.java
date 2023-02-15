package ru.practicum.shareit.common;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

/**
 * // TODO .
 *
 * @author Vladimir Ivanov (ivanov.vladimir.l@gmail.com)
 */
public class MyPageRequest extends PageRequest {
	int from;

	/**
	 * Creates a new {@link PageRequest} with sort parameters applied.
	 *
	 * @param from zero-based index, must not be negative.
	 * @param size the size of the page to be returned, must be greater than 0.
	 * @param sort must not be {@literal null}, use {@link Sort#unsorted()} instead.
	 */
	public MyPageRequest(int from, int size, Sort sort) {
		super(from / size, size, sort);
		this.from = from;
	}

	@Override
	public long getOffset() {
		return from;
	}
}
