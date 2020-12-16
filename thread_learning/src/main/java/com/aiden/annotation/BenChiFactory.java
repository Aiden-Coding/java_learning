package com.aiden.annotation;

public class BenChiFactory {
  static public Car createCar() {
    return new BenChi();
  }
  public static void main(String[] args) {
    Car c1 = AoDiChiFactory.createCar();
    Car c2 = BenChiFactory.createCar();
    c1.run();
    c2.run();
  }
}
