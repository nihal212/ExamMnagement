package com.ensah.app.services;

import java.util.List;

import com.ensah.app.entities.ElementPedagodique;
import com.ensah.app.entities.Personnel;

public interface IElementPedogogiqueService {
	public void addElementPedagogique(ElementPedagodique e);

	public void updateElementPedagogique(ElementPedagodique e);

	public List<ElementPedagodique> getAllElementPedagogique();

	public void deleteElementPedagogique(Long id);

	public ElementPedagodique getElementPedagogiqueById(Long id);
    boolean existsBynom(String nom);

}
