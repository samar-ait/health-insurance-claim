package org.id.gestiondossiersmutuelle.reader;

import org.apache.poi.ss.usermodel.*;
import org.id.gestiondossiersmutuelle.model.MedicamentReferentiel;
import org.id.gestiondossiersmutuelle.repository.MedicamentReferentielRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.InputStream;

@Component
public class ExcelReader {

    @Autowired
    private MedicamentReferentielRepository repository;

    public void readAndSaveToDatabase() throws Exception {
        // Load the file from the resources folder
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("medicaments2014.xlsx");

        if (inputStream == null) {
            throw new IllegalArgumentException("File not found in resources: ");
        }

        Workbook workbook = WorkbookFactory.create(inputStream);
        Sheet sheet = workbook.getSheetAt(0);

        for (Row row : sheet) {
            if (row.getRowNum() == 0) {
                // Skip header row
                continue;
            }

            // Map Excel row data to MedicamentReferentiel object
            MedicamentReferentiel medicament = new MedicamentReferentiel();
            medicament.setCodeBarre(row.getCell(0).getStringCellValue());
            medicament.setNomMedicament(row.getCell(1).getStringCellValue());
            medicament.setTypeMedicament(row.getCell(2).getStringCellValue());
            medicament.setPrixMedicament(row.getCell(3).getNumericCellValue());

            // Save to database
            repository.save(medicament);
        }

        workbook.close();
        inputStream.close();
    }
}
