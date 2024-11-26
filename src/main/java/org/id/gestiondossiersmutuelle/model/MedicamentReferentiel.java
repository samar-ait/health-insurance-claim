package org.id.gestiondossiersmutuelle.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedicamentReferentiel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String codeBarre;
    private String nomMedicament;
    private String typeMedicament;
    private double prixMedicament;
    private double tauxRemboursement;
}
