package com.ensah.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ensah.app.entities.Niveau;

@Repository
public interface INiveauRepository extends JpaRepository<Niveau,Long> {
	

}
