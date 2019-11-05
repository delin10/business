package nil.ed.business.sensitive.hash.impl;

import io.rebloom.client.Client;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.util.Pool;

/**
 * @author delin10
 * @since 2019/11/5
 **/
public class RedisBloomFilterBuilder {

    private String host;

    private int port;

    private int initialCapacity;

    private double errorRate;

    private String bloomKey;

    public RedisBloomFilterBuilder setHost(String host) {
        this.host = host;
        return this;
    }

    public RedisBloomFilterBuilder setPort(int port) {
        this.port = port;
        return this;
    }

    public RedisBloomFilterBuilder setInitialCapacity(int initialCapacity) {
        this.initialCapacity = initialCapacity;
        return this;
    }

    public RedisBloomFilterBuilder setErrorRate(double errorRate) {
        this.errorRate = errorRate;
        return this;
    }

    public RedisBloomFilterBuilder setBloomKey(String bloomKey) {
        this.bloomKey = bloomKey;
        return this;
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public int getInitialCapacity() {
        return initialCapacity;
    }

    public double getErrorRate() {
        return errorRate;
    }

    public String getBloomKey() {
        return bloomKey;
    }

    public RedisBloomFilter build(){
        Client client = initClient();
        return new RedisBloomFilter(bloomKey, client);
    }

    private Client initClient(){
        JedisPoolConfig config = new JedisPoolConfig();
        Pool<Jedis> pool = new JedisPool(config, host, port);
        Client client = new Client(pool);
        pool.getResource().del(bloomKey);
        client.createFilter(bloomKey, this.initialCapacity, this.errorRate);
        return client;
    }
}
