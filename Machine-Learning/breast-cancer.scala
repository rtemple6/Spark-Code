import org.apache.spark.mllib.clustering.{KMeans, KMeansModel}
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.mllib.linalg._
import org.apache.spark.mllib.regression._
import org.apache.spark.mllib.evaluation._
import org.apache.spark.mllib.tree._
import org.apache.spark.mllib.tree.model._
import org.apache.spark.mllib.rdd._

val file = sc.textFile("/home/temp/sparkcode/Machine-Learning/Input/breast-cancer-data.txt")
val rawData = file.map(x=>x.split(","))
val parsedData = rawData.map(x=>Vectors.dense(x.drop(2).map(x=> x.toDouble)))

val kmeans = new KMeans()
kmeans.setK(2)
val model = kmeans.run(parsedData)

//Generate id and (B|M) and prediction
val cluster = rawData.map(x=>(x(0).toString,
                                 x(1).toString,
                                 model.predict(Vectors.dense(x.drop(2).map(x=> x.toDouble)))))

//Correct percentage
val malignantOne = cluster.map(x=>(x._1, if (x._2 == "M") {1} else {0}, x._3))
val falseprediction = malignantOne.filter(x=> x._2 != x._3)
falseprediction.collect.foreach(println)

//Wrong percentage
val malignantZero = cluster.map(x=>(x._1, if (x._2 == "B") {1} else {0}, x._3))
val falseprediction2 = malignantZero.filter(x=> x._2 != x._3)
falseprediction2.collect.foreach(println)