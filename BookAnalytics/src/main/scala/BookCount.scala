import org.apache.spark._

/**
  * Created by manuel on 8/07/16.
  * launch: "BookCount hdfs://namenode:8020/user/bluetab/books/pg1.txt"
  * bin/spark-submit --class "BookCount" --num-executors 1 --executor-cores 1 target/bookanalytics_2.11-1.0.jar "hdfs://namenode:8020/user/bluetab/books/pg1.txt"
  */

object BookCount {

  def main (args: Array[String]) {

    if (args.length < 1) {
      System.err.println("Usage: BookCount <file>")
      System.exit(1)
    }

    val file = args(0)

    val sparkConf = new SparkConf().setAppName("BookCount")
    val sc = new SparkContext(sparkConf)
    val textFile = sc.textFile(file)
    val counts = textFile.flatMap(line => line.split(" "))
                         .map(word => (word, 1))
                         .reduceByKey(_ + _)
                         .sortBy(-_._2)
    println("Palabras mas repetidas:")
    counts.take(50).foreach(println)
    println("Fin")
  }

}
