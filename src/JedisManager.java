import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisManager {
    private static final String REDIS_HOST = "localhost";
    private static final int REDIS_PORT = 6379;

    private static JedisPool jedisPool;

    // Singleton tasarım deseni kullanarak JedisPool'u oluşturuyoruz
    private static synchronized JedisPool getJedisPool() {
        if (jedisPool == null) {
            JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
            jedisPool = new JedisPool(jedisPoolConfig, REDIS_HOST, REDIS_PORT);
        }
        return jedisPool;
    }

    // Redis bağlantısını alma
    public static Jedis getJedis() {
        return getJedisPool().getResource();
    }

    // Redis bağlantısını geri bırakma
    public static void returnJedis(Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }
    }
}
