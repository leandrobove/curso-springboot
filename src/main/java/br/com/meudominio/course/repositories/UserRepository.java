package br.com.meudominio.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.meudominio.course.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

	
}
