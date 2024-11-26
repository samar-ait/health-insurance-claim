package org.id.gestiondossiersmutuelle.processor;

import org.id.gestiondossiersmutuelle.dto.DossierMutuelleDTO;
import org.id.gestiondossiersmutuelle.dto.TraitementDTO;
import org.id.gestiondossiersmutuelle.model.MedicamentReferentiel;
import org.id.gestiondossiersmutuelle.repository.MedicamentReferentielRepository;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TraitementRemboursementProcessor {

    @Autowired
    private MedicamentReferentielRepository medicamentReferentielRepository;

    public double process(DossierMutuelleDTO dossierDTO) throws Exception {
        double totalReimbursement = 0.0;
        for (TraitementDTO traitement : dossierDTO.getTraitements()) {
            MedicamentReferentiel medicament = medicamentReferentielRepository.findByCodeBarre(traitement.getCodeBarre())
                    .orElseThrow(() -> new IllegalArgumentException("Médicament non trouvé: " + traitement.getCodeBarre()));

            totalReimbursement += medicament.getPrixMedicament() * (medicament.getTauxRemboursement() / 100);
        }
        return totalReimbursement;
    }
}
