package com.prodevans.scala

object TupleProgramBasic 
{

  def main(args : Array[String])
  {
    val t=(1,"hello","hadoop")
    val emp=("emp001","hello",25000)
    
    println(emp._3)
    
    emp.productIterator.foreach{ i => println("value : "+i) }
    
    val student=new Tuple4(1,"abc","bbb","cccc")
    println("concated string : "+student.toString())
    
  }
  
}