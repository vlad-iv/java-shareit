package ru.practicum.shareit.item;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import ru.practicum.shareit.exception.NotFoundException;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.dto.ItemInfoDto;
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

	@Transactional
	public ItemDto update(ItemDto itemDto, long userId) {

		Item item = itemRepository.findById(itemDto.getId()).orElseThrow(() -> new NotFoundException("Item not found"));
		User owner = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found"));
		// логика
		if (itemDto.getName() != null) {
			item.setName(itemDto.getName());
		}
		item.setOwner(owner);

		itemRepository.save(item); // вызов save для читаемости кода!
		return itemMapper.toDto(item);
	}

	List<ItemInfoDto> getAll() {
//		List<Item> items = itemRepository.findAll(); // получить вещи
//		List<Long> itemIds = items.stream()....;
//		List<Booking> bookings = через itemIds
//		Map<Long, List<Booking>> itemIdByBookings;
		return Collections.emptyList();
	}
}
