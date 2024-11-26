package org.id.gestiondossiersmutuelle.processorTest;

import org.id.gestiondossiersmutuelle.dto.DossierMutuelleDTO;
import org.id.gestiondossiersmutuelle.dto.TraitementDTO;
import org.id.gestiondossiersmutuelle.processor.ValidationProcessor;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class ValidationProcessorTest {

    private final ValidationProcessor processor = new ValidationProcessor();

    @Test
    void testValidDossier() throws Exception {
        // Create a valid DossierMutuelleDTO
        DossierMutuelleDTO validDossier = new DossierMutuelleDTO();
        validDossier.setNomAssure("Ibrahimi");
        validDossier.setNumeroAffiliation("AFF123456");
        validDossier.setPrixConsultation(50.0);
        validDossier.setMontantTotalFrais(150.0);
        validDossier.setTraitements(Collections.singletonList(new TraitementDTO()));

        // Should not throw an exception
        assertDoesNotThrow(() -> processor.process(validDossier));
    }

    @Test
    void testInvalidNomAssure() {
        // Create an invalid DossierMutuelleDTO
        DossierMutuelleDTO invalidDossier = new DossierMutuelleDTO();
        invalidDossier.setNomAssure("");
        invalidDossier.setNumeroAffiliation("AFF123456");
        invalidDossier.setPrixConsultation(50.0);
        invalidDossier.setMontantTotalFrais(150.0);
        invalidDossier.setTraitements(Collections.singletonList(new TraitementDTO()));

        // Should throw an exception
        Exception exception = assertThrows(IllegalArgumentException.class, () -> processor.process(invalidDossier));
        assertEquals("Nom de l'assuré est vide !", exception.getMessage());
    }
}
