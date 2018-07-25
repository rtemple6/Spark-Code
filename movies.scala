//Open File create RDD
val file = sc.textFile("/home/temp/input/movies.txt")
//Create Key/Value pair
val token = file.map(x=>x.split(","))
val keyval = token.map(x=>(x(0),(x(1),x(2))))
keyval.collect.foreach(println)