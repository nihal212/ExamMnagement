package com.ensah.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ensah.app.entities.Personnel;


public interface IPersonnelRepository extends JpaRepository<Personnel, Long> {

	Personnel getPersonnelByCin(String cin);
    boolean existsByCin(String cin);


}
