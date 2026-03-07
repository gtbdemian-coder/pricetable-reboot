package com.filltex.price_table.repository;

import com.filltex.price_table.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
