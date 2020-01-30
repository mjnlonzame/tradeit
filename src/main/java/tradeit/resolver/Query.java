package tradeit.resolver;

import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;

import tradeit.model.Item;
import tradeit.repository.ItemRepository;

@Component
public class Query implements GraphQLQueryResolver {
	private ItemRepository itemRepository;
	
	public Query(ItemRepository itemRepository) {
		this.itemRepository = itemRepository;
	}
	
    public Iterable<Item> findAllItems() {
        return itemRepository.findAll();
    }

    public long countItems() {
        return itemRepository.count();
    }
}
