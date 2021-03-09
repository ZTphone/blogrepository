package com.example.myblogproject.utils;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.ResourceBundle;

@Component
public class RedisUtil {

    public  static  JedisPool jedisPool;

    static {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("redis");

        int maxActive = Integer.parseInt(resourceBundle.getString("spring.redis.jedis.pool.max-active"));
        int maxIdle = Integer.parseInt(resourceBundle.getString("spring.redis.jedis.pool.max-idle"));
        int maxWait = Integer.parseInt(resourceBundle.getString("spring.redis.jedis.pool.max-wait"));
        System.out.println(resourceBundle.getString("spring.redis.jedis.pool.max-wait")+"******");
        String ip = resourceBundle.getString("spring.redis.host");
        int port = Integer.parseInt(resourceBundle.getString("spring.redis.port"));

        JedisPoolConfig config = new JedisPoolConfig();

        config.setMaxTotal(maxActive);

        config.setMaxIdle(maxIdle);

        config.setMaxWaitMillis(maxWait);


        jedisPool = new JedisPool(config, ip, port,100000);
    }

    /**
     * 向缓存中设置字符串内容
     * @param key key
     * @param value value
     * @return
     * @throws Exception
     */
    public static boolean  set(String key,String value) throws Exception{
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.set(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }finally{
            jedisPool.returnResource(jedis);
        }
    }

    /**
     * 向缓存中设置对象
     * @param key
     * @param value
     * @return
     */
    public static boolean  set(String key,Object value){
        Jedis jedis = null;
        try {
            String objectJson = JSON.toJSONString(value);
            jedis = jedisPool.getResource();
            jedis.set(key, objectJson);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }finally{
            jedisPool.returnResource(jedis);
        }
    }

    /**
     * 删除缓存中得对象，根据key
     * @param key
     * @return
     */
    public static boolean del(String key){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.del(key);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }finally{
            jedisPool.returnResource(jedis);
        }
    }

    /**
     * 根据key 获取内容
     * @param key
     * @return
     */
    public static Object get(String key){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            Object value = jedis.get(key);
            return value;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }finally{
            jedisPool.returnResource(jedis);
        }
    }


    /**
     * 根据key 获取对象
     * @param key
     * @return
     */
    public static <T> T get(String key,Class<T> clazz){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String value = jedis.get(key);
            return JSON.parseObject(value, clazz);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }finally{
            jedisPool.returnResource(jedis);
        }
    }

    public static boolean flush(){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.flushDB();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }finally{
            jedisPool.returnResource(jedis);
        }
    }

    public static boolean isEmpty(){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.dbSize()!=0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }finally{
            jedisPool.returnResource(jedis);
        }
    }

}
