package org.id.gestiondossiersmutuelle.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString

public class Traitement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String codeBarre;
    private boolean existe;
    private String nomMedicament;
    private String typeMedicament;
    private double prixMedicament;

    @ManyToOne
    @JoinColumn(name = "dossier_mutuelle_id", nullable = false)
    private DossierMutuelle dossierMutuelle;
}
