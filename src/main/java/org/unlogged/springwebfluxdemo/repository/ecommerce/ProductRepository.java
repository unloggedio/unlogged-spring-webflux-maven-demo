package org.unlogged.springwebfluxdemo.repository.ecommerce;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import org.unlogged.springwebfluxdemo.entity.Product;
import org.unlogged.springwebfluxdemo.model.ecommerce.ProductDto;
import reactor.core.publisher.Flux;

@Repository
public interface ProductRepository extends ReactiveMongoRepository<Product, String> {

    Flux<ProductDto> findProductsByBrand(String brand);
}
