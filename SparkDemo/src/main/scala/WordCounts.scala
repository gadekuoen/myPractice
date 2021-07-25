import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.rdd.RDD

object WordCounts {

  def main(args: Array[String]): Unit = {
    //1. 创建 Spark Context
    val conf = new SparkConf().setAppName("ip_ana").setMaster("local[2]")
    val sc:SparkContext = new SparkContext(conf)

    //2. 读取文件并计算词频
    val source:RDD[String] = sc.textFile("hdfs://node01:8020/dataset/wordcount.txt", 2)
    val words:RDD[String] = source.flatMap{ line => line.split(" ")}
    val wordsTuple:RDD[(String, Int)] = words.map{ word => (word, 1)}
    val wordsCount:RDD[(String, Int)] = wordsTuple.reduceByKey{ (x,y) => x+y }

    //3. 查看执行结果
    wordsCount.collect.foreach(item => println(item))
  }
}
