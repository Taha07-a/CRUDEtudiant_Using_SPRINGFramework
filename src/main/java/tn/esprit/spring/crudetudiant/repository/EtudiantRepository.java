package tn.esprit.spring.crudetudiant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.spring.crudetudiant.entities.Etudiant;

public interface EtudiantRepository extends JpaRepository<Etudiant, Long> {
}
