package com.car.service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.car.service.entity.Car;
import com.car.service.repository.CarRepository;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    // 1️⃣ getAll() → obtener todos los carros
    public List<Car> getAll() {
        return carRepository.findAll();
    }

    // 2️⃣ getCarById(int id) → obtener un carro por su id
    public Car getCarById(int id) {
        return carRepository.findById(id).orElse(null);
    }

    // 3️⃣ save(Car car) → guardar o actualizar un carro
    public Car save(Car car) {
        Car newCar = carRepository.save(car);
        return newCar;
    }

    public List<Car> byUserId(int userId) {
        return carRepository.findByUsuarioId(userId);
    }

}
