package com.hiral.inventory;

import com.hiral.entity.Inventory;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.stream.Collectors;


@Stateless
@Remote(InventoryService.class)
public class InventoryServiceImpl implements InventoryService {

    @PersistenceContext
    private EntityManager iem;

    @Override
    public void clearList() {
        Query deleteFromInventory = iem.createNamedQuery("Inventory.clearAll");
        deleteFromInventory.executeUpdate();
    }
    @Override
    public List<Inventory> getInventoryList() {
        List<Inventory> inventoryList = iem.createNamedQuery("Inventory.findAll", Inventory.class)
                .getResultList();
        return inventoryList.stream().sorted().collect(Collectors.toList());
    }
    @Override
    public void addToList(Inventory inventory) {
        iem.persist(inventory);
    }
}
