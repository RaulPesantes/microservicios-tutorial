package com.usuario.service.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.usuario.service.entity.User;
import com.usuario.service.feignclients.CarFeignClient;
import com.usuario.service.feignclients.MotoFeignClient;
import com.usuario.service.models.Car;
import com.usuario.service.models.Moto;
import com.usuario.service.repository.UserRepository;

@Service 
public class UserService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired 
    private UserRepository userRepository;

    @Autowired 
    private CarFeignClient carFeignClient;

    @Autowired 
    private MotoFeignClient motoFeignClient;

    //1️⃣ getAll() → obtener todos los usuarios
    public List<User> getAll() {
        return userRepository.findAll();
    }

    //2️⃣ getUserById(int id) → obtener un usuario por su id
    public User getUserById(int id) {
        return userRepository.findById(id).orElse(null);
    }

    //3️⃣ save(User user) → guardar o actualizar un usuario
    public User save(User user) {
        User newUser = userRepository.save(user);
        return newUser;
    }

    //Esa línea es la que te permite consumir un microservicio desde otro servicio.
    public List<Car> getCars(int userId) {
        //List<Car> cars = restTemplate.getForObject("http://localhost:8082/carro/usuario/"+ userId , List.class);
        List<Car> cars = restTemplate.getForObject("http://carro-service/carro/usuario/"+ userId , List.class);
        return cars;
    }

    //Esa línea es la que te permite consumir un microservicio desde otro servicio.
    public List<Moto> getMotos(int userId) {
        //List<Moto> motos = restTemplate.getForObject("http://localhost:8083/moto/usuario/"+ userId , List.class);
        List<Moto> motos = restTemplate.getForObject("http:moto-service/moto/usuario/"+ userId , List.class);
        return motos;
    }

    // guardarCar(usuarioId, car) → guarda un carro asociado a un usuario
    public Car saveCar(int usuarioId, Car car) {
        car.setUsuarioId(usuarioId);
        Car newCar = carFeignClient.save(car);
        return newCar;
    } 

    // minuto 1:43:48
    public Moto saveMoto(int usuarioId, Moto moto) {
        moto.setUsuarioId(usuarioId);
        Moto newMoto = motoFeignClient.save(moto);
        return newMoto;
    }

    //
    public Map<String, Object> getUsuarioAndVehiculos(int usuarioId) {
        Map<String, Object> resultado = new HashMap<>();
        User user = userRepository.findById(usuarioId).orElse(null);
        if (user == null) {
            resultado.put("Mensaje", "El usuario no existe");
            return resultado;
        }
        resultado.put("Usuario", user);
        List<Car> cars = carFeignClient.getCars(usuarioId);
        if (cars.isEmpty()) {
            resultado.put("Mensaje, carros", "El usuario no tiene carros");
        }else{
            resultado.put("Mensaje, carros", cars);
        }
        
        List<Moto> motos = motoFeignClient.getMotos(usuarioId);
        if (motos.isEmpty()) {
            resultado.put("Mensaje, motos", "El usuario no tiene carros");
        }else{
            resultado.put("Mensaje motos", motos);
        }
        return resultado;
    }

}
