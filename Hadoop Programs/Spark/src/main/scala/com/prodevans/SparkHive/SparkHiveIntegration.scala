package com.prodevans.SparkHive

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.annotation.Experimental
import org.apache.spark.sql.SQLContext
import org.apache.spark.sql.hive.HiveContext

object SparkHiveIntegration 
{
 
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("Hive and spark Integration")
    val sc = new SparkContext(conf)

    val sqlContext = new SQLContext(sc)

    val hiveContext = new HiveContext(sc)

    val rawData = hiveContext.sql("select * from training_rk.employees")
    rawData.registerTempTable("hive_employees")

    val query = hiveContext.sql("Select * from hive_employees")

    query.show()
    
    query.write.format("orc").saveAsTable("training_rk.result_emp")
    
    /*
     * data de-duplication
     */
    
    val query_HiveData = hiveContext.sql("Select emp_id,emp_name,emp_dsgntn,emp_doj,emp_sal,emp_dept, ROW_NUMBER() OVER(PARTITION BY emp_id ORDER BY emp_doj desc ) RANK from hive_employees")

    query_HiveData.show()
    query_HiveData.registerTempTable("rem_rank")
    
    
    val query_HiveDataRank = hiveContext.sql("select * from rem_rank where RANK = '1'")
    
    query_HiveDataRank.show()
    
    query_HiveDataRank.write.mode("overwrite").format("orc").saveAsTable("training_rk.result_emp")
    
  }
  
}