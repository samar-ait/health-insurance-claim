package org.id.gestiondossiersmutuelle.processor;

import org.id.gestiondossiersmutuelle.dto.DossierMutuelleDTO;
import org.id.gestiondossiersmutuelle.model.Assure;
import org.id.gestiondossiersmutuelle.model.DossierMutuelle;
import org.id.gestiondossiersmutuelle.repository.AssureRepository;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DossierMutuelleProcessor implements ItemProcessor<DossierMutuelleDTO, DossierMutuelle> {

    @Autowired
    private AssureRepository assureRepository;

    @Override
    public DossierMutuelle process(DossierMutuelleDTO dossierDTO) throws Exception {
        // Find or create the Assure
        Assure assure = assureRepository.findByNumeroAffiliation(dossierDTO.getNumeroAffiliation())
                .orElseGet(() -> {
                    // Create a new Assure if not found
                    Assure newAssure = new Assure();
                    newAssure.setNom(dossierDTO.getNomAssure());
                    newAssure.setNumeroAffiliation(dossierDTO.getNumeroAffiliation());
                    newAssure.setImmatriculation(dossierDTO.getImmatriculation());
                    return assureRepository.save(newAssure); // Persist the new Assure
                });

        // Transform DTO to entity
        DossierMutuelle dossier = new DossierMutuelle();
        double montantTotale = dossierDTO.getMontantTotalFrais() + dossierDTO.getPrixConsultation();
        dossier.setNomBeneficiaire(dossierDTO.getNomBeneficiaire());
        dossier.setDateDepotDossier(dossierDTO.getDateDepotDossier());
        dossier.setMontantTotal(montantTotale);
        dossier.setTotalRembourse(dossierDTO.getTotalRembourse());
        dossier.setAssure(assure); // Link the Assure to the dossier

        return dossier;
    }
}
