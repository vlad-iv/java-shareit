package ru.practicum.shareit.user;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ru.practicum.shareit.common.Create;
import ru.practicum.shareit.common.Update;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDto {
	private Long id;

	@NotBlank(groups = {Create.class})
	private String name;

	@Email(groups = {Update.class, Create.class})
	@NotNull(groups = {Create.class})
	private String email;
}
