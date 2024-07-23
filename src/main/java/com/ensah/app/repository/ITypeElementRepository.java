package com.ensah.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ensah.app.entities.TypeElement;

@Repository
public interface ITypeElementRepository   extends JpaRepository<TypeElement,Long> {

}
