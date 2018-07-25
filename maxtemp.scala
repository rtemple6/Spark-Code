//Open File create RDD
val file = sc.textFile("/home/temp/input/temps.txt")
//Create Key/Value pair
val token = file.map(x=>x.split(" "))
val keyval = token.map(x=>(x(0),x(1)))
//Reduce by Key with max value
val maxtmp = keyval.reduceByKey{case(a,b)=> if (a>b) {a} else {b}}
//Print max value for each month
maxtmp.collect.foreach(println)

//Print the max temp from all values
val maxVal = maxtmp.values.max
val filter = keyval.filter{case(a,b)=> b==maxVal}
filter.collect.foreach(println)