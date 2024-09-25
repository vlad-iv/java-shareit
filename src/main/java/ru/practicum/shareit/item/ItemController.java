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

import lombok.extern.slf4j.Slf4j;
import ru.practicum.shareit.Create;
import ru.practicum.shareit.Update;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.dto.ItemUpdateDto;

/**
 * TODO Sprint add-controllers.
 */
@Slf4j
@RestController
@RequestMapping("/items")
public class ItemController {
	public static final String SHARER_USER_ID = "X-Sharer-User-Id";
	@Autowired
	ItemServiceImpl itemService;
	@PostMapping
	ItemDto createItem(@RequestHeader(SHARER_USER_ID) long userId,
			@Validated({Create.class, Update.class}) @RequestBody ItemDto itemDto) {
		itemDto.setUserId(userId);
		log.info("==> Creating item: {}", itemDto);
		ItemDto item = itemService.createItem(itemDto);
		log.info("<== Creating item: {}", item);
		return item;
	}

	@PatchMapping("/{itemId}")
	ItemDto updateItem(@PathVariable Long itemId,
			 @RequestBody ItemUpdateDto itemDto) {
//		Item item = itemService.getById(itemId);
//		if (itemDto.isNameNotNull(itemDto)) {
//			item.setName(itemDto.getName());
//		}
//		return itemDto;
		return new ItemDto();
	}
}
