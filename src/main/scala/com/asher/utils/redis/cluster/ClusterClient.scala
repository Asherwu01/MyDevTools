package com.asher.utils.redis.cluster

import java.util

import redis.clients.jedis.{HostAndPort, JedisCluster}

/**
 * @Author Asher Wu
 * @Date 2020/8/17 13:09
 * @Version 1.0
 */
object ClusterClient {
  def main(args: Array[String]): Unit = {
    val set = new util.HashSet[HostAndPort]()
    set.add(new HostAndPort("hadoop102", 6379))
    set.add(new HostAndPort("hadoop102", 6380))
    set.add(new HostAndPort("hadoop102", 6381))
    set.add(new HostAndPort("hadoop102", 7379))
    set.add(new HostAndPort("hadoop102", 7380))
    set.add(new HostAndPort("hadoop102", 7381))
    val cluster = new JedisCluster(set)
    cluster.set("aaaa", "bbbb")
    cluster.mset("{k}a", "b", "{k}a1", "b1")

    cluster.close()
  }
}
