package ru.practicum.shareit.item.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ru.practicum.shareit.Create;
import ru.practicum.shareit.Update;

/**
 * TODO Sprint add-controllers.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ItemDto {
	long userId;
	@NotNull(groups = {Update.class})
	private Long id;
	@NotBlank(groups = {Create.class})
	private String name;
	@NotBlank(groups = {Create.class, Update.class})
	private String description;
	@NotNull(groups = {Update.class})
	private Boolean available;

	private UserDto user;

	@Data
	static class UserDto {
		private String name;
	}
}
