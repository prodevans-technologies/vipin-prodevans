package com.prodevans.scala

object ListWithClass 
{

  def main(args : Array[String])
  {
    
    case class Student(st_roll : Int, st_name : String, st_marks : Int)
    
    val st_list = List(new Student(1,"aaa",80),new Student(2,"bbbb",90),new Student(3,"ccc",40))
    
    st_list drop 1 take 3
    
    val firs_student = st_list.head
    
    st_list.foreach{ x => println("Student No %d "+x) }
    
    val last_student =st_list.tail.last
    
    println(firs_student)
    println(last_student)
    
    val pass_student = st_list.filter{ x=> x.st_marks > 60}
    println(pass_student)
    
    
    
    
  }
  
}