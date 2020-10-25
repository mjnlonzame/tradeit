package tradeit.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import tradeit.model.Item;
import tradeit.model.ItemValue;

public interface ItemService{
	public List<Item> getAll();
	public Optional<Item> getById(Long id);
	public Optional<Item> getByName(String name);
	public Item save(Item item);
	public Optional<Map<ItemValue, List<Item>>> getItemByValue();
	public void deleteById(Long id);
}
