package org.id.gestiondossiersmutuelle.processorTest;

import org.id.gestiondossiersmutuelle.dto.DossierMutuelleDTO;
import org.id.gestiondossiersmutuelle.dto.TraitementDTO;
import org.id.gestiondossiersmutuelle.model.DossierMutuelle;
import org.id.gestiondossiersmutuelle.processor.*;
import org.id.gestiondossiersmutuelle.repository.MedicamentReferentielRepository;
import org.id.gestiondossiersmutuelle.model.MedicamentReferentiel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class CalculProcessorTest {

    @Autowired
    private CalculProcessor calculProcessor;

    @Test
    void testProcess() throws Exception {
        // Prepare test data
        List<TraitementDTO> traitements = Arrays.asList(
                new TraitementDTO("1234567890", true, "Paracétamol", "Antalgique", 5.0),
                new TraitementDTO("0987654321", false, "Ibuprofène", "Anti-inflammatoire", 8.0)
        );
        DossierMutuelleDTO dossier1 = new DossierMutuelleDTO(
                "Ibrahimi",               // nomAssure
                "AFF123456",              // numeroAffiliation
                "IMM098765",              // immatriculation
                "fils",                   // lienParente
                150.0,                    // montantTotalFrais
                50.0,                     // prixConsultation
                3,                        // nombrePiecesJointes
                "Omar",                   // nomBeneficiaire
                LocalDate.of(2024, 11, 10), // dateDepotDossier
                0.0,                      // montantTotal
                0.0,                      // totalRembourse
                traitements
        );

        // Execute the processor
        DossierMutuelle processedDossier1 = calculProcessor.process(dossier1);
        // Print the processed result
        System.out.println("Processed Dossier: " + processedDossier1);

        // Assertions
        assertEquals(150.0 , processedDossier1.getMontantTotal());
        assertEquals((50.0 * 0.8) + (5.0 * 0.5) , processedDossier1.getTotalRembourse()); // Consultation + treatment reimbursements
    }
}
