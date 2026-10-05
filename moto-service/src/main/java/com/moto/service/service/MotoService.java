package com.moto.service.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.moto.service.entity.Moto;
import com.moto.service.repository.MotoRepository;

@Service
public class MotoService {

    @Autowired
    private MotoRepository motoRepository;

    // 1️⃣ getAll() → obtener todos los motos
    public List<Moto> getAll() {
        return motoRepository.findAll();
    }

    // 2️⃣ getMotoById(int id) → obtener un moto por su id
    public Moto getMotoById(int id) {
        return motoRepository.findById(id).orElse(null);
    }

    // 3️⃣ save(Moto moto) → guardar o actualizar un moto
    public Moto save(Moto moto) {
        Moto newMoto = motoRepository.save(moto);
        return newMoto;
    }

    public List<Moto> byUserId(int userId) {
        return motoRepository.findByUsuarioId(userId);
    }

}
