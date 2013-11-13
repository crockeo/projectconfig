package org.crockeo.projectconfig

import scala.io.Source
import java.io._

protected object IO {
  // Writing a file to the hard drive
  def writeFile(fp: String, str: String): Unit = {
    val bf = new BufferedWriter(new FileWriter(new File(fp)))
    bf.write(str)
    bf.close
  }
  
  // Reading a file from the hard drive
  def readFile(fp: String): Option[String] = {
    val f = new File(fp)
    if (f.exists) Some(Source.fromFile(f).mkString)
    else          None
  }
}