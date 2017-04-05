package com.hull.test.redis;

import com.hull.test.autoMethod.camel.BizException;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created by Administrator on 2017/4/5.
 */
public class RedisSequence {
    private static final long STEP = 1;
    private static long currVal ;
    private static final long THRESHOLD = 100;  //阀值，每次达到这个值的倍数时，更新到数据库
    private static JedisPool pool;
    private static Jedis jedis;

    static {
        pool = new JedisPool(new JedisPoolConfig(), "127.0.0.1");
        jedis = pool.getResource();
    }

    public static synchronized long getNextValue(String name) {
        if(name==null){
            throw new BizException("99999","序列关键字不能为空");
        }
        if(jedis.get(name)==null){ //如果redis 中获取不到序列值，从数据库中获取
            currVal = getSequenceId(name);
        }else {
            currVal = Long.parseLong(jedis.get(name));
        }
        currVal = currVal +STEP;
        jedis.set(name, String.valueOf(currVal));

        if(currVal%THRESHOLD==0){
            //当达到阀值时，保存到数据库
            //  1<= 数据库中的sequenceI的 - currVal <=THRESHOLD ，避免redis 中的序列ID 冲突
            updateSequence(name,currVal+THRESHOLD);
        }
        System.out.println("获取到的nextVal："+currVal);
        return currVal;
    }

    /**
     * 从数据库查询序列值
     * @param name
     * @return
     */
    private static long getSequenceId(String name) {
        System.out.println("从数据库获取sequenceId:"+name);
        return 1100;
    }

    /**
     * 更新数据库序列值
     * @param name
     * @param currVal
     */
    private static void updateSequence(String name, long currVal) {
        System.out.println("更新数据库:"+name+"="+currVal);
    }

    public static void main(String[] args) {
        RedisSequence.getNextValue("subStan");
    }
}
