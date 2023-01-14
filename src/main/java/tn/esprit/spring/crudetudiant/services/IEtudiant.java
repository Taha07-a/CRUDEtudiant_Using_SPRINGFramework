package tn.esprit.spring.crudetudiant.services;

import tn.esprit.spring.crudetudiant.entities.Etudiant;

import java.util.List;

public interface IEtudiant {

public List<Etudiant> afficherEtudiants();

public Etudiant ajouterEtudiant(Etudiant e);

public Etudiant modifierEtudiant(Etudiant e);

public void supprimerEtudiant(Long id);

public Etudiant afficherEtudiantById(long id);
}
