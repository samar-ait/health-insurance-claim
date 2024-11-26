package org.id.gestiondossiersmutuelle.dataTest;

import org.apache.poi.ss.usermodel.*;
import org.junit.jupiter.api.Test;

import java.io.InputStream;

public class ExcelReader {

    @Test
    public void testReadFirst50Rows() throws Exception {
        // Load the file from the resources folder
        InputStream fis = getClass().getClassLoader().getResourceAsStream("medicaments2014.xlsx");

        if (fis == null) {
            throw new IllegalArgumentException("File not found in resources: medicaments2014.xlsx");
        }

        Workbook workbook = WorkbookFactory.create(fis);
        Sheet sheet = workbook.getSheetAt(0);

        int rowCount = 0; // Counter to keep track of rows processed
        for (Row row : sheet) {
            if (rowCount >= 50) {
                break; // Stop processing after 50 rows
            }
            for (Cell cell : row) {
                System.out.print(cell.toString() + "\t");
            }
            System.out.println();
            rowCount++;
        }

        workbook.close();
        fis.close();
    }
}
