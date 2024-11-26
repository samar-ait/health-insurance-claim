package org.id.gestiondossiersmutuelle.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;
import java.time.LocalDate;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DossierMutuelle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "assure_id", nullable = false)
    private Assure assure;

    private String nomBeneficiaire;
    private LocalDate dateDepotDossier;
    private double montantTotal;
    private double totalRembourse;

    //@OneToMany(mappedBy = "dossierMutuelle", cascade = CascadeType.ALL)
    //private List<Traitement> traitements;
}
