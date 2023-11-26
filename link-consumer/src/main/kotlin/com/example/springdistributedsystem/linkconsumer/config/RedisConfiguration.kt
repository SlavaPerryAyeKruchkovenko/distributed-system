package com.example.springdistributedsystem.linkconsumer.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.cache.RedisCacheConfiguration
import org.springframework.data.redis.cache.RedisCacheManager
import org.springframework.data.redis.connection.RedisStandaloneConfiguration
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.RedisSerializationContext.*
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
    fun cacheManager(): RedisCacheManager {
        val redisCacheConfiguration =
            RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMinutes(10)).disableCachingNullValues()
                .serializeValuesWith(SerializationPair.fromSerializer(GenericJackson2JsonRedisSerializer()))

        return RedisCacheManager.builder(jedisConnectionFactory()).apply {
            cacheDefaults(redisCacheConfiguration)
        }.build()
    }

    @Bean
    fun redisTemplate(): RedisTemplate<String, Int> {
        return RedisTemplate<String, Int>().apply {
            connectionFactory = jedisConnectionFactory()
        }
    }
}