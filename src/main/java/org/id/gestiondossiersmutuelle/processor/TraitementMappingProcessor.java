package org.id.gestiondossiersmutuelle.processor;

import org.id.gestiondossiersmutuelle.dto.DossierMutuelleDTO;
import org.id.gestiondossiersmutuelle.dto.TraitementDTO;
import org.id.gestiondossiersmutuelle.model.MedicamentReferentiel;
import org.id.gestiondossiersmutuelle.repository.MedicamentReferentielRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TraitementMappingProcessor {

    @Autowired
    private MedicamentReferentielRepository medicamentReferentielRepository;

    public DossierMutuelleDTO process(DossierMutuelleDTO dossierDTO) throws Exception {
        for (TraitementDTO traitement : dossierDTO.getTraitements()) {
            MedicamentReferentiel medicament = medicamentReferentielRepository.findByCodeBarre(traitement.getCodeBarre())
                    .orElseThrow(() -> new IllegalArgumentException("Médicament non trouvé: " + traitement.getCodeBarre()));
            traitement.setExiste(true);
            traitement.setNomMedicament(medicament.getNomMedicament());
            traitement.setPrixMedicament(medicament.getPrixMedicament());
            traitement.setTypeMedicament(medicament.getTypeMedicament());
        }
        return dossierDTO;
    }
}

