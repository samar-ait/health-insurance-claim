package org.id.gestiondossiersmutuelle.repository;

import org.id.gestiondossiersmutuelle.model.MedicamentReferentiel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MedicamentReferentielRepository extends JpaRepository<MedicamentReferentiel, Long> {
    Optional<MedicamentReferentiel> findByCodeBarre(String codeBarre);
}
