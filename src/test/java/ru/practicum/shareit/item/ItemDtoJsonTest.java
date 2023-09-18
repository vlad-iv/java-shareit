package ru.practicum.shareit.item;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import ru.practicum.shareit.item.dto.ItemDto;

/**
 * Test ItemDto.
 *
 * @author Vladimir Ivanov (ivanov.vladimir.l@gmail.com)
 */
@JsonTest
@DisplayName("Item dto json")
class ItemDtoJsonTest {
	@Autowired
	private JacksonTester<ItemDto> jacksonTester;
	private final ItemDto dto = new ItemDto(1L, "Saw", "Tool", Boolean.TRUE);
	private final String dtoJson = "{\n" +
			"    \"id\": 1,\n" +
			"    \"name\": \"Saw\",\n" +
			"    \"description\": \"Tool\",\n" +
			"    \"available\": true\n" +
			"}";

	@Test
	@DisplayName("should serialize")
	void testSerialize() throws Exception {

		var json = jacksonTester.write(dto);

		assertThat(json).hasJsonPath("$.id");
		assertThat(json).hasJsonPath("$.name");
		assertThat(json).hasJsonPath("$.description");
		assertThat(json).hasJsonPath("$.available");

		assertThat(json).extractingJsonPathNumberValue("$.id").isEqualTo(dto.getId().intValue());
		assertThat(json).extractingJsonPathStringValue("$.name").isEqualTo(dto.getName());
		assertThat(json).extractingJsonPathStringValue("$.description").isEqualTo(dto.getDescription());
		assertThat(json).extractingJsonPathBooleanValue("$.available").isEqualTo(Boolean.TRUE);
		// Or
		assertThat(json).isEqualToJson(dtoJson);
	}

	@Test
	@DisplayName("should deserialize")
	void testDeserialize() throws IOException {

		var item = new ItemDto(1L, "Saw", "Tool", Boolean.TRUE);

		var dto = jacksonTester.parseObject(dtoJson);

		assertThat(dto).extracting("id").isEqualTo(item.getId());
		assertThat(dto).extracting("name").isEqualTo(item.getName());
		assertThat(dto).extracting("description").isEqualTo(item.getDescription());
		assertThat(dto).extracting("available").isEqualTo(item.getAvailable());
	}
}