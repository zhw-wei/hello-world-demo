package com.hello.demo.spark

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 *
 * @author zhw
 * @date 2022/6/7 23:51
 */
object WordCount2 {

  def main(args: Array[String]): Unit = {
    //application
    //spark 框架
    /**
     * 执行步骤：
     * 1。 建立和spark框架的连接
     * 2。 执行业务操作
     * 3。 关闭连接
     */

    //建立和spark框架的连接
    val sparkConf: SparkConf = new SparkConf().setMaster("local").setAppName("WordCount")
    val context: SparkContext = new SparkContext(sparkConf)

    //执行业务操作
    //1. 读取文件，获取一行一行的数据
    val lines: RDD[String] = context.textFile("demo-spark/data")

    //2. 将一行数据拆分，形成一个一个的单词（分词）
    val words: RDD[String] = lines.flatMap(_.split(" "))

    val wordToOne: RDD[(String, Int)] = words.map(word => (word, 1))

    //spark提供了更多的功能，可以将分组和聚合使用同一个方法实现
    //reducebyKey：相同key的数据，可以对value进行reduce聚合
    val wordCount: RDD[(String, Int)] = wordToOne.reduceByKey(_ + _)

    //5. 将转换结果采集到控制台打印出来
    val array: Array[(String, Int)] = wordCount.collect()
    array.foreach(println)

    //关闭连接
    context.stop()
  }

}
