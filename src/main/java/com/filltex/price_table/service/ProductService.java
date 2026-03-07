package com.filltex.price_table.service;

import com.filltex.price_table.domain.Product;
import com.filltex.price_table.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Product와 관련된 비즈니스 로직을 처리하는 서비스 클래스
 */
@Service
public class ProductService {

    /** Product 데이터베이스에 접근하기 위한 Repositroy */
    private final ProductRepository repository;

    /**
     * 생성자를 통한 Repository 주입
     */
    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    /**
     * 제품 정보를 저장
     */
    public Product save(Product product) {
        return repository.save(product);
    }

    /**
     * 제품 목록 전체 조회
     */
    public List<Product> findAll() {
        return repository.findAll();
    }

    /**
     * 특정한 ID를 가진 제품 조회
     */
    public Product findById(Long id) {
        return repository.findById(id).orElse(null); //orElse 물어보기
    }

    /**
     * ID를 이용한 제품 삭제
     */
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
