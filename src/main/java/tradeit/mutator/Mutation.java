package tradeit.mutator;

import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;

import tradeit.model.Item;
import tradeit.repository.ItemRepository;

@Component
public class Mutation implements GraphQLMutationResolver {

	private ItemRepository itemRepository;

	public Mutation(ItemRepository itemRepository) {
		this.itemRepository = itemRepository;
	}

	public Item newItem(String name, double price) {
//		Item item = new Item(name, price);
//		itemRepository.save(item);
//		return item;
		return null;
	}
}
