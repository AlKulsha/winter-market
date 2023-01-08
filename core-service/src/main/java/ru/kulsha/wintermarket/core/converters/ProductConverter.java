package ru.kulsha.wintermarket.core.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.kulsha.wintermarket.api.ProductDto;
import ru.kulsha.wintermarket.api.ResourceNotFoundException;
import ru.kulsha.wintermarket.core.entities.Category;
import ru.kulsha.wintermarket.core.entities.Product;
import ru.kulsha.wintermarket.core.services.CategoryService;

@Component
@RequiredArgsConstructor
public class ProductConverter {
    private final CategoryService categoryService;

    public Product dtoToEntity(ProductDto productDto){
        Product p = new Product();
        p.setTitle(productDto.getTitle());
        p.setPrice(productDto.getPrice());
        p.setId(productDto.getId());
        Category c = categoryService.findByTitle(productDto.getTitle())
                .orElseThrow(()-> new ResourceNotFoundException("Category is not found"));
        p.setCategory(c);
        return p;
    }

    public ProductDto entityToDto(Product product){
        return new ProductDto(product.getId(), product.getTitle(), product.getPrice(), product.getCategory().getTitle());
    }
}
