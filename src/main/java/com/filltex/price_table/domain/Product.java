package com.filltex.price_table.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String itemName;

    private String finish;

    @Column(nullable = false)
    private Integer price;

    public void update(String itemName, String finish, Integer price) {
        this.itemName = itemName;
        this.finish = finish;
        this.price = price;
    }


}
