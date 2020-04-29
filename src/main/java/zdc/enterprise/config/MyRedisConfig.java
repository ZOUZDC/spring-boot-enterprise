package zdc.enterprise.config;

import com.alibaba.fastjson.support.spring.GenericFastJsonRedisSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

@Configuration
public class MyRedisConfig {

    /**
     * 指定序列化
     * @param redisConnectionFactory
     * @return
     */
    @Bean
    @Primary
    public RedisTemplate redisTemplate(RedisConnectionFactory redisConnectionFactory){

        RedisTemplate<Object, Object> template = new RedisTemplate();
        template.setConnectionFactory(redisConnectionFactory);
        GenericFastJsonRedisSerializer jsonRedisSerializer = new GenericFastJsonRedisSerializer();
        template.setDefaultSerializer(jsonRedisSerializer);
        template.setKeySerializer(jsonRedisSerializer);
        template.setValueSerializer(jsonRedisSerializer);
        return template;
    }

    /**
     * 指定序列化
     * @param redisConnectionFactory
     * @return
     */
    @Bean(name ="DefaultRedisTemplate" )
    public RedisTemplate defaultRedisTemplate(RedisConnectionFactory redisConnectionFactory){

        RedisTemplate template = new RedisTemplate();
        template.setConnectionFactory(redisConnectionFactory);
        //GenericFastJsonRedisSerializer jsonRedisSerializer = new GenericFastJsonRedisSerializer();
        //template.setDefaultSerializer(jsonRedisSerializer);
        //template.setKeySerializer(jsonRedisSerializer);
        //template.setValueSerializer(jsonRedisSerializer);
        return template;
    }

}
