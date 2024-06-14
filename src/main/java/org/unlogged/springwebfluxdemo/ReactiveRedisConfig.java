package org.unlogged.springwebfluxdemo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class ReactiveRedisConfig {

    @Primary
    @Bean
    public ReactiveRedisConnectionFactory reactiveRedisConnectionFactory() {
        return new LettuceConnectionFactory("localhost", 6379);
    }

//    @Bean
//    public ReactiveRedisTemplate<String, EmployeeV1> reactiveRedisTemplate(
//            ReactiveRedisConnectionFactory factory) {
//        StringRedisSerializer keySerializer = new StringRedisSerializer();
//        Jackson2JsonRedisSerializer<EmployeeV1> valueSerializer =
//                new Jackson2JsonRedisSerializer<>(EmployeeV1.class);
//        RedisSerializationContext.RedisSerializationContextBuilder<String, EmployeeV1> builder =
//                RedisSerializationContext.newSerializationContext(keySerializer);
//        RedisSerializationContext<String, EmployeeV1> context =
//                builder.value(valueSerializer).build();
//        return new ReactiveRedisTemplate<>(factory, context);
//    }

//    @Bean
//    public ReactiveRedisTemplate<String, Book> reactiveRedisTemplate(
//            ReactiveRedisConnectionFactory factory) {
//        Jackson2JsonRedisSerializer<Book> serializer = new Jackson2JsonRedisSerializer<>(Book.class);
//
//        RedisSerializationContext<String, Book> context =
//                RedisSerializationContext.<String, Book>newSerializationContext(new StringRedisSerializer())
//                        .value(serializer)
//                        .build();
//
//        return new ReactiveRedisTemplate<>(factory, context);
//    }
}
