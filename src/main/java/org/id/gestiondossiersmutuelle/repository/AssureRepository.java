package org.id.gestiondossiersmutuelle.repository;

import org.id.gestiondossiersmutuelle.model.Assure;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AssureRepository extends JpaRepository<Assure, Long>  {
    Optional<Assure> findByNumeroAffiliation(String numeroAffiliation);
}
