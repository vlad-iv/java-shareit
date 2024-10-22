package ru.practicum.shareit.item;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ru.practicum.shareit.item.model.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
	@Override
	@EntityGraph("item.owner")
	Optional<Item> findById(Long id);

    @EntityGraph("item.owner")
    List<Item> findByOwner_Id(Long ownerId);

    Optional<Item> findByIdAndOwner_Id(Long id, Long ownerId);

	@Query("select item from Item item" +
			" where item.available = true " +
			" and (item.name like :text)" +
			" and item.description like :text")
	List<Item> search(String text);

	@EntityGraph(value = "item.owner")
	List<Item> findByNameAndId();

	@EntityGraph(attributePaths = {"owner"})
	List<Item> findByName(String text);
}
