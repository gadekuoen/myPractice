import scala.io.Source

def widthOfLength(s: String) = s.length.toString.length

if (args.length > 0){
  val lines = Source.fromFile(args(0)).getLines().toList

  val longestLine = lines.reduceLeft((a,b) => if(a.length > b.length) a else b)
  val maxWidth = widthOfLength(longestLine)

  println("maxWidth=" + maxWidth)
  println("widthOfLength=" + widthOfLength(lines(0)))

  for ( line <- lines){
    val namespace = maxWidth - widthOfLength(line)
    val padding = " " * namespace
    println(padding + line.length + " | " + line)
  }
} else {
  Console.err.println("Please enter filename.")
}


