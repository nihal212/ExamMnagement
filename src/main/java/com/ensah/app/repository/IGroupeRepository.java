package com.ensah.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ensah.app.entities.Groupe;

@Repository
public interface IGroupeRepository extends JpaRepository<Groupe, Long> {
}