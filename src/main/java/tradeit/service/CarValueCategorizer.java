package tradeit.service;

import tradeit.model.ItemValue;

public class CarValueCategorizer implements ValueCategorizer {

    @Override
    public ItemValue getValue(double price) {
        if (price <= 500000)
            return ItemValue.LOW;
        else if (price > 500000 && price <= 1000000)
            return ItemValue.MEDIUM;
        else if (price > 1000000 && price <= 3000000)
            return ItemValue.HIGH;
        else
            return ItemValue.LUXURY;
    }
}
