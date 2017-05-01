package site.zido.jianliao.common;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

/**
 * cyd.
 * Date: 2017/3/22 0022
 * Time: 20:25
 *
 * @author <a href="http://zido.site">wuhongxu</a>.
 * @version 1.0.0
 */
@Configuration
@EnableCaching
public class CacheConfiguration {
    @Bean
    public CacheManager getCacheManager(){
        final ConcurrentMapCache zido = new ConcurrentMapCache("zido");
        SimpleCacheManager manager = new SimpleCacheManager();
        manager.setCaches(Collections.singletonList(zido));
        return manager;
    }
}
