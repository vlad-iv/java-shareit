package ru.practicum.shareit.user;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * // TODO .
 *
 * @author Vladimir Ivanov (ivanov.vladimir.l@gmail.com)
 */
public interface UserRepository extends JpaRepository<User, Long> {
	List<User> findAllByEmail(String email, PageRequest pageRequest);
}
