package com.filltex.price_table.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 제품 정보를 저장하는 엔티티
 * 가격표 시스템에서 관리되는 원단 제품 정보를 나타낸다.
 */
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /** 제품 고유 ID */
    private Long id;

    @Column(nullable = false)
    /** 아이템명 */
    private String itemName;

    /** 가공도 */
    private String finish;

    @Column(nullable = false)
    /** 제품 가격 */
    private Integer price;

    /**
     * 제품 정보 수정
     */
    public void update(String itemName, String finish, Integer price) {
        this.itemName = itemName;
        this.finish = finish;
        this.price = price;
    }


}
