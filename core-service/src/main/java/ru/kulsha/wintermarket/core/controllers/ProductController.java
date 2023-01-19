package ru.kulsha.wintermarket.core.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.kulsha.wintermarket.api.ProductDto;
import ru.kulsha.wintermarket.api.ResourceNotFoundException;
import ru.kulsha.wintermarket.core.converters.ProductConverter;
import ru.kulsha.wintermarket.core.entities.Product;
import ru.kulsha.wintermarket.core.services.CategoryService;
import ru.kulsha.wintermarket.core.services.ProductService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final CategoryService categoryService;
    private final ProductConverter productConverter;

    @GetMapping
    public List<ProductDto> findAllProducts(){
        return productService.findAll()
                .stream()
                .map(productConverter::entityToDto)
                .collect(Collectors.toList());
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<?> findById(@PathVariable Long id){
//        Optional<Product> product = productService.findById(id);
//        if (!product.isPresent()) {
//            ResponseEntity<AppError> err = new ResponseEntity<>(new AppError(HttpStatus.NOT_FOUND.value(),"Product is not found, id: " + id), HttpStatus.NOT_FOUND);
//            return err;
//        }
//        return new ResponseEntity<>(product.get(), HttpStatus.OK);
//    }

    @GetMapping("/{id}")
    public ProductDto findById(@PathVariable Long id){
        Product p = productService.findById(id).orElseThrow(()->new ResourceNotFoundException("Product is not found, id: " + id));
        return productConverter.entityToDto(p);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDto createNewProduct(@RequestBody ProductDto productDto){
        Product product = productService.createNewProduct(productDto);
        return productConverter.entityToDto(product);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        productService.deleteById(id);
    }
}
