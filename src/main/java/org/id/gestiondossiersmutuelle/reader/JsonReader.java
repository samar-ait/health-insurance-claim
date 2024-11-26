package org.id.gestiondossiersmutuelle.reader;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.id.gestiondossiersmutuelle.dto.DossierMutuelleDTO;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class JsonReader {

    private static final String RESOURCE_PATH = "data.json";  // Path to your JSON file in resources

    // Method to read JSON and map it to a list of DossierMutuelleDTO objects
    public List<DossierMutuelleDTO> readDossierMutuelle() throws IOException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(RESOURCE_PATH);

        if (inputStream == null) {
            throw new IOException("File not found in resources: " + RESOURCE_PATH);
        }

        // Create ObjectMapper and register the JavaTimeModule
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule()); // Register the module to handle LocalDate

        return objectMapper.readValue(inputStream, new TypeReference<List<DossierMutuelleDTO>>(){});
    }


}
