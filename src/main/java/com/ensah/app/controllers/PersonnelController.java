package com.ensah.app.controllers;




import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ensah.app.services.DepartementService;
import com.ensah.app.services.IGroupeService;
import com.ensah.app.services.IPersonnelService;
import com.ensah.app.responses.PersonResponse;
import com.ensah.app.entities.*;
import com.ensah.app.exeptions.DuplicateCinException;
import com.ensah.app.exeptions.FieldValidationException;
import com.ensah.app.repository.IdepartementRepository;
import com.ensah.app.requests.PersonnelRequest;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

/**
 * Ce controleur gère les personnes de type Etudiant, Enseignant et Cadre Admin
 * 
 * @author Boudaa
 *
 */

@RestController
@RequestMapping("/api/v1/auth/admin")
@CrossOrigin
public class PersonnelController {

	@Autowired
	private IPersonnelService personService;
   @Autowired 
private DepartementService departement;
	@Autowired
	private HttpSession httpSession;
	@Autowired
    private IGroupeService groupeService;
	
	/** Utilisé pour la journalisation */
	private Logger LOGGER = LoggerFactory.getLogger(getClass());


	public PersonnelController() {


		
		
	}
	
	 @GetMapping("departement")
	 public ResponseEntity<List<Departement>> AllDepartements(){
		 return ResponseEntity.ok(departement.getAllDepartements());
		 
	 }
	 @GetMapping("groupe")
	 public ResponseEntity<List<Groupe>> Allgroup(){
		 return ResponseEntity.ok(groupeService.getAllGroup());
		 
	 }

	@GetMapping(value = "showForm")
	public ResponseEntity<List<PersonResponse>> showForm(@RequestParam int typePerson) {
	    List<Personnel> persons = personService.getAllPersonnes();
	    List<PersonResponse> modelPersons = new ArrayList<>();

	    for (Personnel person : persons) {
	        PersonResponse pm = new PersonResponse();
	        if (typePerson == PersonResponse.TYPE_PROF) {
	            BeanUtils.copyProperties(person, pm);
	            pm.setTypePerson(PersonResponse.TYPE_PROF);
	        } else if (typePerson == PersonResponse.TYPE_Administrateur) {
	            BeanUtils.copyProperties(person, pm);
	            pm.setTypePerson(PersonResponse.TYPE_Administrateur);
	        }
	        modelPersons.add(pm);
	    }

	    return ResponseEntity.ok(modelPersons);
	}



	@PostMapping(value = "/addPerson")
	public ResponseEntity<String> addPerson(@Valid @RequestBody PersonResponse personResponse, BindingResult bindingResult) {
	    if (bindingResult.hasErrors()) {
	        // Gérer les erreurs de validation ici
	        return ResponseEntity.badRequest().body("Erreur de validation: veuillez vérifier les données envoyées.");
	    }
	
	    if (personService.existsByCin(personResponse.getCin())) {
            throw new DuplicateCinException("Duplicate CIN: " + personResponse.getCin());
        }

	    // Créer une nouvelle entité en fonction du type de personne
	    if (personResponse.getTypePerson() == PersonResponse.TYPE_PROF) {
	    	System.out.println("qsdfghjklmlkjhgfdsq"+personResponse.getId_departement());
	        Enseignant enseignant = new Enseignant();
	        enseignant.setDepartement( departement.findByIdDeparetement( personResponse.getId_departement()));
	        BeanUtils.copyProperties(personResponse, enseignant);
	        if (personResponse.getId_departement() == 1) {
                Groupe groupe = groupeService.findByIdGroup(1);
                if (groupe != null) {
                    enseignant.setGroupe(groupe);
                }
            }else if(personResponse.getId_departement() == 2) {
                Groupe groupe = groupeService.findByIdGroup(2);
                if (groupe != null) {
                    enseignant.setGroupe(groupe);
                }
                }

	        personService.addPersonnel(enseignant);
	    } else if (personResponse.getTypePerson() == PersonResponse.TYPE_Administrateur) {
	        Administrateur administrateur = new Administrateur();
	        BeanUtils.copyProperties(personResponse, administrateur);
	        personService.addPersonnel(administrateur);
	    }

	    // Retourner une réponse indiquant que l'ajout a été réussi
	    return ResponseEntity.ok("Personne ajoutée avec succès.");
	}


