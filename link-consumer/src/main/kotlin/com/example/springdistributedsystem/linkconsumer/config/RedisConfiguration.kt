package com.example.springdistributedsystem.linkconsumer.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.cache.RedisCacheConfiguration
import org.springframework.data.redis.cache.RedisCacheManager
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.connection.RedisStandaloneConfiguration
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair
import org.springframework.data.redis.serializer.StringRedisSerializer
import java.time.Duration

@Configuration
class RedisConfiguration {

    @Value("\${spring.data.redis.port}")
    lateinit var redisPort: String

    @Bean
    fun jedisConnectionFactory(): JedisConnectionFactory {
        return JedisConnectionFactory(RedisStandaloneConfiguration("redis", redisPort.toInt()))
    }

    @Bean
    fun cacheConfigurator(): RedisCacheConfiguration {
        return RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMinutes(10)).disableCachingNullValues()
            .serializeValuesWith(SerializationPair.fromSerializer(GenericJackson2JsonRedisSerializer()))
            .serializeKeysWith(SerializationPair.fromSerializer(StringRedisSerializer()))
    }

    @Bean
    fun cacheManager(
        redisConnectionFactory: RedisConnectionFactory,
        redisCacheConfiguration: RedisCacheConfiguration
    ): RedisCacheManager {
        return RedisCacheManager.builder(redisConnectionFactory).apply {
            cacheDefaults(redisCacheConfiguration)
        }.build()
    }

    @Bean
    fun redisTemplate(
        redisConnectionFactory: RedisConnectionFactory,
        redisCacheConfiguration: RedisCacheConfiguration
    ): RedisTemplate<String, Int> {
        return RedisTemplate<String, Int>().apply {
            connectionFactory = redisConnectionFactory
            valueSerializer = GenericJackson2JsonRedisSerializer()
            keySerializer = StringRedisSerializer()
        }
    }
}