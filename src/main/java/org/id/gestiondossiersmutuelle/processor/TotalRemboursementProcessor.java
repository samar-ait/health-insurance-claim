package org.id.gestiondossiersmutuelle.processor;

import org.springframework.stereotype.Component;

@Component
public class TotalRemboursementProcessor  {

    public double process(double consultationReimbursement, double traitementReimbursement) {
        return consultationReimbursement + traitementReimbursement;
    }
}

