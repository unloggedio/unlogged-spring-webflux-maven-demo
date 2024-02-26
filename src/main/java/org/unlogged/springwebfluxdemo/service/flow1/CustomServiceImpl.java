package org.unlogged.springwebfluxdemo.service.flow1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.unlogged.springwebfluxdemo.repository.PersonReactiveMongoRepository;
import org.unlogged.springwebfluxdemo.repository.RedisCoffeeInteractionRepo;
import org.unlogged.springwebfluxdemo.repository.flow1.RxJavaSqlRepo;

@Service
public class CustomServiceImpl extends CustomServiceCEImpl implements CustomService {

    @Autowired
    public CustomServiceImpl(RedisCoffeeInteractionRepo repo, RxJavaSqlRepo sqlRepo, PersonReactiveMongoRepository mongoRepository) {
        super(repo, sqlRepo, mongoRepository);
    }
}
