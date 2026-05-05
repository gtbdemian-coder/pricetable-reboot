package com.filltex.price_table.service;

import com.filltex.price_table.domain.Member;
import com.filltex.price_table.domain.MemberRole;
import com.filltex.price_table.domain.Product;
import com.filltex.price_table.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 역할: Product와 관련된 비즈니스 로직을 처리
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
     * 제품 정보를 DB에 저장 -> 요청자를 확인하고 적합자가 아닐경우 예외로 처리함
     */
    public Product save(Product product, Member loginMember) {

        if (loginMember == null || loginMember.getMemberRole() != MemberRole.ADMIN) {
            throw new IllegalStateException("권한 없음");
        }

        return repository.save(product);
    }

    /**
     * 제품 목록 전체 조회
     */
    public List<Product> findAll() {
        return repository.findAll();
    }

    /**
     * 특정한 ID를 가진 제품 조회(*ID가 등록되어있지 않았을 경우, 예외발생)
     */
    public Product findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 제품이 없습니다. id=" + id));
    }

    /**
     * 제품 수정
     */
    @Transactional
    public void update(Long id, String itemName, String finish, Double price) {

        Product product = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 제품 없음"));

        product.update(itemName, finish, price);
    }

    /**
     * ID를 이용한 제품 삭제
     */
    @Transactional
    public void delete(Long id) {

        Product product = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 제품 없음"));

        repository.delete(product);
    }
}
