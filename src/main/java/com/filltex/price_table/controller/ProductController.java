package com.filltex.price_table.controller;

import com.filltex.price_table.domain.Member;
import com.filltex.price_table.domain.MemberRole;
import com.filltex.price_table.domain.Product;
import com.filltex.price_table.dto.ProductDto;
import com.filltex.price_table.service.ProductService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
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
    public String productForm(HttpSession httpSession, Model model) {
        Member loginMember = (Member) httpSession.getAttribute("loginMember");

        if (loginMember == null) {
            return "redirect:/products";
        }

        if (loginMember.getMemberRole() != MemberRole.ADMIN) {
            return "redirect:/products";
        }

        model.addAttribute("product", new ProductDto());
        return "product-form";

    }

    /**
     * 제품 등록(등록 완료 후 목록 페이지로 이동)
     */
    @PostMapping("/new")
    public String createProduct(@Valid @ModelAttribute("product") ProductDto productDto, BindingResult bindingResult, HttpSession httpSession) {
        Member loginMember = (Member) httpSession.getAttribute("loginMember");

        if (loginMember == null) {
            return "redirect:/products";
        }

        if (loginMember.getMemberRole() != MemberRole.ADMIN) {
            return "redirect:/products";
        }

        if (bindingResult.hasErrors()) {
            return "product-form";
        }

        service.save(productDto.toEntity(), loginMember);
        return "redirect:/products";
    }

    /**
     * 제품 수정 페이지 조회(수정 완료 후 수정폼으로 이동)
     */
    @GetMapping("/{id}/edit")
    public String editProduct(@PathVariable Long id, Model model, HttpSession httpSession) {

        Member loginMember = (Member) httpSession.getAttribute("loginMember");

        if (loginMember == null || loginMember.getMemberRole() != MemberRole.ADMIN) {
            return "redirect:/products";
        }

        Product product = service.findById(id);

        ProductDto productDto = new ProductDto();
        productDto.setItemName(product.getItemName());
        productDto.setFinish(product.getFinish());
        productDto.setPrice(product.getPrice());

        model.addAttribute("product", productDto);

        return "product-form";
    }

    /**
     * 제품 수정(수정 완료 후 목록 페이지로 이동)
     */
    @PostMapping("/{id}/edit")
    public String updateProduct(@PathVariable Long id, @Valid @ModelAttribute("product") ProductDto productDto, BindingResult bindingResult, HttpSession httpSession) {

        Member loginMember = (Member) httpSession.getAttribute("loginMember");

        if (loginMember == null || loginMember.getMemberRole() != MemberRole.ADMIN) {
            return "redirect:/products";
        }

        if (bindingResult.hasErrors()) {
            return "product-form";
        }

        service.update(id, productDto.getItemName(), productDto.getFinish(), productDto.getPrice());

        return "redirect:/products";
    }

    /**
     * 제품 삭제(삭제 완료 후 목록 페이지로 이동)
     */
    @PostMapping("/{id}/delete")
    public String deleteProduct(@PathVariable Long id, HttpSession httpSession) {

        Member loginMember = (Member) httpSession.getAttribute("loginMember");

        if (loginMember == null || loginMember.getMemberRole() != MemberRole.ADMIN) {
            return "redirect:/products";
        }

        service.delete(id);
        return "redirect:/products";
    }
}
