//sc is spark context
val textFile = sc.textFile("/home/temp/input/input.txt")
//flatMap is like tokenizer
val words = textFile.flatMap(line=>line.split(" "))
//Mapping each word with the value of 1. Reduce adds with previous value, like an accumulator.
val counts = words.map(word=>(word, 1)).reduceByKey{case (x,y)=>x+y}
//counts.saveAsTextFile("/home/temp/sparkoutput/wordfreq")
counts.collect.foreach(println)