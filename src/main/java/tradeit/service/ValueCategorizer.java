package tradeit.service;

import tradeit.model.ItemValue;

public interface ValueCategorizer{
    ItemValue getValue(double price);
}
