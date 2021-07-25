import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}
import org.junit.Test

class WordCount extends Serializable {

  /**
    * 访问hdfs
    */
  @Test
  def wordCount(): Unit = {
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

  /**
    * 访问本地文件
    */
  @Test
  def wordCount1():Unit = {
    //1. 创建 Spark Context
    val conf = new SparkConf().setAppName("ip_ana").setMaster("local[2]")
    val sc:SparkContext = new SparkContext(conf)

    //2. 读取文件并计算词频
    val source:RDD[String] = sc.textFile("dataset/wordcount.txt", 2)
    val words:RDD[String] = source.flatMap{ line => line.split(" ")}
    val wordsTuple:RDD[(String, Int)] = words.map{ word => (word, 1)}
    val wordsCount:RDD[(String, Int)] = wordsTuple.reduceByKey{ (x,y) => x+y }

    //3. 查看执行结果
    wordsCount.collect.foreach(item => println(item))
  }


  /**
    * Map算子
    */
  @Test
  def mapCount():Unit = {
    val conf = new SparkConf().setAppName("ip_ana").setMaster("local[2]")
    val sc:SparkContext = new SparkContext(conf)

    sc.parallelize(Seq(1,2,3)).map(num => num * 10)
      .collect()
      .foreach(item => println(item))
  }

  /**
    * FlatMap算子
    */
  @Test
  def flatMapCount():Unit = {
    val conf = new SparkConf().setAppName("ip_anan").setMaster("local[2]")
    val sc:SparkContext = new SparkContext(conf)

    sc.parallelize(Seq("Hello lily", "Hello lucy", "Hello tim"))
      .flatMap(line => line.split(" "))
      .collect()
      .foreach(item => println(item))
  }


  /**
    * ReduceByKey算子
    */
  @Test
  def reduceByKeyCount:Unit = {
    val conf = new SparkConf().setAppName("ip_ana").setMaster("local[2]")
    val sc:SparkContext = new SparkContext(conf)

    sc.parallelize(Seq(("a",1),("a",1),("b",1)))
      .reduceByKey((curr, agg) => curr+agg)
      .collect()
      .foreach(item => println(item))
  }

}
