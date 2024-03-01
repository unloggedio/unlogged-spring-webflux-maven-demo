package org.unlogged.springwebfluxdemo;


import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import org.bson.conversions.Bson;
import org.springframework.context.annotation.Bean;
import org.springframework.data.convert.DefaultTypeMapper;
import org.springframework.data.convert.Jsr310Converters;
import org.springframework.data.mongodb.ReactiveMongoDatabaseFactory;
import org.springframework.data.mongodb.ReactiveMongoTransactionManager;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;
import org.springframework.data.mongodb.core.convert.MongoTypeMapper;
import org.springframework.data.mongodb.core.convert.NoOpDbRefResolver;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.transaction.reactive.TransactionalOperator;
import org.unlogged.springwebfluxdemo.converter.StringToInstantConverter;
import org.unlogged.springwebfluxdemo.listener.EncryptionMongoEventListener;
import org.unlogged.springwebfluxdemo.service.mock.EncryptionService;

import java.util.Collections;

@EnableReactiveMongoRepositories
public class MongoReactiveApplication
        extends AbstractReactiveMongoConfiguration {

    @Bean
    public MongoClient mongoClient1() {
        return MongoClients.create();
    }

    @Override
    protected String getDatabaseName() {
        return "reactive";
    }

    @Bean
    public ReactiveMongoTransactionManager reactiveMongoTransactionManager(ReactiveMongoDatabaseFactory factory) {
        return new ReactiveMongoTransactionManager(factory);
    }

    @Bean
    public TransactionalOperator transactionalOperator(ReactiveMongoTransactionManager reactiveMongoTransactionManager) {
        return TransactionalOperator.create(reactiveMongoTransactionManager);
    }

    @Bean
    public MongoCustomConversions mongoCustomConversions() {
        return new MongoCustomConversions(Collections.singletonList(new StringToInstantConverter()));
    }

    @Bean
    public MappingMongoConverter mappingMongoConverter(DefaultTypeMapper<Bson> typeMapper, MongoMappingContext context) {
        MappingMongoConverter converter = new MappingMongoConverter(NoOpDbRefResolver.INSTANCE, context);
        converter.setTypeMapper((MongoTypeMapper) typeMapper);
        converter.setCustomConversions(mongoCustomConversions());
        converter.setMapKeyDotReplacement("-UNLOGGED-DOT-REPLACEMENT-");
        return converter;
    }

    @Bean
    public EncryptionMongoEventListener encryptionMongoEventListener(EncryptionService encryptionService) {
        return new EncryptionMongoEventListener(encryptionService);
    }
}