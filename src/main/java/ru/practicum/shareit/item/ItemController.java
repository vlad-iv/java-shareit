package ru.practicum.shareit.item;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.practicum.shareit.Create;
import ru.practicum.shareit.Update;
import ru.practicum.shareit.item.dto.ItemCreateDto;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.dto.ItemInfoDto;
import ru.practicum.shareit.item.dto.ItemUpdateDto;
import ru.practicum.shareit.item.model.Item;

/**
 * TODO Sprint add-controllers.
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/items")
@Validated
public class ItemController {
	public static final String SHARER_USER_ID = "X-Sharer-User-Id";
	final ItemService itemService;
	@PostMapping
	ItemDto createItem(@RequestHeader(SHARER_USER_ID) long userId,
			@Validated({Create.class, Update.class}) @RequestBody ItemCreateDto itemDto) {
		itemDto.setUserId(userId);
		log.info("==> Creating item: {}", itemDto);
		ItemDto item = itemService.createItem(itemDto);
		log.info("<== Creating item: {}", item);
		return item;
	}

	@PatchMapping("/{itemId}")
	ItemDto updateItem(@PathVariable @Min(0) Long itemId,
			 @RequestBody ItemUpdateDto itemDto) {
//		Item item = itemService.getById(itemId);
//		if (itemDto.isNameNotNull(itemDto)) {
//			item.setName(itemDto.getName());
//		}
//		return itemDto;
		return new ItemDto();
	}

	@GetMapping("/{id}")
	ItemInfoDto getItem(@PathVariable String id) {
		List<Item> items = itemService.findByBookings_User_Email(email);
		return new ItemInfoDto();
	}
}
