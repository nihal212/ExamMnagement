package com.ensah.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ensah.app.entities.Departement;
@Repository
public interface IdepartementRepository extends JpaRepository<Departement, Long>  {

}
