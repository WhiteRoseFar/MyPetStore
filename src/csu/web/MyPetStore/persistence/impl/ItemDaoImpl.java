package csu.web.MyPetStore.persistence.impl;

import csu.web.MyPetStore.domain.Item;
import csu.web.MyPetStore.persistence.ItemDao;

import java.util.List;
import java.util.Map;

public class ItemDaoImpl implements ItemDao {
    // private static final String getItemListByProductString;


    @Override
    public void updateInventoryQuantity(Map<String, Object> param) {

    }

    @Override
    public int getInventoryQuantity(String itemId) {
        return 0;
    }

    @Override
    public List<Item> getItemListByProduct(String productId) {
        return null;
    }

    @Override
    public Item getItem(String itemId) {
        return null;
    }
}
