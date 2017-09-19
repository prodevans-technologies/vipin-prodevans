package com.prodevans.scala


import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.SparkConf


/*
 * EmployeeManager.csv[id,managerName]
 * EmployeeName.csv[id,name]
 * EmployeeSalary.csv[id,salary]
 * 
 * Using spark generate a joined output link below and save a text file (seperated by coma)
 * for final distribution and final output must be stored
 * 
 */

object JoinOperation 
{

  def main(args: Array[String])
  {
    System.setProperty("hadoop.home.dir", "C:/Users/vip/Documents/GitHub/Hadoop/winutils");
    
    val conf= new SparkConf().setAppName("Join Operation").setMaster("local").set("spark.hadoop.validateOutputSpecs","false")
    val sc=new SparkContext(conf)
    
    val manager = sc.textFile("C:/Users/vip/Desktop/Hadoop_Data/EmployeeManager.csv")
    val managerPairRDD= manager.map( x=> (x.split(",")(0), x.split(",")(1) ) )
    println(managerPairRDD.count())
    
    
    val employeeName = sc.textFile("C:/Users/vip/Desktop/Hadoop_Data/EmployeeName.csv")
    val employeePairRDD= employeeName.map( x=> (x.split(",")(0), x.split(",")(1) ) )
    println(employeePairRDD.count())
    
    
    val employeeSalary = sc.textFile("C:/Users/vip/Desktop/Hadoop_Data/EmployeeSalary.csv")
    val salaryPairRDD= employeeSalary.map( x=> (x.split(",")(0), x.split(",")(1) ) )
    println(salaryPairRDD.count())
    
    val joinedRDD = managerPairRDD.join(employeePairRDD).join(salaryPairRDD)
    joinedRDD.take(10).foreach(println)
    
    
    val resultRDD = joinedRDD.map( record => record._1 + ","+ record._2._1._2 +","+record._2._1._1 +","+record._2._2  )
    resultRDD.take(10).foreach(println)
    
    
    //val myFile = sc.textFile("file.txt")
    //val finalRdd = doStuff(myFile)
    //finalRdd.coalesce(1).saveAsTextFile("newfile")
    
    resultRDD.saveAsTextFile("C:/Users/vip/Desktop/Hadoop_Data/join")
    
    
    
  }
  
}