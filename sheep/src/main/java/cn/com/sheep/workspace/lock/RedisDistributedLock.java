package cn.com.sheep.workspace.lock;


import io.lettuce.core.SetArgs;
import io.lettuce.core.api.async.RedisAsyncCommands;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collections;

/**
 * redis 简单实现的分布式锁
 */
@Component
@Data
public class RedisDistributedLock {

    private final static String PREFIX = "lock_";
    private final static Long RELEASE_SUCCESS = 1L;
    private final static String LOCK_SUCCESS = "OK";
    private final static String RELEASE_LOCK_SCRIPT = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
    private ThreadLocal<String> threadLocal = new ThreadLocal<>();

    @Resource(name = "redisTemplate")
    private RedisTemplate redisTemplate;

    public boolean lock(final String lockName, final String requestId, final long expiredInMilliSeconds) {
        String result = (String) redisTemplate.execute((RedisCallback<String>) connection -> {
            Object nativeConnection = connection.getNativeConnection();
            String resultString = "";
            RedisSerializer keySerializer = redisTemplate.getKeySerializer();
            byte[] keyByte = keySerializer.serialize(PREFIX + lockName);
            byte[] valueByte = keySerializer.serialize(requestId);
            if (nativeConnection instanceof RedisAsyncCommands) {
                RedisAsyncCommands commands = (RedisAsyncCommands) nativeConnection;
                resultString = commands.getStatefulConnection().sync().set(keyByte, valueByte, SetArgs.Builder.nx().px(expiredInMilliSeconds));
            }
            return resultString;
        });

        if (LOCK_SUCCESS.equals(result)) {
            threadLocal.remove();
            threadLocal.set(requestId);
            return true;
        }
        return false;
    }

    public boolean unlock(final String lockName, final String requestId) {
        DefaultRedisScript<Long> redisScript = new DefaultRedisScript<>();
        redisScript.setScriptText(RELEASE_LOCK_SCRIPT);
        redisScript.setResultType(Long.class);
        Long result = (Long) redisTemplate.execute(redisScript, Collections.singletonList(PREFIX + lockName), Collections.singletonList(requestId));
        if (RELEASE_SUCCESS.equals(result)) {
            threadLocal.remove();
            return true;
        }
        return false;
    }

    /**
     * 是否可重入锁
     *
     * @param key
     * @param originValue
     * @return
     */
    private Boolean isReentrantLock(String key, String originValue) {
        String value = (String) redisTemplate.opsForValue().get(key);
        return StringUtils.isNotBlank(value) && value.equals(originValue);
    }
}
