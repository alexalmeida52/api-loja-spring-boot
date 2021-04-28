package com.alexalves.cursomc.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alexalves.cursomc.domain.Estado;
import com.alexalves.cursomc.services.EstadoService;

@RestController
@RequestMapping(value="/estados")
public class EstadoResource {
    @Autowired
    private EstadoService service;

    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public ResponseEntity<?> find(@PathVariable Integer id){
        Estado obj = service.find(id);
        return ResponseEntity.ok().body(obj);
    }
}