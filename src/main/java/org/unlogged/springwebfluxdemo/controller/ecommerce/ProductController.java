package org.unlogged.springwebfluxdemo.controller.ecommerce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.unlogged.springwebfluxdemo.model.ecommerce.ProductDto;
import org.unlogged.springwebfluxdemo.service.ecommerce.ProductService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/ecommerce/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public Flux<ProductDto> getProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{productId}")
    public Mono<ProductDto> getProductById(@PathVariable String productId) {
        return productService.getProduct(productId);
    }

    @PostMapping
    public Mono<ProductDto> saveProduct(@RequestBody Mono<ProductDto> productDtoMono) {
        return productService.saveProduct(productDtoMono);
    }

    @PutMapping("/update/{productId}")
    public Mono<ProductDto> updateProduct(@RequestBody Mono<ProductDto> productDtoMono, @PathVariable String productId) {
        return productService.updateProduct(productDtoMono, productId);
    }

    @DeleteMapping("/delete/{productId}")
    public Mono<Void> deleteProduct(@PathVariable String productId) {
        return productService.deleteProduct(productId);
    }
}
