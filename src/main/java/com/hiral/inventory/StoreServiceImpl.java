package com.hiral.inventory;

import com.hiral.entity.Store;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.stream.Collectors;


@Stateless
@Remote(StoreService.class)
public class StoreServiceImpl implements StoreService {

    @PersistenceContext
    private EntityManager em;
    @Override
    public void clearList() {
        Query deleteFromSportInventory = em.createNamedQuery("Store.clearAll");
        deleteFromSportInventory.executeUpdate();
    }
    @Override
    public List<Store> getStoreList() {
        List<Store> storeList =  em.createNamedQuery("Store.findAll", Store.class)
                .getResultList();
        return storeList.stream().collect(Collectors.toList());
    }
    @Override
    public void addToList(Store store) {
        em.persist(store);
    }

}
