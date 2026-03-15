package com.filltex.price_table.controller;

import com.filltex.price_table.domain.Product;
import com.filltex.price_table.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    /**
     * 제품 등록(등록 완료 후 목록 페이지로 이동)
     */
    @PostMapping("/new")
    public String createProduct(@ModelAttribute Product product) {
        service.save(product);
        return "redirect:/products";
    }

    /**
     * 제품 목록 페이지 조회
     */
    @GetMapping
    public String productList(Model model) {
        model.addAttribute("products", service.findAll());
        return "product-list";
    }

    /**
     * 제품 등록 페이지 조회
     */
    @GetMapping("/new")
    public String productForm(Model model) {
        model.addAttribute("product", new Product());
        return "product-form";
    }

    /**
     * 제품 수정 페이지 조회(수정 완료 후 수정폼으로 이동)
     */
    @GetMapping("/{id}/edit")
    public String editProduct(@PathVariable Long id, Model model) {
        Product product = service.findById(id);
        model.addAttribute("product", product);
        return "product-form";
    }

    /**
     * 제품 수정(수정 완료 후 목록 페이지로 이동)
     */
    @PostMapping("/{id}/edit")
    public String updateProduct(@PathVariable Long id, @ModelAttribute Product product) {

        Product findProduct = service.findById(id);
        findProduct.update(product.getItemName(), product.getFinish(), product.getPrice());

        service.save(findProduct);

        return "redirect:/products";
    }

    /**
     * 제품 삭제(삭제 완료 후 목록 페이지로 이동)
     */
    @PostMapping("/{id}/delete")
    public String deleteProduct(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/products";
    }
}
