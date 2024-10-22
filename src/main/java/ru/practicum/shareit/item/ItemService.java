package ru.practicum.shareit.item;

import java.util.List;

import ru.practicum.shareit.item.dto.ItemCreateDto;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.dto.ItemInfoDto;

/**
 * // TODO .
 *
 * @author Vladimir Ivanov (ivanov.vladimir.l@gmail.com)
 */
public interface ItemService {

	ItemDto createItem(ItemCreateDto itemDto);

	List<ItemInfoDto> getItems();

//	List<ItemDto> findBy(ItemParams itemParams);
}
