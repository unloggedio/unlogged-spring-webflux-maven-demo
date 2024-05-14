package org.unlogged.springwebfluxdemo.service.ecommerce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.unlogged.springwebfluxdemo.model.ecommerce.ProductDto;
import org.unlogged.springwebfluxdemo.repository.ecommerce.ProductRepository;
import org.unlogged.springwebfluxdemo.utils.ProductUtil;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public Flux<ProductDto> getAllProducts() {
        return repository.findAll().map(ProductUtil::productEntityToProductDto);
    }

    public Mono<ProductDto> getProduct(String productId) {
        return repository.findById(productId).map(ProductUtil::productEntityToProductDto);
    }

//    public Flux<ProductDto> getAllProductsFromBrand(String brand) {
//        return repository.findProductsByBrand(brand);
//    }

    public Mono<ProductDto> saveProduct(Mono<ProductDto> productDtoMono) {
        return productDtoMono.map(ProductUtil::productDtoToProductEntity)
                .flatMap(repository::insert)
                .map(ProductUtil::productEntityToProductDto);
    }

    public Mono<ProductDto> updateProduct(Mono<ProductDto> productDtoMono, String productId) {
        return repository.findById(productId)
                .flatMap(product -> productDtoMono.map(ProductUtil::productDtoToProductEntity)
                .doOnNext(e-> e.setProductId(productId)))
                        .flatMap(repository::save)
                        .map(ProductUtil::productEntityToProductDto);
    }

    public Mono<Void> deleteProduct(String productId) {
        return repository.deleteById(productId);
    }
}
