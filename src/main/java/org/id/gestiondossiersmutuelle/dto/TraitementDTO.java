package org.id.gestiondossiersmutuelle.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TraitementDTO {

    private String codeBarre;
    private boolean existe;
    private String nomMedicament;
    private String typeMedicament;
    private double prixMedicament;
}
