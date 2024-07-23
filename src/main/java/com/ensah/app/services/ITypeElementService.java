package com.ensah.app.services;

import java.util.List;

import com.ensah.app.entities.Niveau;
import com.ensah.app.entities.TypeElement;

public interface ITypeElementService {
	List<TypeElement> getAllTypeElement();
	public TypeElement getTypeElementById(Long n);
}
