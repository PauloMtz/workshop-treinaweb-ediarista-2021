package com.home.ediaristas.repository;

import com.home.ediaristas.models.Diarista;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiaristaRepository extends JpaRepository<Diarista, Long> {
    
    // busca por código ibge
    Page<Diarista> findByCodigoIbge(String codigoIbge, Pageable pageable);
}
