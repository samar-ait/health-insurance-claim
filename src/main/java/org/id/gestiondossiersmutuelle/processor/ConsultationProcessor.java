package org.id.gestiondossiersmutuelle.processor;

import org.springframework.stereotype.Component;

@Component
public class ConsultationProcessor {

    private static final double CONSULTATION_REIMBURSEMENT_PERCENTAGE = 0.8; // 80%

    public double process(double prixConsultation) {
        return prixConsultation * CONSULTATION_REIMBURSEMENT_PERCENTAGE;
    }
}

