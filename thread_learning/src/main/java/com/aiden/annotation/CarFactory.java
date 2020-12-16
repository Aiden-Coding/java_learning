package com.aiden.annotation;

public class CarFactory {
  static public Car createCar(String carName) {
  Car car = null;
  if (carName.equals("奥迪")) {
    car = new AoDi();
  } else if (carName.equals("奔驰")) {
    car = new BenChi();
  }
  return car;

}
  public static void main(String[] args) {
    Car car1 = CarFactory.createCar("奥迪");
    Car car2 = CarFactory.createCar("奔驰");
    car1.run();
    car2.run();
  }
}
