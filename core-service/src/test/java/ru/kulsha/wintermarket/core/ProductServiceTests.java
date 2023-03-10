package ru.kulsha.wintermarket.core;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.kulsha.wintermarket.api.ProductDto;
import ru.kulsha.wintermarket.core.entities.Category;
import ru.kulsha.wintermarket.core.repositories.ProductRepository;
import ru.kulsha.wintermarket.core.services.CategoryService;
import ru.kulsha.wintermarket.core.services.ProductService;

import java.util.Collections;
import java.util.Optional;

@SpringBootTest(classes = ProductService.class)
public class ProductServiceTests {
    @Autowired
    private ProductService productService;

    @MockBean
    private ProductRepository productRepository;

    @MockBean
    private CategoryService categoryService;

    @Test
    public void createNewProductTest() {
        Category category = new Category();
        category.setId(1L);
        category.setTitle("Food");
        category.setProducts(Collections.emptyList());
        Mockito.doReturn(Optional.of(category))
                .when(categoryService)
                .findByTitle("Food");

        ProductDto productDto = new ProductDto(null, "Oranges", 100, "Food");
        productService.createNewProduct(productDto);

        Mockito.verify(productRepository, Mockito.times(1)).save(ArgumentMatchers.any());
    }

}
