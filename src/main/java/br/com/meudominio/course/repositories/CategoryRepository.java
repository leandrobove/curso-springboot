package br.com.meudominio.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.meudominio.course.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
