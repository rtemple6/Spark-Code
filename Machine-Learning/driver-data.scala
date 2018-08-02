import org.apache.spark.mllib.clustering.{KMeans, KMeansModel}
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.mllib.linalg._
import org.apache.spark.mllib.regression._
import org.apache.spark.mllib.evaluation._
import org.apache.spark.mllib.tree._
import org.apache.spark.mllib.tree.model._
import org.apache.spark.mllib.rdd._

// val denseVector = Vectors.dense(10,1,4,1,0,0)
val file = sc.textFile("/home/temp/sparkcode/Machine-Learning/Input/driver_data.csv")
//Get the first line
val header = file.first()
//Get data minus the first line
val filterData = file.filter(x=> x!=header )

// val parsedData = filterData.map(s=>Vectors.dense(s.split(",").drop(1).map(x=>x.toDouble)))
val splitData = filterData.map(x=>x.split(","))
val rawData = splitData.map(x=>(x(0), x(1).toDouble, x(2).toDouble))

//From Rawdata take only second and third column
val parsedData = rawData.map(x=>Vectors.dense(x._2, x._3)).cache()

val kmeans = new KMeans()
kmeans.setK(2)

val model = kmeans.run(parsedData)

//We want to see ID/Prediction
//Parse the dense vector for each row
// val cluster = model.predict(parsedData)
val cluster = rawData.map(x=>(x._1, model.predict(Vectors.dense(x._2, x._3))))
cluster.collect.foreach(println)

val testData = model.predict(Vectors.dense(25, 70))