package br.com.meudominio.course.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.meudominio.course.entities.User;
import br.com.meudominio.course.repositories.UserRepository;
import br.com.meudominio.course.services.exceptions.DataBaseException;
import br.com.meudominio.course.services.exceptions.ResourceNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;

	public List<User> findAll() {
		return repository.findAll();
	}

	public User findById(Long id) {
		Optional<User> findById = repository.findById(id);

		return findById.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	public User insert(User objUser) {
		return repository.save(objUser);
	}

	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataBaseException(e.getMessage());
		}
	}

	public User update(Long id, User newUser) {

		try {
			User entity = repository.getOne(id);

			updateData(entity, newUser);

			return repository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}

	}

	private void updateData(User entity, User newUser) {
		entity.setName(newUser.getName());
		entity.setEmail(newUser.getEmail());
		entity.setPhone(newUser.getPhone());
	}

}
