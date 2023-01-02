package ru.kulsha.wintermarket.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kulsha.wintermarket.dto.CreateNewProductDto;
import ru.kulsha.wintermarket.dto.ProductDto;
import ru.kulsha.wintermarket.entities.Product;
import ru.kulsha.wintermarket.exceptions.AppError;
import ru.kulsha.wintermarket.exceptions.ResourceNotFoundException;
import ru.kulsha.wintermarket.services.ProductService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public List<ProductDto> findAllProducts(){
        return productService.findAll()
                .stream()
                .map(p -> new ProductDto(p.getId(), p.getTitle(), p.getPrice()))
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
        Product p=productService.findById(id).orElseThrow(()->new ResourceNotFoundException("Product is not found, id: " + id));
        return new ProductDto(p.getId(), p.getTitle(), p.getPrice());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createNewProduct(@RequestBody CreateNewProductDto createNewProductDto){
        productService.createNewProduct(createNewProductDto);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        productService.deleteById(id);
    }
}
