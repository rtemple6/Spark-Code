val myseqc = sc.parallelize(Seq(("apple", 2.5),("orange", 3.0),("apple", 3.0),("orange", 3.2)))
//Manipulate data to include a count
val mapred = myseqc.mapValues(x=>(x,1))
//Add all instances + accumulate score
val reduced = mapred.reduceByKey{case(a,b)=>(a._1+b._1, a._2+b._2)}
//Divide instance count by accumlated score
val average = reduced.mapValues(x=>(x._1/x._2))
average.collect.foreach(println)

//---------
//input.txt
//---------
//stockname, yesterday_price, today_price 