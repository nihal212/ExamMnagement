package com.ensah.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ensah.app.entities.ElementPedagodique;
import com.ensah.app.entities.Personnel;

@Repository

public interface IElementPedagogiqueRepository extends JpaRepository<ElementPedagodique,Long> {
    boolean existsBynom(String nom);


}
