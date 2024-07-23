package com.ensah.app.services.Imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ensah.app.entities.TypeElement;
import com.ensah.app.repository.ITypeElementRepository;
import com.ensah.app.services.ITypeElementService;

import jakarta.transaction.Transactional;
@Service
@Transactional
public class TypeElementImp implements ITypeElementService {
	@Autowired
	private ITypeElementRepository type;

	@Override
	public List<TypeElement> getAllTypeElement() {
		// TODO Auto-generated method stub
		return type.findAll();
	}

	@Override
	public TypeElement getTypeElementById(Long n) {
		// TODO Auto-generated method stub
		return type.findById(n).get();
	}

}
