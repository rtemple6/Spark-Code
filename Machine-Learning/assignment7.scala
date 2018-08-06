import org.apache.spark.mllib.linalg._
import org.apache.spark.mllib.regression._
import org.apache.spark.mllib.evaluation._
import org.apache.spark.mllib.tree._
import org.apache.spark.mllib.tree.model._
import org.apache.spark.mllib.rdd._
import org.apache.spark.sql.DataFrame

val file = sc.textFile("/home/temp/sparkcode/Machine-Learning/Input/Hungarian.txt")
val lines = file.flatMap(line=>line.split("\n"))

val Data = lines.map{x=>
    val values = x.split(',').map(x=> if (x == "?") {0} else { x.toDouble})
    val featureVector = Vectors.dense(values.init)
    val target = values.last
    LabeledPoint(target, featureVector)
}.cache()
 Data.collect.foreach(println)

val categoricalFeatureInfo = Map[Int, Int]((1,2),(5,2),(8,2))
val model = DecisionTree.trainClassifier(Data, 2, categoricalFeatureInfo, "gini", 5, 100)

val testData = Vectors.dense(58,0,2,180,393,0,0,110,1,1,2,0,7,1)
val prediction = model.predict(testData)
println("Model Tree:\n " + model.toDebugString)