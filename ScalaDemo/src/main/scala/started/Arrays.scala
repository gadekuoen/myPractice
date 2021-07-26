var greetStrings = new Array[String](3)

greetStrings(0) = "Hello"
greetStrings(1) = ", "
greetStrings(2) = "world!\n"

for(i <- 0 to 2){
  print(greetStrings(i))
}

greetStrings.update(0,"Wu")
greetStrings.update(1," ")
greetStrings.update(2,"Jiang!\n")

for(i <- 0 to 2){
  print(greetStrings(i))
}