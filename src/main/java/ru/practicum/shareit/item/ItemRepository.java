package ru.practicum.shareit.item;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ru.practicum.shareit.item.model.Item;

/**
 * // TODO .
 *
 * @author Vladimir Ivanov (ivanov.vladimir.l@gmail.com)
 */
@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

	@Query("select item from Item item" +
			" where item.available = true " +
			" and (item.name like :text)" +
			" and item.description like :text")
	List<Item> search(String text);

//	@EntityGraph(value = "item.owner")
//	List<Item> findByNameAndId(String text, String text);
//
//	@EntityGraph(attributePaths = {"owner"})
//	List<Item> findByName(String text);
}
