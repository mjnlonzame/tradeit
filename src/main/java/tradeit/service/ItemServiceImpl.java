package tradeit.service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tradeit.model.CarValue;
import tradeit.model.Item;
import tradeit.repository.ItemRepository;

@Service
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public List<Item> getAll() {
        return StreamSupport.stream(itemRepository.findAll().spliterator(), false)
                .sorted(Comparator.comparing(Item::getPurchasedDate))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Item> getById(Long id) {

        return itemRepository.findById(id);
    }

    @Override
    public Optional<Item> getByName(String name) {
        return itemRepository.findByName(name);
    }

    @Override
    public Item save(Item item) {
//		item =  Item.builder().category(Category.APPLIANCE).build();
        return itemRepository.save(item);
    }

    @Override
    public Optional<Map<CarValue, List<Item>>> getCarByValue() {
        return Optional.ofNullable(this.getAll().stream().collect(Collectors.groupingBy(Item::getCarValue)));

    }

}
