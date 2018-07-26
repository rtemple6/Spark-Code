//PART A
//Get data from the textfile
val file = sc.textFile("/home/temp/sparkcode/input/fruits_prices.txt")
val token = file.map(x=>x.split(","))
//Create map
val keyval = token.map(x=>( x(0), x(1).toFloat ))
//Manipulate data to include a count
val mapred = keyval.mapValues(x=>(x.toFloat,1))
//Add all instances + accumulate score
val reduced = mapred.reduceByKey{case(a,b)=>(a._1+b._1, a._2+b._2)}
//Divide instance count by accumlated score
val average = reduced.mapValues(x=>(x._1/x._2))
average.collect.foreach(println)

//PART B
//Get data from the textfile
val file1 = sc.textFile("/home/temp/sparkcode/input/fruit_weight_prices.txt")
val token1 = file1.map(x=>x.split(","))
//Create map
val keyval1 = token1.map(x=>( x(0), (x(1).toFloat,x(2).toFloat) ))
// keyval1.collect.foreach(println)
//Manipulate data to include a count
val mapred1 = keyval1.mapValues(x=>(x,1))
// mapred1.collect.foreach(println)
//Add all instances + accumulate score
val reduced1 = mapred1.reduceByKey{case(a,b)=>(((a._1._1 + b._1._1), (a._1._2 + b._1._2)), a._2+b._2)}
// reduced1.collect.foreach(println)
//Divide instance count by accumlated score
val average1 = reduced1.mapValues(x=>(x._1._2/x._2))
average1.collect.foreach(println)

//PART C
val totalAverage = reduced1.mapValues(x=>((x._1._2/x._1._1)/x._2))
totalAverage.collect.foreach(println)
