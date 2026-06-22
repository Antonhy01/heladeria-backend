package com.heladeria.repository;

import org.springframework.stereotype.Repository;
import com.heladeria.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}