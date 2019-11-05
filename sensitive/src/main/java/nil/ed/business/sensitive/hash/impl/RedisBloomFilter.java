package nil.ed.business.sensitive.hash.impl;

import io.rebloom.client.Client;
import nil.ed.business.sensitive.hash.IBloomFilter;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.util.Pool;

/**
 * @author delin10
 * @since 2019/11/5
 **/
public class RedisBloomFilter implements IBloomFilter<String> {
    private String bloomKey;

    private Client client;

    RedisBloomFilter(String bloomKey, Client client) {
        this.client = client;
        this.bloomKey =bloomKey;
    }



    @Override
    public boolean exist(String e) {
        return client.exists(bloomKey, e);
    }

    @Override
    public boolean insert(String e) {
        return client.add(bloomKey, e);
    }
}
