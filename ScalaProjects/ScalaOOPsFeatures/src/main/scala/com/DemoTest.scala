package com
class User(var name:String, var age:Int);
case class Person(name:String, age :Int); // by default it consider as val type
object DemoTest {
  def main(args: Array[String]): Unit = {
    var user1 = new User("John",21); // object of that class
    var user2 = new User("Steven",24);
    var p1= new Person("Ravi",25);
    var p2= new Person("Ajay",28);
    println(s"age is ${user1.name} and age is ${user1.age}")
    println(s"age is ${user2.name} and age is ${user2.age}")
    user1.age=31;
    user2.age=32;
    println(s"age is ${user1.name} and age is ${user1.age}")
    println(s"age is ${user2.name} and age is ${user2.age}")

    println(s"age is ${p1.name} and age is ${p1.age}")
    println(s"age is ${p2.name} and age is ${p2.age}")
    //p1.age = 31;
    //p2.age = 32;
    println(s"age is ${p1.name} and age is ${p1.age}")
    println(s"age is ${p2.name} and age is ${p2.age}")
  }
}
