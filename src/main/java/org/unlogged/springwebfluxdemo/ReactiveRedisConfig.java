package org.unlogged.springwebfluxdemo;

import com.fasterxml.jackson.databind.json.JsonMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.*;
import org.springframework.data.redis.util.ByteUtils;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.data.redis.serializer.RedisSerializationContext.newSerializationContext;

public class ReactiveRedisConfig {

    @Bean
    public ReactiveRedisConnectionFactory reactiveRedisConnectionFactory() {
        return new LettuceConnectionFactory("localhost", 6379);
    }

    @Bean
    ReactiveRedisOperations<String, String> reactiveRedisOperations(ReactiveRedisConnectionFactory factory) {
        Jackson2JsonRedisSerializer<String> serializer = new Jackson2JsonRedisSerializer<>(String.class);

        RedisSerializationContext.RedisSerializationContextBuilder<String, String> builder =
                newSerializationContext(new StringRedisSerializer());

        RedisSerializationContext<String, String> context = builder.value(serializer).build();
        return new ReactiveRedisTemplate<>(factory, context);
    }


    @Bean
    ReactiveRedisTemplate<Object, Object> reactiveRedisTemplate(ReactiveRedisConnectionFactory factory,
                                                                RedisSerializer<Object> serializer) {
        RedisSerializer<Object> keySerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        RedisSerializationContext.SerializationPair<Object> spair = RedisSerializationContext.SerializationPair.fromSerializer(keySerializer);
        RedisSerializationContext<Object, Object> context = RedisSerializationContext
                .newSerializationContext(serializer).key(spair).hashKey(keySerializer)
                .build();
        return new ReactiveRedisTemplate<>(factory, context);
    }

    @Bean
    public RedisSerializer<Object> springSessionDefaultRedisSerializer() {
        return new JSONSessionRedisSerializer();
    }

    private static class JSONSessionRedisSerializer implements RedisSerializer<Object> {

        private static final byte[] SESSION_DATA_PREFIX = "appsmith-session:".getBytes();

        private static final byte[] OAUTH_CLIENT_PREFIX = "appsmith-oauth-client:".getBytes();

        private final JdkSerializationRedisSerializer fallback = new JdkSerializationRedisSerializer();

        private final GenericJackson2JsonRedisSerializer jsonSerializer = new GenericJackson2JsonRedisSerializer(new JsonMapper());

        @Override
        public byte[] serialize(Object t) {
            return fallback.serialize(t);
        }

        private byte[] serializeOAuthClientMap(Map<?, ?> data) {
            final Map<String, Object> dataMap = new HashMap<>();
            return jsonSerializer.serialize(dataMap);
        }

        @Override
        public Object deserialize(byte[] bytes) {
            return fallback.deserialize(bytes);
        }
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