	/*@PutMapping(value = "updatePerson/{idPerson}")
	public ResponseEntity<String> updatePersonForm(@PathVariable("idPerson") int idPerson,@RequestBody PersonnelRequest PersonnelRequest) {

		// On reoit comme paramètre l'id de la personne à mettre à jour
		Personnel utl = personService.getPersonnelById(Long.valueOf(idPerson));

		// On construit le modèle

		// En fonction due type de l'utilisateur à modifier
		// Ceci va nous pemettre d'afficher un formulaire adapté
		// slon le type de la personne
		if (utl instanceof Enseignant) {
			
			utl.setNom(PersonnelRequest.getNom());
		    utl.setPrenom(PersonnelRequest.getPrenom());
		    utl.setCin(PersonnelRequest.getCin());
		    utl.setEmail(PersonnelRequest.getEmail());

		    ((Enseignant) utl).setSpecialite(PersonnelRequest.getSpecialite());
		} else if (utl instanceof Administrateur) {
			utl.setNom(PersonnelRequest.getNom());
		    utl.setPrenom(PersonnelRequest.getPrenom());
		    utl.setCin(PersonnelRequest.getCin());
		    ((Administrateur) utl).setProfession(PersonnelRequest.getProfession());
		}

		// Initialiser le modele avec la personne
	
	    return ResponseEntity.ok("Personne Modifier avec succès.");
	}*/
	
	
	
	
	@PutMapping("/updatePerson/{id}")
	public ResponseEntity<?> updatePerson(@PathVariable Long id, @RequestBody PersonResponse personResponse) {
	    try {
	        // Logique pour trouver et mettre à jour la personne dans la base de données
	        Personnel existingPerson = personService.getPersonnelById(id);
	        if (existingPerson != null) {
	            BeanUtils.copyProperties(personResponse, existingPerson);
	            personService.updatePersonnel(existingPerson);
	            return ResponseEntity.ok(existingPerson);
	        } else {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Person not found");
	        }
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating person");
	    }
	}

	
	
	
/*
	@RequestMapping(value = "serachPerson", method = RequestMethod.GET)
	public String serachPerson(@RequestParam String cin, Model model) {

		// On reoit comme paramètre l'id de la personne à mettre à jour
		Personne utl = personService.getPersonneByCin(cin);

		if (utl == null) {

			// Initialiser le modele avec la personne
			model.addAttribute("personModel", new ArrayList<PersonModel>());
		} else {

			// On construit le modèle
			PersonModel pm = new PersonModel();

			// En fonction due type de l'utilisateur à modifier
			// Ceci va nous pemettre d'afficher un formulaire adapté
			// slon le type de la personne
			if (utl instanceof Etudiant) {
				BeanUtils.copyProperties((Etudiant) utl, pm);
				pm.setTypePerson(PersonModel.TYPE_STUDENT);
			} else if (utl instanceof Enseignant) {
				BeanUtils.copyProperties((Enseignant) utl, pm);
				pm.setTypePerson(PersonModel.TYPE_PROF);
			} else if (utl instanceof CadreAdministrateur) {
				BeanUtils.copyProperties((CadreAdministrateur) utl, pm);
				pm.setTypePerson(PersonModel.TYPE_CADRE_ADMIN);

			}
			List<PersonModel> modelPersons = new ArrayList<PersonModel>();
			modelPersons.add(pm);
			// Initialiser le modele avec la personne
			model.addAttribute("personList", modelPersons);
		}
		return "admin/listPersons";
	}

	@RequestMapping("updatePerson")
	public String updatePerson(@Valid @ModelAttribute("personModel") PersonModel person, BindingResult bindingResult,
			Model model) {

		// En cas d'erreur
		if (bindingResult.hasErrors()) {

			return "admin/updateForm";
		}

		// On copie les données du modèle vers l'objet métier puis on appel le service
		// pour faire la mise à jour
		if (person.getTypePerson() == PersonModel.TYPE_STUDENT) {
			Etudiant etd = new Etudiant();
			BeanUtils.copyProperties(person, etd);

			personService.updatePersonne(etd);

		} else if (person.getTypePerson() == PersonModel.TYPE_PROF) {
			Enseignant prof = new Enseignant();
			BeanUtils.copyProperties(person, prof);
			personService.updatePersonne(prof);

		} else if (person.getTypePerson() == PersonModel.TYPE_CADRE_ADMIN) {
			CadreAdministrateur ca = new CadreAdministrateur();
			BeanUtils.copyProperties(person, ca);
			personService.updatePersonne(ca);

		}

		// Mettre le message de succès dans le modèle
		model.addAttribute("msg", "Opération effectuée avec succès");

		return "admin/updateForm";
	}

	@RequestMapping("managePersons")
	public String managePersons(Model model) {

		List<Personne> persons = personService.getAllPersonnes();
		List<PersonModel> modelPersons = new ArrayList<PersonModel>();

		// Copier les objets metier vers PersonModel plus flexible
		for (int i = 0; i < persons.size(); i++) {
			PersonModel pm = new PersonModel();
			if (persons.get(i) instanceof Etudiant) {
				BeanUtils.copyProperties((Etudiant) persons.get(i), pm);
				pm.setTypePerson(PersonModel.TYPE_STUDENT);
				modelPersons.add(pm);
			} else if (persons.get(i) instanceof Enseignant) {
				BeanUtils.copyProperties((Enseignant) persons.get(i), pm);
				pm.setTypePerson(PersonModel.TYPE_PROF);
				modelPersons.add(pm);
			} else if (persons.get(i) instanceof CadreAdministrateur) {
				BeanUtils.copyProperties((CadreAdministrateur) persons.get(i), pm);
				pm.setTypePerson(PersonModel.TYPE_CADRE_ADMIN);
				modelPersons.add(pm);
			}
		}

		model.addAttribute("personList", modelPersons);

		return "admin/listPersons";
	}
*/
	@DeleteMapping(value = "deletePerson/{idPerson}")
	public ResponseEntity<String> delete(@PathVariable int idPerson) {
	    personService.deletePersonnel(Long.valueOf(idPerson));
	    return ResponseEntity.ok("La personne a été supprimée avec succès.");
	}


	












@ExceptionHandler(DuplicateCinException.class)
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
