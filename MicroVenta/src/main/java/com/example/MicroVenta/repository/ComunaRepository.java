package com.example.MicroVenta.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.MicroVenta.model.Comuna;

@Repository
public interface ComunaRepository extends JpaRepository<Comuna, Integer> {
}
