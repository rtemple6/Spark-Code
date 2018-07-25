//Open File create RDD
val file = sc.textFile("/home/temp/input/movies.txt")
//Create Key/Value pair
val token = file.map(x=>x.split(","))
val keyval = token.map(x=>(x(0),(x(1),x(2))))
//Reduce
val maxRating = keyval.reduceByKey{case(a,b)=> if(a._2>b._2){a} else {b}}
//Display top movie for each user
maxRating.collect.foreach(println)

//Create another Key/Value pair
val movieRatingList = token.map(x=>(x(1), x(2)))
//Get max rating for new list
val highestRatedValue = movieRatingList.values.max
//Filter list for all values w/ highestRatedValue
val filter = movieRatingList.filter{case(key,value)=> value==highestRatedValue}
//Display top movies for all total list
filter.collect.foreach(println)
