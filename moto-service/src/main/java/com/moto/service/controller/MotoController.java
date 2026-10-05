package com.moto.service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moto.service.entity.Moto;
import com.moto.service.service.MotoService;

@RestController
@RequestMapping("/moto")
public class MotoController {

    @Autowired
    private MotoService motoService;

    // 1️⃣ listMotos() → listar todos los motos
    @GetMapping
    public ResponseEntity<List<Moto>> listMotos() {
        List<Moto> motos = motoService.getAll();
        if (motos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(motos);
    }

    // 2️⃣ obtenerMoto(id) → buscar un moto por id
    @GetMapping("/{id}")
    public ResponseEntity<Moto> obtenerMoto(@PathVariable("id") int id) {
        Moto moto = motoService.getMotoById(id);
        if (moto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(moto);
    }

    // 3️⃣ guardarMoto(moto) → crear un moto nuevo
    @PostMapping
    public ResponseEntity<Moto> guardarMoto(@RequestBody Moto moto) {
        Moto newMoto = motoService.save(moto);
        return ResponseEntity.ok(newMoto);
    }

    // 4️⃣ listMotosByUserId(usuarioId) → listar las motos de un usuario
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Moto>> listMotosByUserId(@PathVariable int usuarioId) {
        List<Moto> motos = motoService.byUserId(usuarioId);
        if (motos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(motos);
    }

}

//me quede en 57:06