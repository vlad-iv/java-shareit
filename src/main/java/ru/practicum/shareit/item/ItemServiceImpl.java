package ru.practicum.shareit.item;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import ru.practicum.shareit.booking.Booking;
import ru.practicum.shareit.booking.BookingRepository;
import ru.practicum.shareit.exception.NotFoundException;
import ru.practicum.shareit.item.dto.ItemCreateDto;
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
	private final BookingRepository bookingRepository;

	@Override
	public ItemDto createItem(ItemCreateDto itemDto) {
		User owner = userRepository.findById(itemDto.getUserId())
				.orElseThrow(() -> new NotFoundException("User not found"));
		Item item = itemMapper.toModel(itemDto, owner);
		// логика
		Item itemSaved = itemRepository.save(item);
		return itemMapper.toDto(itemSaved);
	}

	@Override
	public List<ItemInfoDto> getItems() {
		// 2 * N + 1 - недопустимое решение
		// Найти решение за 2 запроса
		List<Item> items = itemRepository.findByOwner_Id(3L); // Запрос 1
		List<Long> itemIds = items.stream().map(Item::getId).toList();
//		List<Booking> bookings = bookingRepository.findBy(itemIds, LocalDateTime.now() /* Optional */); // Запрос 2
		// bookings -> bookingsMapByItemId
		Map<Long, List<Booking>> bookingsMapByItemId = new HashMap<>();
//		items.stream().map(i -> itemMapper.toItemInfoDto(i, getListBooking(bookingsMapByItemId.get(i.getId())),
//				getNextBooking(bookingsMapByItemId.get(i.getId()))));
		List<ItemInfoDto> itemInfoDtos = items.stream().map(i -> itemMapper.toItemInfoDto(i, bookingsMapByItemId.get(i.getId()))).toList();
		return itemInfoDtos;
	}

	@Transactional
	public ItemDto update(ItemDto itemDto, long userId) {

		Item item = itemRepository.findById(itemDto.getId())
				.orElseThrow(() -> new NotFoundException("Item not found"));
		User owner = userRepository.findById(userId)
				.orElseThrow(() -> new NotFoundException("User not found"));

		// логика
		if (itemDto.getName() != null) {
			item.setName(itemDto.getName());
		}
		item.setOwner(owner);

		itemRepository.save(item); // вызов save для читаемости кода!
		return itemMapper.toDto(item);
	}
 // У пользователя 100 item
	// Сколько запросов в БД, что бы получить ответ вещи + комментарии + бронирования ?
	List<ItemInfoDto> getAll() {
//		List<Item> items = itemRepository.findAll(); // получить вещи

//		List<Long> itemIds = items.stream()....;
//		List<Booking> bookings = через itemIds
//		Map<Long, List<Booking>> itemIdByBookings;
		return Collections.emptyList();
	}
}
