package org.id.gestiondossiersmutuelle.processor;

import org.id.gestiondossiersmutuelle.dto.DossierMutuelleDTO;
import org.id.gestiondossiersmutuelle.dto.TraitementDTO;
import org.id.gestiondossiersmutuelle.model.MedicamentReferentiel;
import org.id.gestiondossiersmutuelle.repository.MedicamentReferentielRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TraitementRemboursementProcessor {

    @Autowired
    private MedicamentReferentielRepository medicamentReferentielRepository;

    public double process(DossierMutuelleDTO dossierDTO) throws Exception {
        double totalReimbursement = 0.0;

        // Loop through each traitement (treatment) in the dossier
        for (TraitementDTO traitement : dossierDTO.getTraitements()) {

            // Log to check the 'existe' value of each treatment
            System.out.println("Traitement: " + traitement.getNomMedicament() + ", Existe: " + traitement.isExiste());

            // Only process the treatment if it exists (traitement.isExiste() is true)
            if (traitement.isExiste()) {
                // Find the medicament in the referentiel by its codeBarre (barcode)
                MedicamentReferentiel medicament = medicamentReferentielRepository.findByCodeBarre(traitement.getCodeBarre())
                        .orElseThrow(() -> new IllegalArgumentException("Médicament non trouvé: " + traitement.getCodeBarre()));

                // Calculate the reimbursement for this treatment
                totalReimbursement += medicament.getPrixMedicament() * medicament.getTauxRemboursement() ; // Assuming TauxRemboursement is a percentage
            } else {
                System.out.println("Traitement " + traitement.getNomMedicament() + " will not be processed as it is marked as 'existe = false'");
            }
        }

        // Return the total reimbursement for all the treatments
        return totalReimbursement;
    }
}
