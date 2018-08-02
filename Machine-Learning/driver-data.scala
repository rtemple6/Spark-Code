import org.apache.spark.mllib.linalg._
import org.apache.spark.mllib.regression._
import org.apache.spark.mllib.evaluation._
import org.apache.spark.mllib.tree._
import org.apache.spark.mllib.tree.model._
import org.apache.spark.mllib.rdd._

// val denseVector = Vectors.dense(10,1,4,1,0,0)
val file = sc.textFile("/home/temp/sparkcode/Machine-Learning/Input/input.csv")
//Get the first line
val header = file.first()
//Get data minus the first line
val rawData = file.filter(x=> x!=header )