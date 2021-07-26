var addressSet = Set("Beijing","Hangzhou")
addressSet += "Shanghai"
//println(addressSet.contains("Hangzhou"))
//println(addressSet.contains("guangzhou"))
println(addressSet)

import scala.collection.mutable.Set

var movieSet = Set("Sichuan","HuNan")
movieSet += "XinJiang"
println(movieSet)

import scala.collection.mutable.HashSet

var hashSet = HashSet("Wujiang", "Dashen")
println(hashSet + " 是真的")

import scala.collection.mutable.Map

var treasureMap = Map[Int, String]()
treasureMap += (1 -> "Go to island")
treasureMap += (2 -> "Find big X on ground")
treasureMap += (3 -> "Dig. ")
println(treasureMap(2))
