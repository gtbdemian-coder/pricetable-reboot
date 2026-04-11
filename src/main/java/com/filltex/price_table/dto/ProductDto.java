package com.filltex.price_table.dto;

import com.filltex.price_table.domain.Product;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class ProductDto {

    @NotBlank(message = "아이템명은 필수입니다")
    private String itemName;

    private String finish;

    @NotNull(message = "가격은 필수입니다")
    @Min(value = 0, message = "가격은 0 이상이어야 합니다")
    private Double price;

    public Product toEntity() {
        return new Product(null, itemName, finish, price);
    }
}
