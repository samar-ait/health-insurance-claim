package org.id.gestiondossiersmutuelle.repository;

import org.id.gestiondossiersmutuelle.model.Beneficiaire;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BeneficiaireRepository extends JpaRepository<Beneficiaire,Long> {
}
