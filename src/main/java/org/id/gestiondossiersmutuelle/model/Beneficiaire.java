package org.id.gestiondossiersmutuelle.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Beneficiaire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String lienParente;

    @ManyToOne
    @JoinColumn(name = "assure_id", nullable = false)
    private Assure assure;


}
