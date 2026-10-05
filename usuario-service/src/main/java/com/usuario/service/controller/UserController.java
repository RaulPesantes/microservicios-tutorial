package com.usuario.service.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usuario.service.entity.User;
import com.usuario.service.models.Car;
import com.usuario.service.models.Moto;
import com.usuario.service.service.UserService;

@RestController
@RequestMapping("/usuario")
public class UserController {

    @Autowired
    private UserService userService;

    // 1️⃣ listUsers() → listar todos los users
    @GetMapping
    public ResponseEntity<List<User>> listUsers() {
        List<User> users = userService.getAll();
        if (users.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(users);
    }

    // 2️⃣ obtenerUser(id) → buscar un user por id
    @GetMapping("/{id}")
    public ResponseEntity<User> obtenerUser(@PathVariable("id") int id) {
        User user = userService.getUserById(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    // 3️⃣ guardarUser(car) → crear un user nuevo
    @PostMapping
    public ResponseEntity<User> guardarUser(@RequestBody User user) {
        User newUser = userService.save(user);
        return ResponseEntity.ok(newUser);
    }

    // 4️⃣ listCars(id) → listar los carros de un usuario específico
    @GetMapping("/carros/{usuarioId}")
    public ResponseEntity<List<Car>> listCars(@PathVariable("usuarioId") int id) {
        User user = userService.getUserById(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        List<Car> cars = userService.getCars(id);
        return ResponseEntity.ok(cars);
    }

    // 5️⃣ listMotos(id) → listar los motos de un usuario específico
    @GetMapping("/motos/{usuarioId}")
    public ResponseEntity<List<Moto>> listMotos(@PathVariable("usuarioId") int id) {
        User user = userService.getUserById(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        List<Moto> motos = userService.getMotos(id);
        return ResponseEntity.ok(motos);
    }

    // 6️⃣ saveCar(usuarioId, car) → guardar un carro para un usuario específico
    @PostMapping("/carro/{usuarioId}")
    public ResponseEntity<Car> saveCar(@PathVariable("usuarioId") int usuarioId, @RequestBody Car car) {
        Car newCar = userService.saveCar(usuarioId, car);
        return ResponseEntity.ok(newCar);
    }

    // 7️⃣ saveMoto(usuarioId, moto) → guardar una moto para un usuario específico
    @PostMapping("/moto/{usuarioId}")
    public ResponseEntity<Moto> saveMoto(@PathVariable("usuarioId") int usuarioId, @RequestBody Moto moto) {
        Moto newMoto = userService.saveMoto(usuarioId, moto);
        return ResponseEntity.ok(newMoto);
    }

    // 8️⃣ listarTodosLosVehiculos(usuarioId) → obtener el usuario junto con la lista completa de sus vehículos
    @GetMapping("/todos/{usuarioId}")
    public ResponseEntity<Map<String, Object>> listarTodosLosVehiculos(@PathVariable("usuarioId") int usuarioId) {
        Map<String, Object> resultado = userService.getUsuarioAndVehiculos(usuarioId);
        return ResponseEntity.ok(resultado);
    }

}
