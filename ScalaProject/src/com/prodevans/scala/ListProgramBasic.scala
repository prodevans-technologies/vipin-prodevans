package com.prodevans.scala

object ListProgramBasic 
{

  val number : List[Int]=List(1,2,3,4,5)
  val strings : List[String]=List("aaa","bbb","ccc","ddd")
  
  def displayNumbers()
  {
    number.foreach{println}
  }
  def displayStrings()
  {
    strings.foreach{println}
  }
  def main(args:Array[String])
  {
    ListProgramBasic.displayNumbers()
    ListProgramBasic.displayStrings()
  }
  
  val fruite1="apples" :: ("oranges" :: ("pears" :: Nil))
  val fruite2="mangos" :: ("banana" :: Nil)
  
  var fruite = fruite1 :::fruite2
  println("fruite1 ::: fruite2 :"+fruite)
  
  fruite = List.concat(fruite1,fruite2)
  println("concat function : "+fruite)
  
  
  val fruite_apple = List.fill(3)("apples")
  println("fruite apple : "+fruite_apple)
  
  
  var num_list = List.fill(10)(2)
  println("num list : "+num_list)
  
  
  var squares = List.tabulate(6)(n => n*n)
  println("squares : "+squares)
  
  var mul = List.tabulate(4,5)( _ * _ )
  println("mul  : "+mul)
  
  
  
  
  
  
  
  
  
  
  
  
  
}