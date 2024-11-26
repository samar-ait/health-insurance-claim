package org.id.gestiondossiersmutuelle.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Assure {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String numeroAffiliation;
    private String immatriculation;

    @OneToMany(mappedBy = "assure", cascade = CascadeType.ALL)
    private List<Beneficiaire> beneficiaires;
}
