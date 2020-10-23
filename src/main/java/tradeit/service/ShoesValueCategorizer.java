package tradeit.service;

import tradeit.model.ItemValue;


public class ShoesValueCategorizer implements ValueCategorizer {
    @Override
    public ItemValue getValue(double price) {
        if (price <= 1000)
            return ItemValue.LOW;
        else if (price > 1000 && price <= 8000)
            return ItemValue.MEDIUM;
        else if (price > 8000 && price <= 15000)
            return ItemValue.HIGH;
        else
            return ItemValue.LUXURY;
    }
}
