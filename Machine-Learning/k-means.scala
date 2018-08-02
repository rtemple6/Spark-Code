import org.apache.spark.mllib.clustering.{KMeans, KMeansModel}
import org.apache.spark.mllib.linalg.Vectors

val file = sc.textFile("/home/temp/sparkcode/Machine-Learning/Input/driver_data.csv")
val header = file.first()
val rawData = file.filter(x=> x!=header)
val parsedData = rawData.map(x=>Vectors.dense(x.split(",").drop(1).map(x=>x.toDouble))).cache()
// parsedData.collect.foreach(println)

val kmeans = new KMeans()
//Set number of clusters
kmeans.setK(2)
//Pass RDD Vector
val model = kmeans.run(parsedData)
model.clusterCenters.foreach(println)
//Generate cluster number
model.predict(parsedData).foreach(println)
// model
val test = model.predict(Vectors.dense(70,25))
val test2 = model.predict(Vectors.dense(160,2))

println(test2)