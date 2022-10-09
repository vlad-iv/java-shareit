package ru.practicum.shareit.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.practicum.shareit.common.Create;
import ru.practicum.shareit.common.Update;
import ru.practicum.shareit.item.dto.ItemDto;

/**
 * TODO Sprint add-controllers.
 */
@RestController
@RequestMapping("/items")
public class ItemController {
	@Autowired
	ItemServiceImpl itemService;
	@PostMapping
	ItemDto createItem(@RequestHeader("X-Sharer-User-Id") long userId,
			@Validated({Create.class}) @RequestBody ItemDto itemDto) {
		return itemService.createItem(itemDto);
	}

	@PatchMapping("/{itemId}")
	ItemDto updateItem(@PathVariable Long itemId,
			@Validated({Update.class}) @RequestBody ItemDto itemDto) {
		return itemDto;
	}
}
