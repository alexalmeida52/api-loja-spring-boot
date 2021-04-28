package com.alexalves.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alexalves.cursomc.domain.Estado;
import com.alexalves.cursomc.repositories.EstadoRepository;
import com.alexalves.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class EstadoService {
    @Autowired
    private EstadoRepository repo;

    public Estado find(Integer id) {
        Optional<Estado> obj = repo.findById(id);

        return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Estado.class.getName()));
    }
}