package zdc.enterprise.controller;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.StringRedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zdc.enterprise.constants.ResultVo;

import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
@Slf4j
@RequestMapping("/redis")
public class RedisTestController {

    @Autowired
    @Qualifier("DefaultRedisTemplate")
    private RedisTemplate defaultRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    /***
     * 根据序列化方式调用不同的实现类
     * @return
     */
    @GetMapping("/test")
    public ResultVo test() {
        return ResultVo.success("defaultRedisTemplate", defaultRedisTemplate.getDefaultSerializer().getClass(),
                "redisTemplate", redisTemplate.getDefaultSerializer().getClass());
    }


    /**
     * 单线程100000测试
     * 20722 19156 19209
     * @return
     */
    @GetMapping("/string1")
    public ResultVo string1() {
        redisTemplate.hasKey("123");

        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            String uuid = UUID.randomUUID().toString();
            redisTemplate.opsForValue().set(uuid, uuid);
        }

        long end = System.currentTimeMillis();

        return ResultVo.success("redisTemplate:100000", end - start);
    }

    /**
     * 100线程100000测试
     * 1230 1416 1228
     * @return
     */
    @GetMapping("/string100")
    public ResultVo string100() throws InterruptedException {
        redisTemplate.hasKey("123");

        CountDownLatch countDownLatch = new CountDownLatch(100000);
        ExecutorService pool = Executors.newFixedThreadPool(100);

        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            pool.execute(()->{
                String uuid = UUID.randomUUID().toString();
                redisTemplate.opsForValue().set(uuid, uuid);
                countDownLatch.countDown();
            });

        }
        countDownLatch.await();
        long end = System.currentTimeMillis();

        return ResultVo.success("redisTemplate:100000", end - start);
    }


    /**
     * 单线程管道100000测试
     * 1694 1126 1409 1064
     * @return
     */
    @GetMapping("/stringPipe")
    public ResultVo stringPipe() throws InterruptedException {
        redisTemplate.hasKey("123");

        long start = System.currentTimeMillis();
        String uuid = UUID.randomUUID().toString();
        redisTemplate.executePipelined(new RedisCallback(){
            @Override
            public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
                System.out.println(redisConnection.getClass());
                for (int i = 0; i < 100000; i++) {
                    byte[] uuid = UUID.randomUUID().toString().getBytes();
                    redisConnection.set(uuid, uuid);
                }
                return null;
            }
        },redisTemplate.getDefaultSerializer());

        long end = System.currentTimeMillis();

        return ResultVo.success("redisTemplate:100000", end - start);
    }


    HashMap<String, Object> map = new HashMap<>();
    HashMap<byte[], byte[]> mapP = new HashMap<>();
    {
        map.put("1111","2222");
        map.put("2222","2222");
        map.put("3333","2222");
        map.put("4444","2222");
        map.put("5555","2222");
        mapP.put("1111".getBytes(),"2222".getBytes());
        mapP.put("2222".getBytes(),"2222".getBytes());
        mapP.put("3333".getBytes(),"2222".getBytes());
        mapP.put("4444".getBytes(),"2222".getBytes());
        mapP.put("5555".getBytes(),"2222".getBytes());
    }




    /**
     * 单线程100000测试 发送了1000000次
     * 20101 20321 20670
     * @return
     */
    @GetMapping("/hash1")
    public ResultVo hash1() {
        redisTemplate.hasKey("123");

        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            String uuid = UUID.randomUUID().toString();
            redisTemplate.opsForHash().putAll(uuid,map);
        }

        long end = System.currentTimeMillis();

        return ResultVo.success("100000", end - start);
    }

    /**
     * 100线程100000测试 发送了最少1000次
     * 1768 1603 1583
     * @return
     */
    @GetMapping("/hash100")
    public ResultVo hash100() throws InterruptedException {
        redisTemplate.hasKey("123");

        CountDownLatch countDownLatch = new CountDownLatch(100000);
        ExecutorService pool = Executors.newFixedThreadPool(100);

        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            pool.execute(()->{
                String uuid = UUID.randomUUID().toString();
                redisTemplate.opsForHash().putAll(uuid,map);
                countDownLatch.countDown();
            });

        }
        countDownLatch.await();
        long end = System.currentTimeMillis();

        return ResultVo.success("redisTemplate:100000", end - start);
    }


    /**
     * 单线程管道100000测试  发送了一次
     * 1456 1374 1319
     * @return
     */
    @GetMapping("/hashPipe")
    public ResultVo hashPipe() throws InterruptedException {
        redisTemplate.hasKey("123");

        long start = System.currentTimeMillis();
        String uuid = UUID.randomUUID().toString();
        redisTemplate.executePipelined(new RedisCallback(){
            @Override
            public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
                System.out.println(redisConnection.getClass());
                for (int i = 0; i < 100000; i++) {
                    byte[] uuid = UUID.randomUUID().toString().getBytes();
                    redisConnection.hMSet(uuid, mapP);
                }
                return null;
            }
        },redisTemplate.getDefaultSerializer());

        long end = System.currentTimeMillis();

        return ResultVo.success("redisTemplate:100000", end - start);
    }

}
