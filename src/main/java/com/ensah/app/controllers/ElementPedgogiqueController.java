package com.ensah.app.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.ensah.app.exeptions.DuplicateNomException;
import com.ensah.app.exeptions.FieldValidationException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.ensah.app.entities.ElementPedagodique;

import com.ensah.app.entities.Administrateur;
import com.ensah.app.entities.Enseignant;
import com.ensah.app.entities.Niveau;
import com.ensah.app.entities.Personnel;
import com.ensah.app.entities.TypeElement;
import com.ensah.app.exeptions.DuplicateCinException;
import com.ensah.app.exeptions.FieldValidationException;
import com.ensah.app.responses.ElementPedagogiqueResponse;
import com.ensah.app.responses.PersonResponse;
import com.ensah.app.services.IElementPedogogiqueService;
import com.ensah.app.services.INiveauService;
import com.ensah.app.services.IPersonnelService;
import com.ensah.app.services.ITypeElementService;
import com.ensah.app.services.Imp.NiveauImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth/admin")
@CrossOrigin

public class ElementPedgogiqueController {
	

	
	@Autowired
	private INiveauService niveau;
	@Autowired
	private IPersonnelService enseignant;
	@Autowired
    private IElementPedogogiqueService EPS;

	@Autowired
    private ITypeElementService type;

	
	
	@GetMapping("showNiveau")
	 public ResponseEntity<List<Niveau>>  getNiveaux(){
		List<Niveau> enseignants = niveau.getAllNiveau();
		System.out.print("les niveaux sont "+ enseignants);
        return ResponseEntity.ok(enseignants);
		
	}
	@GetMapping("showElements")
	 public ResponseEntity<List<TypeElement>>  getElements(){
		List<TypeElement> elemets = type.getAllTypeElement();
		System.out.print("les elemets sont "+ elemets);
       return ResponseEntity.ok(elemets);
		
	}
	
