package com.hiral.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

@Builder
@NamedQuery(name = "Store.clearAll", query = "DELETE FROM Store")
@NamedQuery(name = "Store.findAll", query = "SELECT s FROM Store s")
@NamedQuery(name = "Store.getByName", query = "SELECT s from Store s where s.name = :name")

public class Store implements Serializable {
@Id
@GeneratedValue
private Long id;
private String name;
private String location;

@OneToMany(mappedBy = "store", fetch = FetchType.EAGER)
private List<Inventory> listOfInventory;

}
