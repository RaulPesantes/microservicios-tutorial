package com.car.service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.car.service.entity.Car;
import com.car.service.service.CarService;

@RestController
@RequestMapping("/carro")
public class CarController {

    @Autowired
    private CarService carService;

    // 1️⃣ listCars() → listar todos los coches
    @GetMapping
    public ResponseEntity<List<Car>> listCars() {
        List<Car> cars = carService.getAll();
        if (cars.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(cars);
    }

    // 2️⃣ obtenerCar(id) → buscar un coche por id
    @GetMapping("/{id}")
    public ResponseEntity<Car> obtenerCar(@PathVariable("id") int id) {
        Car car = carService.getCarById(id);
        if (car == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(car);
    }

    // 3️⃣ guardarCar(car) → crear un coche nuevo
    @PostMapping
    public ResponseEntity<Car> guardarCar(@RequestBody Car car) {
        Car newCar = carService.save(car);
        return ResponseEntity.ok(newCar);
    }

    // 4️⃣ listCarsByUserId(usuarioId) → listar las carros de un usuario
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Car>> listCarsByUserId(@PathVariable int usuarioId) {
        List<Car> cars = carService.byUserId(usuarioId);
        if (cars.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(cars);
    }
    

}
