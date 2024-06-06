package org.unlogged.springwebfluxdemo.repository.ecommerce;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import org.unlogged.springwebfluxdemo.entity.Seller;

@Repository
public interface SellerRepository extends ReactiveMongoRepository<Seller, String> {
}
