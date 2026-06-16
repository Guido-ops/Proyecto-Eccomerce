package eccomerce.ms_pagos.repository;

import eccomerce.ms_pagos.model.MetodoPago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface MetodoPagoRepository extends JpaRepository<MetodoPago, Long> {
    List<MetodoPago> findByActivoTrue();
    boolean existsByNombre(String nombre);
}