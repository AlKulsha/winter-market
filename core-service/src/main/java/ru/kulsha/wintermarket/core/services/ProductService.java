package ru.kulsha.wintermarket.core.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.kulsha.wintermarket.api.ProductDto;
import ru.kulsha.wintermarket.api.ResourceNotFoundException;
import ru.kulsha.wintermarket.core.entities.Product;
import ru.kulsha.wintermarket.core.repositories.ProductRepository;
import ru.kulsha.wintermarket.core.entities.Category;
import ru.kulsha.wintermarket.core.repositories.specifications.ProductsSpecifications;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryService categoryService;

    public Page<Product> findAll(Specification<Product> spec, int page){
        return productRepository.findAll(spec, PageRequest.of(page, 5));
    }

    public Optional<Product> findById(Long id){
        return productRepository.findById(id);
    }

    public void deleteById(Long id){
        productRepository.deleteById(id);
    }


    public Product createNewProduct(ProductDto productDto){
        Product product = new Product();
        product.setPrice(productDto.getPrice());
        product.setTitle(productDto.getTitle());
        Category category = categoryService.findByTitle(productDto.getCategoryTitle()).orElseThrow(()-> new ResourceNotFoundException("Category is not found"));
        product.setCategory(category);
        productRepository.save(product);
        return product;
    }

    public Specification<Product> createSpecByFilters(Integer minPrice, Integer maxPrice, String title){
        Specification<Product> spec = Specification.where(null);
        if(minPrice != null){
            spec = spec.and(ProductsSpecifications.priceGreaterOrEqualsThan(minPrice));
        }
        if(maxPrice != null){
            spec = spec.and(ProductsSpecifications.priceLessThanOrEqualsThan(maxPrice));
        }
        if(title != null){
            spec = spec.and(ProductsSpecifications.titleLike(title));
        }
        return spec;
    }
}
