package liujun.manager.redis;

import redis.clients.jedis.Jedis;

public class RedisManager {

    public static void main(String[] args) {
        int result = -1;
        try {
            Jedis jedis = new Jedis("192.168.75.1", 6379);
//            Jedis jedis = new Jedis("192.168.0.103", 6379);

            String ping = jedis.ping();
            System.out.println(ping);
            if (ping.equalsIgnoreCase("PONG")) {
                System.out.println("redis缓存有效！" + ping);
                result = 0;
            }
        } catch (Exception e) {
            System.out.println("redis缓存失败！");
            System.out.println(e.getMessage());
            result = 1;
        }
        System.out.println(result);
    }
}
