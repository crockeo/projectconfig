package org.crockeo.projectconfig

class Config(chMap: Map[String, Char  ],
             shMap: Map[String, Short ],
             inMap: Map[String, Int   ],
             loMap: Map[String, Long  ],
             flMap: Map[String, Float ],
             doMap: Map[String, Double],
             stMap: Map[String, String]) {
  // Converting the config to a parseable string
  override def toString: String = {
    def mapToString[A, B](m: Map[A, B]): String =
      if (m.isEmpty) "|"
      else           m.map(a => a._1 + "=" + a._2).reduceLeft(_ + "," + _)
    
    List(
      mapToString(chMap),
      mapToString(shMap),
      mapToString(inMap),
      mapToString(loMap),
      mapToString(flMap),
      mapToString(doMap),
      mapToString(stMap)
    ).reduceLeft(_ + "\n" + _)
  }
  
  // Accessing config values
  def charFlag  (s: String): Char   = chMap(s)
  def shortFlag (s: String): Short  = shMap(s)
  def intFlag   (s: String): Int    = inMap(s)
  def longFlag  (s: String): Long   = loMap(s)
  def floatFlag (s: String): Float  = flMap(s)
  def doubleFlag(s: String): Double = doMap(s)
  def stringFlag(s: String): String = stMap(s)
  
  // Pushing new values
  def pushPair(p: (String, Any)): Config = {
    def pushChar  (p: (String, Char  )): Config = new Config(chMap + p, shMap    , inMap    , loMap    , flMap    , doMap    , stMap    )
    def pushShort (p: (String, Short )): Config = new Config(chMap    , shMap + p, inMap    , loMap    , flMap    , doMap    , stMap    )
    def pushInt   (p: (String, Int   )): Config = new Config(chMap    , shMap    , inMap + p, loMap    , flMap    , doMap    , stMap    )
    def pushLong  (p: (String, Long  )): Config = new Config(chMap    , shMap    , inMap    , loMap + p, flMap    , doMap    , stMap    )
    def pushFloat (p: (String, Float )): Config = new Config(chMap    , shMap    , inMap    , loMap    , flMap + p, doMap    , stMap    )
    def pushDouble(p: (String, Double)): Config = new Config(chMap    , shMap    , inMap    , loMap    , flMap    , doMap + p, stMap    )
    def pushString(p: (String, String)): Config = new Config(chMap    , shMap    , inMap    , loMap    , flMap    , doMap    , stMap + p)
    
    p match {
      case (key, v) =>
             if (v.isInstanceOf[Char]  ) pushChar  (key, v.asInstanceOf[Char]  )
        else if (v.isInstanceOf[Short] ) pushShort (key, v.asInstanceOf[Short] )
        else if (v.isInstanceOf[Int]   ) pushInt   (key, v.asInstanceOf[Int]   )
        else if (v.isInstanceOf[Long]  ) pushLong  (key, v.asInstanceOf[Long]  )
        else if (v.isInstanceOf[Float] ) pushFloat (key, v.asInstanceOf[Float] )
        else if (v.isInstanceOf[Double]) pushDouble(key, v.asInstanceOf[Double])
        else if (v.isInstanceOf[String]) pushString(key, v.asInstanceOf[String])
        else                             this
      case default => this
    }
  }
}

object Config {
  val emptyConfig: Config =
    new Config(
      Map(),
      Map(),
      Map(),
      Map(),
      Map(),
      Map(),
      Map()
    )
  
  // Saving a config to the hard drive
  def saveConfig(fp: String, cfg: Config): Unit =
    IO.writeFile(fp, cfg.toString)
    
  // Reading a config from the hard drive
  def loadConfig(fp: String): Option[Config] =
    IO.readFile(fp).map(Parser.parse)
}