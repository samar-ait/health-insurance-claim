package org.id.gestiondossiersmutuelle.processor;

import org.id.gestiondossiersmutuelle.dto.DossierMutuelleDTO;
import org.id.gestiondossiersmutuelle.model.Assure;
import org.id.gestiondossiersmutuelle.model.DossierMutuelle;
import org.id.gestiondossiersmutuelle.processor.ConsultationProcessor;
import org.id.gestiondossiersmutuelle.processor.TotalRemboursementProcessor;
import org.id.gestiondossiersmutuelle.processor.TraitementMappingProcessor;
import org.id.gestiondossiersmutuelle.processor.TraitementRemboursementProcessor;
import org.id.gestiondossiersmutuelle.repository.AssureRepository;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CalculProcessor implements ItemProcessor<DossierMutuelleDTO, DossierMutuelle> {

    private final ConsultationProcessor consultationProcessor;
    private final TraitementMappingProcessor traitementMappingProcessor;
    private final TraitementRemboursementProcessor traitementRemboursementProcessor;
    private final TotalRemboursementProcessor totalRemboursementProcessor;
    private final AssureRepository assureRepository;


    @Autowired
    public CalculProcessor(ConsultationProcessor consultationProcessor,
                           TraitementMappingProcessor traitementMappingProcessor,
                           TraitementRemboursementProcessor traitementRemboursementProcessor,
                           TotalRemboursementProcessor totalRemboursementProcessor, AssureRepository assureRepository) {
        this.consultationProcessor = consultationProcessor;
        this.traitementMappingProcessor = traitementMappingProcessor;
        this.traitementRemboursementProcessor = traitementRemboursementProcessor;
        this.totalRemboursementProcessor = totalRemboursementProcessor;
        this.assureRepository = assureRepository;
    }

    @Override
    public DossierMutuelle process(DossierMutuelleDTO dossierDTO) throws Exception {
        // Mapper les traitements
        dossierDTO = traitementMappingProcessor.process(dossierDTO);

        // Find or create the Assure
        Assure assure;
        Optional<Assure> optionalAssure = assureRepository.findByNumeroAffiliation(dossierDTO.getNumeroAffiliation());

        if (optionalAssure.isPresent()) {
            assure = optionalAssure.get();
        } else {
            Assure newAssure = new Assure();
            newAssure.setNom(dossierDTO.getNomAssure());
            newAssure.setNumeroAffiliation(dossierDTO.getNumeroAffiliation());
            newAssure.setImmatriculation(dossierDTO.getImmatriculation());
            assure = assureRepository.save(newAssure); // Save and assign
        }

        // Calculer le remboursement pour la consultation
        double consultationReimbursement = consultationProcessor.process(dossierDTO.getPrixConsultation());

        // Calculer le remboursement des traitements
        double traitementReimbursement = traitementRemboursementProcessor.process(dossierDTO);

        // Ajouter les totaux
        double totalReimbursement = totalRemboursementProcessor.process(consultationReimbursement, traitementReimbursement);

        // Transformer en entité DossierMutuelle
        DossierMutuelle dossier = new DossierMutuelle();
        dossier.setNomBeneficiaire(dossierDTO.getNomBeneficiaire());
        dossier.setDateDepotDossier(dossierDTO.getDateDepotDossier());
        dossier.setMontantTotal(dossierDTO.getMontantTotalFrais());
        dossier.setTotalRembourse(totalReimbursement);
        dossier.setAssure(assure);

        return dossier;
    }
}
