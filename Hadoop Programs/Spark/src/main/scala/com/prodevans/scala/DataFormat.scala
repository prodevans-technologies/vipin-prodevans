package com.prodevans.scala


import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.SparkConf
import org.apache.spark.sql.SQLContext

object DataFormat 
{
  
  def main(args: Array[String])
  {
  
    System.setProperty("hadoop.home.dir", "C:/Users/vip/Documents/GitHub/Hadoop/winutils");
    
    val conf= new SparkConf().setAppName("Join Operation").setMaster("local").set("spark.hadoop.validateOutputSpecs","false")
    val sc=new SparkContext(conf)
    
    val rawRDD= sc.textFile("C:/Users/vip/Desktop/Hadoop_Data/emp_data.csv",2)
    val rawRDD2= sc.textFile("C:/Users/vip/Desktop/Hadoop_Data/dept.csv",2)
    
    val rawRDDEmployees = rawRDD.map(record => record.split(",",-1))
                                .map( x=> CaseClassEmployees 
                                          (
                                                Integer.parseInt(x(0).toString()),
                                                x(1),
                                                x(2),
                                                x(3),
                                                Integer.parseInt(x(4).toString()),
                                                x(5)
                                          )
                                     )
    

     val rawRDDDept=rawRDD2.map(record => record.split(",",-1))
                           .map( x=> CaseClassDept 
                                     (
                                         x(0),
                                         x(1)
                                     )
                               
                               )
                                                                         
    val sqlContext=new SQLContext(sc)
    
    import sqlContext.implicits._
    
    val rawDF=rawRDDEmployees.toDF()
    
    val rawDFDept=rawRDDDept.toDF()
    
    rawDF.groupBy("emp_dept").count().show()
    
    rawDF.registerTempTable("Employees")
    
    rawDFDept.registerTempTable("Departments")
    rawDFDept.show()
    
    val joinQuery = sqlContext.sql("select e.emp_name, e.emp_dsgntn, e.emp_sal, d.emp_dept from Employees e INNER JOIN Departments d where e.emp_dept=d.dept_id")
 
    joinQuery.show()
    
    //val querDF=sqlContext.sql("select emp_name,emp_sal from Employees") 
    //val querDF1=sqlContext.sql("select emp_dept,max(emp_sal) from Employees group by emp_dept")                                 
    
    //querDF1.show()                                 
  }

}