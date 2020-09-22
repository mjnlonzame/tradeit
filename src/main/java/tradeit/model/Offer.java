package tradeit.model;

import java.util.Optional;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Offer {
	private Item item;
	private double breakEvenPrice;

	public Optional<Item> getOptItem() {
		return Optional.ofNullable(item);
	}

}
