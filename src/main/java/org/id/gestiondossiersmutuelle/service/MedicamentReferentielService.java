package org.id.gestiondossiersmutuelle.service;

import org.apache.poi.ss.usermodel.*;
import org.id.gestiondossiersmutuelle.model.MedicamentReferentiel;
import org.id.gestiondossiersmutuelle.repository.MedicamentReferentielRepository;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
public class MedicamentReferentielService {

    private final MedicamentReferentielRepository repository;

    public MedicamentReferentielService(MedicamentReferentielRepository repository) {
        this.repository = repository;
    }

    public void importFromExcel(InputStream inputStream) throws Exception {
        Workbook workbook = WorkbookFactory.create(inputStream);
        Sheet sheet = workbook.getSheetAt(0);

        for (Row row : sheet) {
            if (row.getRowNum() == 0) {
                // Skip header row
                continue;
            }

            MedicamentReferentiel medicament = new MedicamentReferentiel();
            medicament.setCodeBarre(row.getCell(0).getStringCellValue());
            medicament.setNomMedicament(row.getCell(1).getStringCellValue());
            medicament.setTypeMedicament(row.getCell(2).getStringCellValue());
            medicament.setPrixMedicament(row.getCell(3).getNumericCellValue());

            repository.save(medicament);
        }

        workbook.close();
    }
}
