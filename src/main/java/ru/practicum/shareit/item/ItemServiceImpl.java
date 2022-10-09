package ru.practicum.shareit.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
public class ItemServiceImpl {
    @Autowired
    UserRepository userRepository;
    @Autowired
    ItemRepository itemRepository;

    public ItemInfoDto createItem(ItemDto itemDto, Long userId) {
        User owner = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User with id=" + userId + " not found"));
        Item item = ItemMapper.toItem(itemDto, owner);
        return ItemMapper.toItemInfoDto(itemRepository.save(item), null, null);
    }
}
