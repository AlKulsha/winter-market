package ru.kulsha.wintermarket.core.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kulsha.wintermarket.api.ProductDto;
import ru.kulsha.wintermarket.api.ResourceNotFoundException;
import ru.kulsha.wintermarket.core.entities.Product;
import ru.kulsha.wintermarket.core.repositories.ProductRepository;
import ru.kulsha.wintermarket.core.entities.Category;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryService categoryService;

    public List<Product> findAll(){
        return productRepository.findAll();
    }

    public Optional<Product> findById(Long id){
        return productRepository.findById(id);
    }

    public void deleteById(Long id){
        productRepository.deleteById(id);
    }

//    public void createNewProduct(CreateNewProductDto createNewProductDto) {
//        Product product = new Product();
//        product.setTitle(createNewProductDto.getTitle());
//        product.setPrice(createNewProductDto.getPrice());
//        productRepository.save(product);
//    }

    public Product createNewProduct(ProductDto productDto){
        Product product = new Product();
        product.setPrice(productDto.getPrice());
        product.setTitle(productDto.getTitle());
        Category category = categoryService.findByTitle(productDto.getCategoryTitle()).orElseThrow(()-> new ResourceNotFoundException("Category is not found"));
        product.setCategory(category);
        productRepository.save(product);
        return product;
    }
}
