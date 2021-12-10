package com.asher.utils.es

import io.searchbox.client.config.HttpClientConfig
import io.searchbox.client.{JestClient, JestClientFactory}
import io.searchbox.core.Index

/**
 * @author Asher Wu
 * @date 2021/12/9 21:24
 *
 *       1.TransportClient 为代表的ES原生客户端，不能执行原生dsl语句必须使用它的Java api方法。
 *       2.以Rest ApI为主的missing client，最典型的就是jest,支持DSL语句拼成的字符串，
 *       直接传给服务端，然后返回json字符串再解析。
 */
object MyESUtil {
  //声明Jest客户端工厂
  private var jestFactory: JestClientFactory = null

  //提供获取Jest客户端的方法
  def getJestClient(): JestClient = {
    if (jestFactory == null) {
      //创建Jest客户端工厂对象
      build()
    }
    jestFactory.getObject
  }

  def build(): Unit = {
    jestFactory = new JestClientFactory
    jestFactory.setHttpClientConfig(new HttpClientConfig
    .Builder("http://hadoop101:9200")
      .multiThreaded(true)
      .maxTotalConnection(20)
      .connTimeout(10000)
      .readTimeout(1000).build())
  }

  //向ES中插入单条数据  方式1  将插入文档的数组以json的形式直接传递
  def putIndex1(): Unit = {
    //获取客户端连接
    val jestClient: JestClient = getJestClient()
    //定义执行的source
    var source: String =
      """
        |{
        |  "id":200,
        |  "name":"operation meigong river",
        |  "doubanScore":8.0,
        |  "actorList":[
        |	    {"id":3,"name":"zhang han yu"}
        |	  ]
        |}
      """.stripMargin
    //创建插入类 Index   Builder中的参数表示要插入到索引中的文档，底层会转换Json格式的字符串，所以也可以将文档封装为样例类对象
    val index: Index = new Index.Builder(source)
      .index("movie_index_1")
      .`type`("movie")
      .id("1")
      .build()
    //通过客户端对象操作ES     execute参数为Action类型，Index是Action接口的实现类
    jestClient.execute(index)
    //关闭连接
    jestClient.close()
  }

  // 测试
  def main(args: Array[String]): Unit = {
    //测试查看效果
    MyESUtil.putIndex1();
  }

}
