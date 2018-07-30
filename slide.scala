//sc is spark context
val textFile = sc.textFile("/home/temp/sparkcode/input/id-name-age-url.txt")
//flatMap is like tokenizer
val lines = textFile.flatMap(line=>line.split("\n"))
val mapped = lines.flatMap(token=>token.split(", "))
val xx = mapped.map(token=> if (token == "Ryan") {(token, 1)})
// val split = mapped.map(word=>(word, 1))
// val split = mapped.map(word=>(word, 1))
xx.collect.foreach(println)
//Mapping each word with the value of 1. Reduce adds with previous value, like an accumulator.
// val counts = split.map(word=>(word, 1)).reduceByKey{case (x,y)=>x+y}
//counts.saveAsTextFile("/home/temp/sparkoutput/wordfreq")
// counts.collect.foreach(println)