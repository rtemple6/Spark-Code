val file = sc.textFile("/home/temp/sparkcode/input/stocks.txt")
val token = file.map(x=>x.split(","))
val keyval = token.map(x=>( x(0), (x(1).toFloat,x(2).toFloat) ))
val reduced = keyval.reduceByKey{case(a,b)=>(a._1+b._1, a._2+b._2)}
reduced.collect.foreach(println)

val file1 = sc.textFile("/home/temp/sparkcode/input/stocks1.txt")
val token1 = file1.map(x=>x.split(","))
val keyval1 = token1.map(x=>( x(0), x(1).toFloat ))
val reduced1 = keyval1.reduceByKey{case(a,b)=>a+b}
reduced1.collect.foreach(println)