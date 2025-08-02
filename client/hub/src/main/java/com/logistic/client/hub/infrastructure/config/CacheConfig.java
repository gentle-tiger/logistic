package com.logistic.client.hub.infrastructure.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.logistic.client.hub.application.dto.FindRouteResponse;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.CacheKeyPrefix;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair;
import org.springframework.data.redis.serializer.RedisSerializer;
@Configuration
@EnableCaching
public class CacheConfig {

  @Bean
  public ObjectMapper objectMapper() {
    return new ObjectMapper()
        .registerModule(new JavaTimeModule())
        .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
  }

  /* ---------- 직렬화기 ---------- */
  @Bean
  public RedisSerializer<Object> genericSerializer(ObjectMapper mapper) {
    // 타입 정보 포함, 범용
    return new GenericJackson2JsonRedisSerializer(mapper);
  }

  @Bean
  public RedisSerializer<FindRouteResponse> routeSerializer(ObjectMapper mapper) {
    // FindRouteResponse 전용 (deprecated 메서드 X)
    return new Jackson2JsonRedisSerializer<>(mapper, FindRouteResponse.class);
  }

  /* ---------- 캐시 매니저 ---------- */
  @Bean
  public RedisCacheManager cacheManager(RedisConnectionFactory factory,
      RedisSerializer<Object> genericSerializer,
      RedisSerializer<FindRouteResponse> routeSerializer) {

    // 기본 설정
    RedisCacheConfiguration defaultConf = RedisCacheConfiguration.defaultCacheConfig()
        .disableCachingNullValues()
        .entryTtl(Duration.ofHours(1))
        .computePrefixWith(CacheKeyPrefix.simple())
        .serializeValuesWith(SerializationPair.fromSerializer(genericSerializer));

    // 캐시별 설정
    Map<String, RedisCacheConfiguration> confs = new HashMap<>();
    confs.put("hubDistance",
        defaultConf.serializeValuesWith(
            SerializationPair.fromSerializer(routeSerializer)));
    confs.put("allHubsCache",
        defaultConf.entryTtl(Duration.ofHours(2)));   // genericSerializer 그대로

    return RedisCacheManager.builder(factory)
        .cacheDefaults(defaultConf)
        .withInitialCacheConfigurations(confs)
        .build();
  }
}
