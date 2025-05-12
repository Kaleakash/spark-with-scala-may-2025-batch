package com
class User(var name:String, var age:Int);
object DemoTest {
  def main(args: Array[String]): Unit = {
    var user1 = new User("John",21); // object of that class
    var user2 = new User("Steven",24);
    println(s"age is ${user1.name} and age is ${user1.age}")
    println(s"age is ${user2.name} and age is ${user2.age}")
  }
}
