package org.crockeo.projectconfig

protected object Parser {
  def parse(str: String): Config = {
    def pushAll(cfg: Config, ps: List[(String, Any)]): Config =
      ps.foldLeft(cfg)((a, b) => a.pushPair(b))
    
    val lines: List[List[(String, String)]] =
      str.split("\n").map(l => if (l == "|") List()
                               else         l.split(",").map(b => (b.split("=")(0), b.split("=")(1))).toList).toList
      
    (for (n <- 0 until lines.length)
     yield n match {
       case 0 => lines(n).map(a => (a._1, a._2.charAt(0)))
       case 1 => lines(n).map(a => (a._1, a._2.toShort))
       case 2 => lines(n).map(a => (a._1, a._2.toInt))
       case 3 => lines(n).map(a => (a._1, a._2.toLong))
       case 4 => lines(n).map(a => (a._1, a._2.toFloat))
       case 5 => lines(n).map(a => (a._1, a._2.toDouble))
       case 6 => lines(n)
     }).toList.foldLeft(Config.emptyConfig)(pushAll(_, _))
  }
}