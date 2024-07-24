package ru.practicum.shareit.item.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * TODO Sprint add-controllers.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ItemUpdateDto {
	private Long id;
	private String name;
	private String description;
	private Boolean available;
	public boolean isNameNotNull(final ItemUpdateDto itemDto) {
		return itemDto.getName() != null && !itemDto.getName().isBlank();
	}
}
