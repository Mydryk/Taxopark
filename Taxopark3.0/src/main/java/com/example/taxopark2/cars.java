package com.example.taxopark2;

public class cars {

    int idCars, fuel_consumption;
    String car_name, car_model, hight_speed,  car_price ;

    public int getIdCars() {
        return idCars;
    }

    public void setIdCars(int idCars) {
        this.idCars = idCars;
    }

    public String getCar_name() {
        return car_name;
    }

    public void setCar_name(String car_name) {
        this.car_name = car_name;
    }

    public String getCar_model() {
        return car_model;
    }

    public void setCar_model(String car_model) {
        this.car_model = car_model;
    }

    public String getHight_speed() {
        return hight_speed;
    }

    public void setHight_speed(String hight_speed) {
        this.hight_speed = hight_speed;
    }

    public int getFuel_consumption() {
        return fuel_consumption;
    }

    public void setFuel_consumption(int fuel_consumption) {
        this.fuel_consumption = fuel_consumption;
    }

    public String getCar_price() {
        return car_price;
    }

    public void setCar_price(String car_price) {
        this.car_price = car_price;
    }






    public cars(int id, String car_name, String car_model, String hight_speed, int fuel_consumption, String car_price) {
        this.idCars = id;
        this.car_name = car_name;
        this.car_model = car_model;
        this.hight_speed = hight_speed;
        this.fuel_consumption = fuel_consumption;
        this.car_price = car_price;
    }
}



