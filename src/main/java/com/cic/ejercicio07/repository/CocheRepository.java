package com.cic.ejercicio07.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cic.ejercicio07.model.Coche;

@Repository
public interface CocheRepository extends JpaRepository<Coche, Long> {

}
