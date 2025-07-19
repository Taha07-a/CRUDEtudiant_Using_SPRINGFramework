package tn.esprit.spring.crudetudiant.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.spring.crudetudiant.entities.Etudiant;
import tn.esprit.spring.crudetudiant.entities.Option;  // Added import
import tn.esprit.spring.crudetudiant.repository.EtudiantRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EtudiantServiceImplTest {

    @Mock
    private EtudiantRepository etudiantRepository;

    @InjectMocks
    private EtudiantServiceImpl etudiantService;

    @Test
    public void testAddEtudiant() {
        // Changed constructor and getter to match Etudiant class
        Etudiant sampleEtudiant = new Etudiant(1L, "Taha", "Ahmed Gabsi", Option.TWIN);  // Use correct Option value
        when(etudiantRepository.save(any(Etudiant.class))).thenReturn(sampleEtudiant);
        Etudiant result = etudiantService.ajouterEtudiant(sampleEtudiant);
        assertEquals("Taha", result.getNomEtudiant());  // Changed to getNomEtudiant()
        verify(etudiantRepository, times(1)).save(sampleEtudiant);
    }

}
