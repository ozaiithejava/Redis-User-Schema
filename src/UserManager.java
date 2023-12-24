import redis.clients.jedis.Jedis;

public class UserManager {
    private static final String USER_KEY_PREFIX = "user:";

    // Redis bağlantısını alma
    private static Jedis getJedis() {
        return JedisManager.getJedis();
    }

    // Kullanıcıyı Redis'e ekleme veya güncelleme
    public static void saveUser(User user) {
        try (Jedis jedis = getJedis()) {
            String userKey = USER_KEY_PREFIX + user.getId();
            jedis.hset(userKey, "id", user.getId());
            jedis.hset(userKey, "username", user.getUsername());
            jedis.hset(userKey, "password", user.getPassword());
            jedis.hset(userKey, "email", user.getEmail());
            jedis.hset(userKey, "phone", user.getPhone());
            jedis.hset(userKey, "lastLoginIp", user.getLastLoginIp());
        }
    }

    // Kullanıcıyı Redis'ten silme
    public static void deleteUser(String userId) {
        try (Jedis jedis = getJedis()) {
            String userKey = USER_KEY_PREFIX + userId;
            jedis.del(userKey);
        }
    }

    // Kullanıcıyı ID'ye göre Redis'ten getirme
    public static User getUserById(String userId) {
        try (Jedis jedis = getJedis()) {
            String userKey = USER_KEY_PREFIX + userId;
            if (jedis.exists(userKey)) {
                return getUserFromHash(jedis.hgetAll(userKey));
            }
        }
        return null;
    }

    // Kullanıcıyı Email'e göre Redis'ten getirme
    public static User getUserByEmail(String email) {
        try (Jedis jedis = getJedis()) {
            String userKey = findUserKeyByEmail(email);
            if (userKey != null && jedis.exists(userKey)) {
                return getUserFromHash(jedis.hgetAll(userKey));
            }
        }
        return null;
    }

    // Kullanıcıyı Username'e göre Redis'ten getirme
    public static User getUserByUsername(String username) {
        try (Jedis jedis = getJedis()) {
            String userKey = findUserKeyByUsername(username);
            if (userKey != null && jedis.exists(userKey)) {
                return getUserFromHash(jedis.hgetAll(userKey));
            }
        }
        return null;
    }

    // Redis Hash'inden User nesnesi oluşturma
    private static User getUserFromHash(java.util.Map<String, String> hash) {
        User user = new User();
        user.setId(hash.get("id"));
        user.setUsername(hash.get("username"));
        user.setPassword(hash.get("password"));
        user.setEmail(hash.get("email"));
        user.setPhone(hash.get("phone"));
        user.setLastLoginIp(hash.get("lastLoginIp"));
        return user;
    }

    // Kullanıcıyı Email'e göre Redis key'ini bulma
    private static String findUserKeyByEmail(String email) {
        try (Jedis jedis = getJedis()) {
            for (String key : jedis.keys(USER_KEY_PREFIX + "*")) {
                if (getUserFromHash(jedis.hgetAll(key)).getEmail().equals(email)) {
                    return key;
                }
            }
        }
        return null;
    }

    // Kullanıcıyı Username'e göre Redis key'ini bulma
    private static String findUserKeyByUsername(String username) {
        try (Jedis jedis = getJedis()) {
            for (String key : jedis.keys(USER_KEY_PREFIX + "*")) {
                if (getUserFromHash(jedis.hgetAll(key)).getUsername().equals(username)) {
                    return key;
                }
            }
        }
        return null;
    }
}
