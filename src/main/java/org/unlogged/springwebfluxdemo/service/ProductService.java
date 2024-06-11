package org.unlogged.springwebfluxdemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.unlogged.springwebfluxdemo.model.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductService {

    @Autowired
    private ReactiveMongoTemplate mongoTemplate;

    public Mono<Product> createProduct(Product product) {
        return mongoTemplate.save(product);
    }

    public Mono<Product> getProductById(String id) {
        return mongoTemplate.findById(id, Product.class);
    }

    public Flux<Product> getAllProducts() {
        return mongoTemplate.findAll(Product.class);
    }

    public Mono<Product> updateProduct(String id, Product product) {
        Query query = new Query(Criteria.where("id").is(id));
        return mongoTemplate.findOne(query, Product.class)
                .flatMap(existingProduct -> {
                    existingProduct.setName(product.getName());
                    existingProduct.setPrice(product.getPrice());
                    return mongoTemplate.save(existingProduct);
                });
    }

    public Mono<Void> deleteProduct(String id) {
        Query query = new Query(Criteria.where("id").is(id));
        return mongoTemplate.remove(query, Product.class).then();
    }
}
