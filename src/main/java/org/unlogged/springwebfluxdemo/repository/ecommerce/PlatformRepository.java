package org.unlogged.springwebfluxdemo.repository.ecommerce;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import org.unlogged.springwebfluxdemo.entity.Platform;

@Repository
public interface PlatformRepository extends ReactiveMongoRepository<Platform, String> {

}
