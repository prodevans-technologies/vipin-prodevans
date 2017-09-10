package com.prodevans.functions

object HigherOrderFunction 
{
  
  class FunctionClass
  {
    def number(x : Int):String =
    {
      return (x.toString() + x.toString())
    }
  }
  
  def main(a :  Array[String] )
  {
    
    def summation( f:Int=>String, x:Int)=f(x)
    
    def number(x : Int):String =
    {
      return (x.toString() + x.toString())
    }
    
    val obj=new FunctionClass()
    
    println(summation(number , 7))
    println(summation(obj.number , 10))
    
  }

  

}