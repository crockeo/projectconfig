package org.crockeo.projectconfig

class Config(chMap: Map[String, Char  ],
             shMap: Map[String, Short ],
             inMap: Map[String, Int   ],
             loMap: Map[String, Long  ],
             flMap: Map[String, Float ], 
             doMap: Map[String, Double],
             stMap: Map[String, String]) {
  // Converting the config to a parseable string
  override def toString: String = ??? // TODO: Implement toString function
  
  // Accessing config values
  def charFlag  (s: String): Char   = chMap(s)
  def shortFlag (s: String): Short  = shMap(s)
  def intFlag   (s: String): Int    = inMap(s)
  def longFlag  (s: String): Long   = loMap(s)
  def floatFlag (s: String): Float  = flMap(s)
  def doubleFlag(s: String): Double = doMap(s)
  def stringFlag(s: String): String = stMap(s)
  
  // Pushing new values
  def pushPair(p: (String, Char  )): Config = new Config(chMap + p, shMap    , inMap    , loMap    , flMap    , doMap    , stMap    )
  def pushPair(p: (String, Short )): Config = new Config(chMap    , shMap + p, inMap    , loMap    , flMap    , doMap    , stMap    )
  def pushPair(p: (String, Int   )): Config = new Config(chMap    , shMap    , inMap + p, loMap    , flMap    , doMap    , stMap    )
  def pushPair(p: (String, Long  )): Config = new Config(chMap    , shMap    , inMap    , loMap + p, flMap    , doMap    , stMap    )
  def pushPair(p: (String, Float )): Config = new Config(chMap    , shMap    , inMap    , loMap    , flMap + p, doMap    , stMap    )
  def pushPair(p: (String, Double)): Config = new Config(chMap    , shMap    , inMap    , loMap    , flMap    , doMap + p, stMap    )
  def pushPair(p: (String, String)): Config = new Config(chMap    , shMap    , inMap    , loMap    , flMap    , doMap    , stMap + p)
}

object Config {
  private val emptyConfig: Config = ???
  
  private def parse(s: String): Config = {
    def parseIter(s: Iterable[String], cfg: Config): Config = {
      def parsePair[T](flag: String, value: String): (String, T) =
        flag -> value.asInstanceOf[T]
        
      if (s.isEmpty) cfg
      else           ???
    }
    
    parseIter(s.split("\n"), emptyConfig)
  }
  
  def saveConfig(fp: String, cfg: Config): Unit =
    ???
  def loadConfig(fp: String): Option[Config] =
    ???
}