package com.alexalves.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.alexalves.cursomc.domain.Cliente;
import com.alexalves.cursomc.dto.ClienteDTO;
import com.alexalves.cursomc.repositories.ClienteRepository;
import com.alexalves.cursomc.services.exceptions.DataIntegrityException;
import com.alexalves.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
  @Autowired
  private ClienteRepository repo;

  // LIST ALL
  public List<Cliente> find() {
      return repo.findAll();
  }

  // LIST ONE
  public Cliente find(Integer id) {
      Optional<Cliente> obj = repo.findById(id);

      return obj.orElseThrow(() -> new ObjectNotFoundException(
      "Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
  }

  // LIST PER PAGE
  public Page<Cliente> findPerPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}

  // INSERT
  public Cliente insert(Cliente obj) {
      obj.setId(null);
      return repo.save(obj);
  }
  
  // UPDATE
  public Cliente update(Cliente obj) {
    Cliente newObj = find(obj.getId());
    updateData(newObj, obj);
    return repo.save(newObj);
  }
    
  // DELETE
  public void delete(Integer id) {
    find(id);
    
    try {
      repo.deleteById(id);    		
    } catch (DataIntegrityViolationException e) {
      throw new DataIntegrityException("Não é possível excluir um cliente que possui pedidos.");
    }
  }

  // DTO -> DOMAIN
  public Cliente fromDTO(ClienteDTO objDto) {
		return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null);
	}

  private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
  }
}