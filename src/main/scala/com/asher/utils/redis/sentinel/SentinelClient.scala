package com.asher.utils.redis.sentinel

import java.util

import org.apache.commons.pool2.impl.GenericObjectPoolConfig
import redis.clients.jedis.{Jedis, JedisSentinelPool}

/**
 * @Author Asher Wu
 * @Date 2020/8/15 15:51
 * @Version 1.0
 */
object SentinelClient {
  def main(args: Array[String]): Unit = {
    val set = new util.HashSet[String]()
    set.add("hadoop102:26379")

    val config = new GenericObjectPoolConfig()
    val pool = new JedisSentinelPool("mymaster", set, config)

    val client: Jedis = pool.getResource

    println(client.get("a"))
    client.set("aa", "bb")
    pool.close()
  }
}
