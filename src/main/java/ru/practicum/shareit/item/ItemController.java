package ru.practicum.shareit.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.common.Create;
import ru.practicum.shareit.common.Update;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.dto.ItemInfoDto;

/**
 * TODO Sprint add-controllers.
 */
@RestController
@RequestMapping("/items")
public class ItemController {
    @Autowired
    ItemServiceImpl itemService;

    @PostMapping
    ItemInfoDto createItem(@RequestHeader("X-Sharer-User-Id") long userId,
                           @Validated({Create.class}) @RequestBody ItemDto itemDto) {
        return itemService.createItem(itemDto, userId);
    }

    @PatchMapping("/{itemId}")
    ItemDto updateItem(@PathVariable Long itemId,
                       @Validated({Update.class}) @RequestBody ItemDto itemDto) {
        return itemDto;
    }
}
