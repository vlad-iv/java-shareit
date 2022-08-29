package ru.practicum.shareit.item;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import ru.practicum.shareit.exception.NotFoundException;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.User;
import ru.practicum.shareit.user.UserRepository;

/**
 * // TODO .
 *
 * @author Vladimir Ivanov (ivanov.vladimir.l@gmail.com)
 */
@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {
	final ItemRepository itemRepository;
	final UserRepository userRepository;
	final ItemMapper itemMapper;

	@Override
	public ItemDto createItem(ItemDto itemDto, long userId) {
		User owner = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found"));
		Item item = itemMapper.toModel(itemDto, owner);
		// логика
		Item itemSaved = itemRepository.save(item);
		return itemMapper.toDto(itemSaved);
	}
}
