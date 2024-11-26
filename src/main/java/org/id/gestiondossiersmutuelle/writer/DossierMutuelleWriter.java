package org.id.gestiondossiersmutuelle.writer;

import org.id.gestiondossiersmutuelle.model.DossierMutuelle;
import org.id.gestiondossiersmutuelle.repository.DossierMutuelleRepository;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

@Component
public class DossierMutuelleWriter implements ItemWriter<DossierMutuelle> {

    private final DossierMutuelleRepository dossierMutuelleRepository;

    public DossierMutuelleWriter(DossierMutuelleRepository dossierMutuelleRepository) {
        this.dossierMutuelleRepository = dossierMutuelleRepository;
    }

    @Override
    public void write(Chunk<? extends DossierMutuelle> items) throws Exception {
        dossierMutuelleRepository.saveAll(items.getItems());
    }
}
