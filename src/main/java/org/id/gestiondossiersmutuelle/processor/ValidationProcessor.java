package org.id.gestiondossiersmutuelle.processor;

import org.id.gestiondossiersmutuelle.dto.DossierMutuelleDTO;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class ValidationProcessor implements ItemProcessor<DossierMutuelleDTO, DossierMutuelleDTO> {

    @Override
    public DossierMutuelleDTO process(DossierMutuelleDTO dossier) throws Exception {
        // Validate nomAssure (name of the insured)
        if (dossier.getNomAssure() == null || dossier.getNomAssure().isEmpty()) {
            throw new IllegalArgumentException("Nom de l'assuré est vide !");
        }

        // Validate numeroAffiliation (affiliation number)
        if (dossier.getNumeroAffiliation() == null || dossier.getNumeroAffiliation().isEmpty()) {
            throw new IllegalArgumentException("Numéro d'affiliation est vide !");
        }

        // Validate prixConsultation (price of consultation)
        if (dossier.getPrixConsultation() <= 0) {
            throw new IllegalArgumentException("Prix de la consultation doit être positif !");
        }

        // Validate montantTotalFrais (total cost)
        if (dossier.getMontantTotalFrais() <= 0) {
            throw new IllegalArgumentException("Montant total des frais doit être positif !");
        }

        // Validate traitements (treatments list)
        if (dossier.getTraitements() == null || dossier.getTraitements().isEmpty()) {
            throw new IllegalArgumentException("La liste des traitements est vide !");
        }

        // If all validations pass, return the dossier as-is
        return dossier;
    }
}
