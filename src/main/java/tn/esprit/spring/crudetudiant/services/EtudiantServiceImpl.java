package tn.esprit.spring.crudetudiant.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.spring.crudetudiant.entities.Etudiant;
import tn.esprit.spring.crudetudiant.repository.EtudiantRepository;

import java.util.List;
@Service
@AllArgsConstructor
public class EtudiantServiceImpl implements IEtudiant{

    EtudiantRepository etudiantRepository;
    @Override
    public List<Etudiant> afficherEtudiants() {
        return etudiantRepository.findAll();
    }

    @Override
    public Etudiant ajouterEtudiant(Etudiant e) {
        return etudiantRepository.save(e);
    }

    @Override
    public Etudiant modifierEtudiant(Etudiant e) {
        return etudiantRepository.save(e);
    }

    @Override
    public void supprimerEtudiant(Long id) {
          etudiantRepository.deleteById(id);
    }

    @Override
    public Etudiant afficherEtudiantById(long id) {
        return etudiantRepository.findById(id).orElse(null);
    }
}
