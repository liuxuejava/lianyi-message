package com.lianyi;

        import redis.clients.jedis.HostAndPort;
        import redis.clients.jedis.Jedis;
        import redis.clients.jedis.JedisCluster;

        import java.util.HashSet;
        import java.util.Set;

/**
 * Created by Stu on 2018/8/17.
 */
public class TestCluster {
    public static void main(String[] args) {
        /*Set<HostAndPort> nodes = new HashSet<>();
        nodes.add(new HostAndPort("192.168.8.87",6379));
        nodes.add(new HostAndPort("192.168.8.26",6379));
        nodes.add(new HostAndPort("192.168.8.45",6379));
        nodes.add(new HostAndPort("192.168.8.97",6379));
        nodes.add(new HostAndPort("192.168.8.56",6379));
        nodes.add(new HostAndPort("192.168.8.110",6379));
        JedisCluster cluster=new JedisCluster(nodes);
        System.out.println(cluster.get("js"));
        cluster.set("today","2018-8-20");
        System.out.println(cluster.get("today"));
        System.out.println(cluster.ttl("today"));*/
        Jedis jedis=new Jedis("47.99.93.0",6379);
        /*jedis.auth("lianyi");*/
        System.out.println(jedis.ping());
        jedis.set("test","java");
        System.out.println(jedis.get("test"));
        System.out.println(jedis.ttl("test"));

    }
}
