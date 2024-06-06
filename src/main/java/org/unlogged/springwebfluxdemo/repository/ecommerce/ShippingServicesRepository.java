package org.unlogged.springwebfluxdemo.repository.ecommerce;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import org.unlogged.springwebfluxdemo.entity.ShippingServices;

@Repository
public interface ShippingServicesRepository extends ReactiveMongoRepository<ShippingServices, String> {
}
