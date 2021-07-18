package com.alexalves.cursomc.services;

import java.util.List;
import java.util.Optional;

import com.alexalves.cursomc.dto.CategoriaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.alexalves.cursomc.domain.Categoria;
import com.alexalves.cursomc.repositories.CategoriaRepository;
import com.alexalves.cursomc.services.exceptions.DataIntegrityException;
import com.alexalves.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repo;

	// LIST ALL
    public List<Categoria> find() {
        return repo.findAll();
    }

    // LIST ONE
    public Categoria find(Integer id) {
        Optional<Categoria> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
    }

    // LIST PER PAGE
    public Page<Categoria> findPerPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}

    // INSERT
    public Categoria insert(Categoria obj) {
        obj.setId(null);
        return repo.save(obj);
    }
    
    // UPDATE
    public Categoria update(Categoria obj) {
    	find(obj.getId());
    	return repo.save(obj);
    }
    
    // REMOVE
    public void remove(Integer id) {
    	find(id);
    	
    	try {
    		repo.deleteById(id);    		
    	} catch (DataIntegrityViolationException e) {
    		throw new DataIntegrityException("Não é possível excluir uma categoria que possui produtos.");
    	}
    }

    // DTO -> DOMAIN
    public Categoria fromDTO(CategoriaDTO objDTO) {
		return new Categoria(objDTO.getId(), objDTO.getNome());
	}

}