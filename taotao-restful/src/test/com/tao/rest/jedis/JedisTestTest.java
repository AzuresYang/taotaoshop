package com.tao.rest.jedis;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import redis.clients.jedis.*;
import redis.clients.jedis.exceptions.JedisClusterException;

import java.util.HashSet;

import static org.junit.Assert.*;

/**
 * Created by 28029 on 2018/4/6.
 */
public class JedisTestTest {
    private static final String HOST = "193.112.68.221";
    private static final Logger LOGGER = LoggerFactory.getLogger(JedisTestTest.class);


    @Test
    public void testJedisSingle() throws Exception {
        //创建一个jedis对象
        Jedis jedis = new Jedis(HOST,6379);
        jedis.set("key1","jedis Test");
        String str = jedis.get("key1");
        System.out.println(str);
        jedis.close();
    }
    @Test
    public void testJedisPool(){
        //创建jedis连接池
        JedisPool pool = new JedisPool(HOST, 6379);
        //从连接池中获得Jedis对象
        Jedis jedis = pool.getResource();
        String string = jedis.get("key1");
        System.out.println(string);
        //关闭jedis对象
        jedis.close();
        pool.close();
    }

    @Test
    public void testJedisPoolClusterXun(){
        HashSet<HostAndPort> nodes = new HashSet<>();
        nodes.add(new HostAndPort(HOST, 7001));
        nodes.add(new HostAndPort(HOST, 7002));
        nodes.add(new HostAndPort(HOST, 7003));
        nodes.add(new HostAndPort(HOST, 7004));
        nodes.add(new HostAndPort(HOST, 7005));
        nodes.add(new HostAndPort(HOST, 7006));

        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(30);
        config.setMaxIdle(10);
        config.setNumTestsPerEvictionRun(1024);
        config.setTimeBetweenEvictionRunsMillis(30000);
        config.setMinEvictableIdleTimeMillis(1800000);
        config.setSoftMinEvictableIdleTimeMillis(10000);
        config.setMaxWaitMillis(1500);
        config.setTestOnBorrow(true);
        config.setTestWhileIdle(true);
        config.setBlockWhenExhausted(false);


        //LOGGER.info("创建一个JedisCluster对象");
        JedisCluster cluster = new JedisCluster(nodes,config);
        int i = 0;
        int failTime = 0;
        int loopTime = 20;
        while(i < loopTime)
        {
            i++;
            System.out.println("time:"+i);

            while(!useJedisPoolCluster(cluster))
                failTime++;
        }
        cluster.close();
        System.out.println("fail Time:"+failTime);
        System.out.println("比例："+(float)((float)failTime/(float)loopTime));

    }
    public boolean useJedisPoolCluster(JedisCluster cluster){


     //   LOGGER.info("设置key1的值为1000");
       // cluster.set("c", "world");
        boolean connectR= false;
        LOGGER.info("从Redis中取key1的值");
        try{

            String string = cluster.get("c");
            System.out.println("succeed:"+string);
            connectR = true;

        }catch(JedisClusterException e)
        {
            System.out.println("fail");
        }finally {

            return connectR;
        }
    }

    @Test
    public void testSpringJedisSingle() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
                "classpath:springConfig/applicationContext-*.xml");
        JedisPool pool = (JedisPool) applicationContext.getBean("redisClient");
        Jedis jedis = pool.getResource();
        String string = jedis.get("key1");
        System.out.println(string);
        jedis.close();
        pool.close();
    }

    @Test
    public void testSpringJedisCluster() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
                "classpath:springConfig/applicationContext-*.xml");
        int stop = 1000;
        int i = 0;
        JedisCluster jedisCluster =  (JedisCluster) applicationContext.getBean("redisClient");
        while(i < stop)
        {
            i++;
            System.out.println("time:www.ourtube.com"+i);
            JedisCluster jedisClusters =  (JedisCluster) applicationContext.getBean("redisClient");
            // jedisCluster.set("c","hello");
            String string = jedisClusters.get("c");
            System.out.println("sC:"+string);
        }
        jedisCluster.close();

    }

}