	@GetMapping("/enseignants")
    public ResponseEntity<List<Enseignant>> getEnseignants() {
        List<Personnel> allPersonnels = enseignant.getAllPersonnes();
        List<Enseignant> enseignants = allPersonnels.stream()
                                                    .filter(personnel -> personnel instanceof Enseignant)
                                                    .map(personnel -> (Enseignant) personnel)
                                                    .collect(Collectors.toList());
		System.out.print("les enseignants sont "+ enseignants);

        return ResponseEntity.ok(enseignants);
    }
	
	
	@PostMapping("addElementPedogogique")
	public ResponseEntity<String> addElementPedogogique(@Valid @RequestBody ElementPedagogiqueResponse EP,BindingResult bindingResult){
		
		 if (bindingResult.hasErrors()) {
		        // Gérer les erreurs de validation ici
		        return ResponseEntity.badRequest().body("Erreur de validation: veuillez vérifier les données envoyées.");
		    }
		 if (EPS.existsBynom(EP.getNom())) {
	            throw new DuplicateNomException("Duplicate nom: " + EP.getNom());
	        }
	        System.out.print("l'id dyal personne howa "+EP.getIdprofesseur());
	        System.out.print("l'id dyal niveau  howa "+EP.getNiveau_id());
	        System.out.print("l'id dyal niveau  howa "+EP.getTypeElement_id());


		 ElementPedagodique e = new ElementPedagodique();
	        BeanUtils.copyProperties(EP, e);

		 e.setProfesseur((Enseignant) enseignant.getPersonnelById( Long.valueOf(EP.getIdprofesseur())));
	        e.setNiveau(niveau.getNiveauById( Long.valueOf(EP.getNiveau_id())));
	        e.setTypeElement(type.getTypeElementById( Long.valueOf(EP.getTypeElement_id())));


	       /* TypeElement typeElement = TypeElement.valueOf(EP.getTypeElement());
	        e.setTypeElement(typeElement);*/
	     

	        

	        EPS.addElementPedagogique(e);

		    return ResponseEntity.ok("ElementPedagodique ajoutée avec succès.");
		
		
	}
	@GetMapping(value = "showElementPeda")
	public ResponseEntity<List<ElementPedagogiqueResponse>> showForm() {
	    List<ElementPedagodique> e = EPS.getAllElementPedagogique();
	    List<ElementPedagogiqueResponse> eResponse = new ArrayList<>();

	    for (ElementPedagodique el : e) {
	    	ElementPedagogiqueResponse pm = new ElementPedagogiqueResponse();
	       
	            BeanUtils.copyProperties(el, pm);
	           
	        
	            eResponse.add(pm);
	    }
		System.out.print("les ElementPedagogique sont "+ e);

	    return ResponseEntity.ok(eResponse);
	}

	
	@PutMapping(value = "updateElementpeda/{id}")
	public ResponseEntity<?> updatePerson(@PathVariable Long id, @RequestBody ElementPedagogiqueResponse eResponse) {
	    try {
	        ElementPedagodique existingEL = EPS.getElementPedagogiqueById(id);
	        if (existingEL != null) {
	            // Debugging logs
	            System.out.println("Updating ElementPedagodique with ID: " + id);
	            System.out.println("Received values: " + eResponse);

	            // Copying properties
	           // BeanUtils.copyProperties(eResponse, existingEL);

	            // Updating the fields
	            existingEL.setNom(eResponse.getNom());
	           // existingEL.setTypeElement(type.getTypeElementById( Long.valueOf(eResponse.getTypeElement_id())));

	            // Debugging logs for idprofesseur and niveau_id
	            System.out.println("Received idprofesseur: " + eResponse.getIdprofesseur());
	            System.out.println("Received niveau_id: " + eResponse.getNiveau_id());
	            
	            if (eResponse.getTypeElement_id() != 0) {
	                TypeElement typee = type.getTypeElementById( Long.valueOf(eResponse.getTypeElement_id()));
	                if (typee != null) {
	                    existingEL.setTypeElement(typee);
	                } else {
	                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("typeElemnt not found");
	                }
	            }

	            if (eResponse.getIdprofesseur() != 0) {
	                Enseignant professeur = (Enseignant) enseignant.getPersonnelById(Long.valueOf(eResponse.getIdprofesseur()));
	                if (professeur != null) {
	                    existingEL.setProfesseur(professeur);
	                } else {
	                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Professeur not found");
	                }
	            }
	            if (eResponse.getNiveau_id() != 0) {
	                Niveau niveau = this.niveau.getNiveauById(Long.valueOf(eResponse.getNiveau_id()));
	                if (niveau != null) {
	                    existingEL.setNiveau(niveau);
	                } else {
	                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Niveau not found");
	                }
	            }

	            // Updating the ElementPedagodique
	            EPS.updateElementPedagogique(existingEL);
	            return ResponseEntity.ok(existingEL);
	        } else {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ElementPedagodique not found");
	        }
	    } catch (Exception e) {
	        // Debugging logs
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating ElementPedagogique");
	    }
	}



	
	@DeleteMapping(value = "deleteElementPedagogique/{idElementPedagogique}")
	public ResponseEntity<String> delete(@PathVariable int idElementPedagogique) {
	    EPS.deleteElementPedagogique(Long.valueOf(idElementPedagogique));
	    return ResponseEntity.ok("L'ElementPedagogique a été supprimée avec succès.");
	}


	@ExceptionHandler(DuplicateNomException.class)
	public ResponseEntity<String> handleDuplicateCinException(DuplicateCinException ex) {
	    return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(FieldValidationException.class)
	public ResponseEntity<String> handleFieldValidationException(FieldValidationException ex) {
	    return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
	    Map<String, String> errors = new HashMap<>();
	    ex.getBindingResult().getAllErrors().forEach((error) -> {
	        String fieldName = ((FieldError) error).getField();
	        String errorMessage = error.getDefaultMessage();
	        errors.put(fieldName, errorMessage);
	    });
	    return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	}


	
	
	
	
	
}
