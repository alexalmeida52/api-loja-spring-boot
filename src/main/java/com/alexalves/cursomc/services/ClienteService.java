package com.alexalves.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.alexalves.cursomc.domain.Cliente;
import com.alexalves.cursomc.repositories.ClienteRepository;
import com.alexalves.cursomc.services.exceptions.DataIntegrityException;
import com.alexalves.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository repo;

    public Cliente find(Integer id) {
        Optional<Cliente> obj = repo.findById(id);

        return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
    }

    public List<Cliente> find() {
        List<Cliente> list = repo.findAll();

        return list;
    }

    public Cliente insert(Cliente obj) {
        obj.setId(null);
        return repo.save(obj);
    }
    
    public Cliente update(Cliente obj) {
    	find(obj.getId());
    	return repo.save(obj);
    }
    
    public void remove(Integer id) {
    	find(id);
    	
    	try {
    		repo.deleteById(id);    		
    	} catch (DataIntegrityViolationException e) {
    		throw new DataIntegrityException("Não é possível excluir um cliente que possui pedidos.");
    	}
    }
}