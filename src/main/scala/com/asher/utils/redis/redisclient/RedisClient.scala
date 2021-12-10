package com.asher.utils.redis.redisclient

import redis.clients.jedis.Jedis
/**
 * @Author Asher Wu
 * @Date 2020/8/14 19:45
 * @Version 1.0
 */
object RedisClient {
  def main(args: Array[String]): Unit = {
    // 1创建客户端
    val client = new Jedis("hadoop101", 6379)

    // 2客户端连接redis服务器
    // 2.1操作字符串
    /*client.set("k1","v1")
    println(client.get("k1"))
    client.mset("k2","v2","k3","v3")
    client.setex("k4",10,"v4")

    val list = client.mget("k1", "k2", "k3", "k4")
    for(e <- list.asScala){
      println(e)
    }*/

    // 2.2操作list
    /*client.rpush("list","a","b","c","d")
    val list = client.lrange("list", 0, -1)
    list.forEach(println)*/

    // 2.3操作set
    /*client.sadd("set1","a","b","c")
    val set = client.smembers("set1")
    set.forEach(println)*/

    // 2.4操作hash
    /*client.flushAll()
    client.hset("h1","f1","v1")
    val map = client.hgetAll("h1")
    println(map)*/

    // 2.5操作zset
    client.flushAll()
    client.zadd("z1",10,"a")
    val set = client.zrange("z1", 0, -1)
    println(set)

    // 关闭连接
    client.close()
  }
}
