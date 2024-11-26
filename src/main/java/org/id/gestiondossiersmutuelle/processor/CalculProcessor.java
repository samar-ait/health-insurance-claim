package org.id.gestiondossiersmutuelle.processor;

import org.id.gestiondossiersmutuelle.dto.DossierMutuelleDTO;
import org.id.gestiondossiersmutuelle.model.DossierMutuelle;
import org.id.gestiondossiersmutuelle.processor.ConsultationProcessor;
import org.id.gestiondossiersmutuelle.processor.TotalRemboursementProcessor;
import org.id.gestiondossiersmutuelle.processor.TraitementMappingProcessor;
import org.id.gestiondossiersmutuelle.processor.TraitementRemboursementProcessor;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CalculProcessor implements ItemProcessor<DossierMutuelleDTO, DossierMutuelle> {

    private final ConsultationProcessor consultationProcessor;
    private final TraitementMappingProcessor traitementMappingProcessor;
    private final TraitementRemboursementProcessor traitementRemboursementProcessor;
    private final TotalRemboursementProcessor totalRemboursementProcessor;

    @Autowired
    public CalculProcessor(ConsultationProcessor consultationProcessor,
                           TraitementMappingProcessor traitementMappingProcessor,
                           TraitementRemboursementProcessor traitementRemboursementProcessor,
                           TotalRemboursementProcessor totalRemboursementProcessor) {
        this.consultationProcessor = consultationProcessor;
        this.traitementMappingProcessor = traitementMappingProcessor;
        this.traitementRemboursementProcessor = traitementRemboursementProcessor;
        this.totalRemboursementProcessor = totalRemboursementProcessor;
    }

    @Override
    public DossierMutuelle process(DossierMutuelleDTO dossierDTO) throws Exception {
        // Mapper les traitements
        dossierDTO = traitementMappingProcessor.process(dossierDTO);

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

        return dossier;
    }
}
