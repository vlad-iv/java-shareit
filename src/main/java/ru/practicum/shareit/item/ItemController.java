package ru.practicum.shareit.item;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.practicum.shareit.Create;
import ru.practicum.shareit.Update;
import ru.practicum.shareit.item.dto.ItemDto;

/**
 * TODO Sprint add-controllers.
 */
@RestController
@RequestMapping("/items")
public class ItemController {
	@PostMapping
	ItemDto createItem(@Validated({Create.class}) @RequestBody ItemDto itemDto) {
		return itemDto;
	}

	@PatchMapping
	ItemDto updateItem(@Validated({Update.class}) @RequestBody ItemDto itemDto) {
		return itemDto;
	}
}
