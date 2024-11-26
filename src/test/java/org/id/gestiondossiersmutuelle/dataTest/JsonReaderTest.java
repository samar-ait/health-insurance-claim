package org.id.gestiondossiersmutuelle.dataTest;

import org.id.gestiondossiersmutuelle.dto.DossierMutuelleDTO;
import org.id.gestiondossiersmutuelle.reader.JsonReader;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

public class JsonReaderTest {
    @Test
    // Main method to read the data and display it on the console
    public void testJsonReader() {
        JsonReader jsonReader = new JsonReader();

        try {
            // Read the DossierMutuelleDTO objects
            List<DossierMutuelleDTO> dossiers = jsonReader.readDossierMutuelle();
            System.out.println("Dossiers Mutuelle:");
            for (DossierMutuelleDTO dossier : dossiers) {
                System.out.println(dossier);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
