package com

object PartiallyAppliedFunctions {

  def findRateFoInterest(amount:Double, rateOfInterest:Double):Double = {
    var totalAmount = amount + amount*rateOfInterest/100;
    totalAmount
  }

  def main(args: Array[String]): Unit = {
  var result1 = findRateFoInterest(100,4);    // fully applied functions
    println("Total amount "+result1);
    var tempResult = findRateFoInterest(200,_:Double)  // partially applied functions
    var result2 = tempResult(2);
    println("Result with partially applied functions  "+result2);
    var result3 = tempResult(4);
    println("Result with partially applied functions  " + result3);
  }
}
