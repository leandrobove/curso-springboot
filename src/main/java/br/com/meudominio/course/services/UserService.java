package br.com.meudominio.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.meudominio.course.entities.User;
import br.com.meudominio.course.repositories.UserRepository;
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
		repository.deleteById(id);
	}

	public User update(Long id, User newUser) {
		User entity = repository.getOne(id);

		updateData(entity, newUser);

		return repository.save(entity);
	}

	private void updateData(User entity, User newUser) {
		entity.setName(newUser.getName());
		entity.setEmail(newUser.getEmail());
		entity.setPhone(newUser.getPhone());
	}

}
