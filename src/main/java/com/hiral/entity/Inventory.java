package com.hiral.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data //Lombok data annotatio
@AllArgsConstructor
@NoArgsConstructor // we need noargs constructor by default
@Entity
@Builder
@NamedQuery(name = "Inventory.clearAll", query = "DELETE FROM Inventory")
@NamedQuery(name = "Inventory.findAll", query = "SELECT ivt FROM Inventory ivt")
@NamedQuery(name = "Inventory.getByName", query = "SELECT ivt from Inventory ivt where ivt.name = :name")
public class Inventory implements Comparable<Inventory>, Serializable { //here also implementing comparable interface

    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true)
    private String name;
    private String sportt;
    private int numberOfQuantity;
    private double pricePerUnit;
    private Date inventoryDateUpdated;

    @ManyToOne
    @JoinColumn(name = "id_store")
    private Store store;

    @PrePersist
    void createdAt() {
        this.inventoryDateUpdated = new Date();
    }

    @PreUpdate
    void updatedAt() {
        this.inventoryDateUpdated = new Date();
    }

    @Override
    public int compareTo(Inventory o) {
        return inventoryDateUpdated.compareTo(o.inventoryDateUpdated);
    }


}
