package tradeit.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import tradeit.model.Item;

public interface ItemRepository extends CrudRepository<Item, Long> {
	public Optional<Item> findByName(String name);
}
