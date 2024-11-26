package org.id.gestiondossiersmutuelle.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DossierMutuelleDTO {

    private String nomAssure;
    private String numeroAffiliation;
    private String immatriculation;
    private String lienParente;
    private double montantTotalFrais;
    private double prixConsultation;
    private int nombrePiecesJointes;
    private String nomBeneficiaire;
    private LocalDate dateDepotDossier;
    private double montantTotal;
    private double totalRembourse;
    private List<TraitementDTO> traitements;
}
