

package com.prodevans.it.SparkPorgrams

import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.SparkConf

object WordCount 
{
  def main(args:Array[String])
  {
    
      val conf= new SparkConf().setAppName("WordCount and Character Count").setMaster("local")
      val sc=new SparkContext(conf)
      
      val key_value1 = sc.textFile("C:/Users/vip/Desktop/abc.txt")
                          .flatMap( line => line.split(" "))
                          .map( word => (word,1)).reduceByKey(_+_)
                          
     val key_value2 = sc.textFile("C:/Users/vip/Desktop/abc.txt")
                          .flatMap( line => line.split(" ")
                          .flatMap( word => for( c <- word )
                                        yield(c,1)
                                        )
                                  ).reduceByKey(_+_)
                                  
     key_value1.foreach(println)
     key_value2.foreach(println)
     sc.stop()
    
                                      
  }
}