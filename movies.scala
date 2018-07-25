//Open File create RDD
val file = sc.textFile("/home/temp/input/movies.txt")
//Create Key/Value pair
val token = file.map(x=>x.split(","))
val keyval = token.map(x=>(x(0),(x(1),x(2))))
//Reduce
val maxRating = keyval.reduceByKey{case(a,b)=> if(a._2>b._2){a} else {b}}
maxRating.collect.foreach(println)
//List the max movies with rating
val totalMax = maxRating.values.max
val filter = maxRating.filter{case(a,b)=> b._2 == totalMax._2}
filter.collect.foreach(println)
