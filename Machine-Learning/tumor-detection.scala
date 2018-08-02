import org.apache.spark.mllib.clustering.{KMeans, KMeansModel}
import org.apache.spark.mllib.linalg.Vectors

val file = sc.textFile("/home/temp/sparkcode/Machine-Learning/Input/breast-cancer-data.txt")
val header = file.first()
val rawData = file.filter(x=> x!=header)
val token = rawData.map(x=>x.split(","))
val keyval = token.map(x=>( x(0), x(1) ))
reduced.collect.foreach(println)

val parsedData = rawData.map(x=>Vectors.dense(x.split(",").drop(2).map(x=>x.toDouble))).cache()
// parsedData.collect.foreach(println)

val kmeans = new KMeans()
//Set number of clusters
kmeans.setK(2)
val model = kmeans.run(parsedData)

model.predict(parsedData).foreach(println)

val test = model.predict(Vectors.dense(12,24.54,47.92,181,0.05263,0.04362,0,0,0.1587,0.05884,0.3857,1.428,2.548,19.15,0.007189,0.00466,0,0,0.02676,0.002783,9.456,30.37,59.16,268.6,0.08996,0.06444,0,0,0.2871,0.07039))

//Get first prediction by the datas
//Get patient ids with false predictions
//Assume 1 is belignent, count how many u have
//Assume 0 is belignent, find percentage of begign
//.map(x=>(x(m|b), model.predict(Vectors.dense())