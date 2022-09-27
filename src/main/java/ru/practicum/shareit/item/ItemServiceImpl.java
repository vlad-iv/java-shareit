package ru.practicum.shareit.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.User;

/**
 * // TODO .
 *
 * @author Vladimir Ivanov (ivanov.vladimir.l@gmail.com)
 */
@Service
public class ItemServiceImpl {
	@Autowired
	ItemRepository itemRepository;
	public ItemDto createItem(ItemDto itemDto) {
		User owner = itemRepository.findById(userId);
		Item item = ItemMapper.toItem(itemDto, owner);
		return itemRepository.save(item);
	}
}
