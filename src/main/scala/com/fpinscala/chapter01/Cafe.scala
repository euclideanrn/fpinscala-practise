package com.fpinscala.chapter01

object Cafe {

  def buyCoffee(cc: CreditCard): (Coffee, Charge) = {
    val cup = new Coffee
    (cup, Charge(cc, cup.price))
  }

  def buyCoffee(cc: CreditCard, n: Int): (List[Coffee], Charge) = {
    val purchases: List[(Coffee, Charge)] = List.fill(n) (buyCoffee(cc))
    val (coffees, charges) = purchases.unzip
    (coffees, charges.reduce((c1, c2) => c1.combine(c2)))
  }

  case class Charge(cc: CreditCard, amount: Double) {
    def combine(other: Charge): Charge = {
      if (cc == other.cc)
        Charge(cc, amount + other.amount)
      else
        throw new Exception("Cannot combine charges to different credit cards.")
    }

    override def toString: String = s"CreditCard::[$cc]. Amount::[$amount]"
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

    val oneCoffee = Cafe.buyCoffee(creditCard)
    println(oneCoffee)

    val nCoffee = Cafe.buyCoffee(creditCard, 100)
    println(nCoffee)
  }
}
