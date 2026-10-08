package com.usuario.service.feignclients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;

import com.usuario.service.models.Car;

// ⚠️ Regla:
// Ruta del Feign = @RequestMapping de la clase del controlador + @GetMapping/@PostMapping del método
// Ejemplo: CarController tiene @RequestMapping("/carro") y @GetMapping("/usuario/{id}")
//          → el Feign debe usar @GetMapping("/carro/usuario/{id}")

//@FeignClient(name = "carro-service", url = "http://localhost:8082")
@FeignClient(name = "car-service") // Sin url: Feign lo descubre vía Eureka + LoadBalancer
public interface CarFeignClient {

    @PostMapping("/carro")
    public Car save(@RequestBody Car car);

    @GetMapping("/carro/usuario/{usuarioId}")
    public List<Car> getCars(@PathVariable("usuarioId") int usuarioId);

}
