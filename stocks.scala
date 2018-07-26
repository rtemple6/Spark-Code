val file = sc.textFile("/home/temp/sparkcode/input/stocks.txt")
val token = file.map(x=>x.split(","))
val keyval = token.map(x=>(x(0),(x(1),x(2))))
val reduced = keyval.reduceByKey{case(a,b)=>(a._1+b._1, a._2+b._2)}
reduced.collect.foreach(println)