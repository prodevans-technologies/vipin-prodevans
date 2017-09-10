package com.prodevans.condition

object PatternMatching 
{
  
  def main(args : Array[String])
  {
    
    println(matchTestAny(50))
    println(matchTestInt(1))
    println(matchTestString("R"))
    
    def matchTestInt(x:Int): String = x match
    {
      case 1=> "one"
      case 2=> "two"
      case _=> "many"
    }
    
    def matchTestString(x:String): String = x match
    {
      case "R"=> "Ram"
      case "a"=> "aa"
      case _=> "many"
    }

    def matchTestAny(x:Any): Any = x match
    {
      case "R"=> "Ram"
      case 1=> "aa"
      case y:Int=> "Scala.Int"  
      case _=> "many"
    }
    
  }
  
}