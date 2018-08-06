import org.apache.spark.mllib.linalg._
import org.apache.spark.mllib.regression._
import org.apache.spark.mllib.evaluation._
import org.apache.spark.mllib.tree._
import org.apache.spark.mllib.tree.model._
import org.apache.spark.mllib.rdd._
import org.apache.spark.sql.DataFrame

// val denseVector = Vectors.dense(10,1,4,1,0,0)
val file = sc.textFile("/home/temp/sparkcode/Machine-Learning/Input/input.csv")
//Get the first line
val header = file.first()
//Get data minus the first line
val rawData = file.filter(x=> x!=header )

//Try to make this multiple line map
val Data = rawData.map{x=>
    val values = x.split(',').map(x=> x.toDouble)
    //The values of each row.
    //Vectors is method, will attrs in LabeledPoint()
    //Having prediction in last column is good design
    val featureVector = Vectors.dense(values.init)
    // println(featureVector)
    //Last column for
    val target = values.last
    // println(target)
    LabeledPoint(target, featureVector)
}
// Data.collect.foreach(println)

val dataToDF = Data.toDF
dataToDF.show

//(what are categories associated with ur attrs eg. Currently Emplored (Y or N = 2), Education Label(Null, BS, MS, PHD = 4))
//(Index, # of choices)
//example = Currently Employed : index = 1, Yes or No = 2
val categoricalFeatureInfo = Map[Int, Int]((1,2),(3,4),(4,2),(5,2))
//Params (Data, Input Values( Yes or No), categoricalFeatureInfo, "gini"(statistical index: search google), depth for tree, number of bins(seperate trees we create))
val model = DecisionTree.trainClassifier(Data,2, categoricalFeatureInfo, "gini", 5, 100)
//What is likelyhood of these params that this candiate will be hired
val testData = Vectors.dense(10, 1, 3, 1, 0, 0)
val prediction = model.predict(testData)
println("Model Tree:\n " + model.toDebugString)