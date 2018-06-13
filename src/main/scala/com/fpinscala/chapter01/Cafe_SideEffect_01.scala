package com.fpinscala.chapter01

object Cafe_SideEffect_01 {

  class Cafe {
    def buyCoffee(cc: CreditCard) : Coffee = {
      val cup = new Coffee()

      cc.charge(cup.price)

      cup
    }
  }

  case class CreditCard(bankName: String, cardNumber: String) {
    def charge(price: Int): Unit = {
      println(s"Price::[$price]. Calling Payment-centre...")
      Thread.sleep(1000)
      println("Success.")
    }

    override def toString: String = s"Bank::[$bankName], CardNo::[$cardNumber]"
  }

  class Coffee {
    val price: Int = 10
    override def toString: String = s"Coffee. Price[$price]"
  }

  def main(args: Array[String]): Unit = {
    val creditCard = CreditCard("bankcomm.com", "4401-2678-3329-2180")

    new Cafe().buyCoffee(creditCard)
  }

}
