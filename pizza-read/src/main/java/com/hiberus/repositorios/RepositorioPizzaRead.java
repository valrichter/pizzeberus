package com.hiberus.repositorios;

import com.hiberus.modelos.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositorioPizzaRead extends JpaRepository<Pizza,Integer> {

}
