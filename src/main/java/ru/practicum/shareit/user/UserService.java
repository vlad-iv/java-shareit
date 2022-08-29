package ru.practicum.shareit.user;

import org.springframework.transaction.annotation.Transactional;

public interface UserService {
    @Transactional
    UserDto createUser(UserDto dto);

    @Transactional
    UserDto updateUser(UserDto dto);
}
