package ru.practicum.shareit.item.dto;


import jakarta.validation.constraints.Email;
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
	private long id;
	private String name;
	private String description;
	private boolean available;


	@Email(groups = {Update.class, Create.class})
	@NotNull(groups = {Create.class})
	private String email;

	private UserDto user;

	@Data
	static class UserDto {
		private String name;
	}
}
