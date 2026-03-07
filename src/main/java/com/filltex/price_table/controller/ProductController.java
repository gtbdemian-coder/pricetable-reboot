package com.filltex.price_table.controller;

import com.filltex.price_table.domain.Product;
import com.filltex.price_table.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Product 관련 요청을 처리하는 Controller
 */
@RestController //Controller랑 차이점 알아보기
@RequestMapping("/products")
public class ProductController {

    private final ProductService service;

    /**
     * 생성자를 통한 Service 주입
     */
    public ProductController(ProductService service) {
        this.service = service;
    }

    /**
     * 제품 등록
     */
    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return service.save(product);
    }

    /**
     * 전체 제품 조회
     */
    @GetMapping
    public List<Product> getProducts() {
        return service.findAll();
    }

    /**
     * 특정 제품 조회
     */
    @GetMapping("/{id}") //@PathVariable의 기능 알아보기
    public Product getProduct(@PathVariable Long id) {
        return service.findById(id);
    }

    /**
     * 제품 삭제
     */
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        service.delete(id);
    }
}
