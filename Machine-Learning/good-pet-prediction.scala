//Ryan Temple
//Assignment 6
import org.apache.spark.mllib.linalg._
import org.apache.spark.mllib.regression._
import org.apache.spark.mllib.evaluation._
import org.apache.spark.mllib.tree._
import org.apache.spark.mllib.tree.model._
import org.apache.spark.mllib.rdd._

//Read from file
val file = sc.textFile("/home/temp/sparkcode/Machine-Learning/Input/pets.csv")
val header = file.first()
val rawData = file.filter(x=> x!=header )

//Map Data
val petData = rawData.map{x=>
    val values = x.split(',').map(x=> x.toDouble)
    val featureVector = Vectors.dense(values.init)
    val target = values.last
    LabeledPoint(target, featureVector)
}

//Train Model
val info = Map[Int, Int]((2,4))
val model = DecisionTree.trainClassifier(petData, 2, info, "gini", 5, 100)

//Predictions
val dog = Vectors.dense(15.1, 4, 0)
val prediction = model.predict(dog)
val dragon = Vectors.dense(122445, 4, 2)
val prediction2 = model.predict(dragon)
val butterfly = Vectors.dense(0.1, 2, 0)
val prediction3 = model.predict(butterfly)