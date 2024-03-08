package org.unlogged.springwebfluxdemo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

@Configuration
public class ReactiveRedisConfig {

    @Bean
    @Primary
    public ReactiveRedisConnectionFactory reactiveRedisConnectionFactory() {
        return new LettuceConnectionFactory("redis-r", 6379);
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
}
