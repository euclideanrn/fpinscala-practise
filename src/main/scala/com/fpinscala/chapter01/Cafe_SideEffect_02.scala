package com.fpinscala.chapter01

import scala.util.Try

object Cafe_SideEffect_02 {

  class Cafe {
    def buyCoffee(cc: CreditCard, p: Payments) : Coffee = {
      val cup = new Coffee()

      p.charge(cc, cup.price)

      cup
    }
  }

  class Payments {
    def charge(cc: CreditCard, price: Int): Unit = {
      println(s"Price::[$price]. Calling Payment-centre...")
      Thread.sleep(1000)
      println("Success.")
    }
  }

  case class CreditCard(bankName: String, cardNumber: String) {
    override def toString: String = s"Bank::[$bankName], CardNo::[$cardNumber]"
  }

  class Coffee {
    val price: Int = 10
    override def toString: String = s"Coffee. Price[$price]"
  }

  def main(args: Array[String]): Unit = {
    val creditCard = CreditCard("bankcomm.com", "4401-2678-3329-2180")

    new Cafe().buyCoffee(creditCard, new Payments)

    val res = Try(() => throw new InterruptedException("Err")).get
    println(res.apply())
  }

}
