package org.id.gestiondossiersmutuelle.config;

import org.id.gestiondossiersmutuelle.dto.DossierMutuelleDTO;
import org.id.gestiondossiersmutuelle.model.DossierMutuelle;
import org.id.gestiondossiersmutuelle.processor.CalculProcessor;
import org.id.gestiondossiersmutuelle.processor.DossierMutuelleProcessor;
import org.id.gestiondossiersmutuelle.processor.ValidationProcessor;
import org.id.gestiondossiersmutuelle.reader.JsonReader;
import org.id.gestiondossiersmutuelle.writer.DossierMutuelleWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.CompositeItemProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

@Configuration
@EnableBatchProcessing
@EnableTransactionManagement
public class BatchConfig {

    private final DossierMutuelleWriter writer;
    private final PlatformTransactionManager transactionManager;

    @Qualifier("batchDataSource")  // Use this to refer to the batch data source
    private final DataSource batchDataSource;

    public BatchConfig(DossierMutuelleWriter writer,
                       @Qualifier("dataSource") DataSource batchDataSource,
                       PlatformTransactionManager transactionManager) {
        this.writer = writer;
        this.batchDataSource = batchDataSource;
        this.transactionManager = transactionManager;
    }

    @Bean
    public JobRepository jobRepository() throws Exception {
        JobRepositoryFactoryBean factoryBean = new JobRepositoryFactoryBean();
        factoryBean.setDataSource(batchDataSource);  // Set batchDataSource explicitly
        factoryBean.setTransactionManager(transactionManager);
        factoryBean.setIsolationLevelForCreate("ISOLATION_READ_COMMITTED");
        factoryBean.setTablePrefix("BATCH_"); // Optional customization
        factoryBean.afterPropertiesSet();
        return factoryBean.getObject();
    }

    @Bean
    public Job processDossiersJob(JobRepository jobRepository, Step processDossiersStep) {
        return new JobBuilder("processDossiersJob", jobRepository)
                .start(processDossiersStep)
                .preventRestart() // Allow restart
                .build();
    }


    @Bean
    public Step processDossiersStep(ItemReader<DossierMutuelleDTO> reader,
                                    CompositeItemProcessor<DossierMutuelleDTO, DossierMutuelle> compositeProcessor,
                                    ItemWriter<DossierMutuelle> writer) throws Exception {
        return new StepBuilder("processDossiersStep", jobRepository())
                .<DossierMutuelleDTO, DossierMutuelle>chunk(10, transactionManager)
                .reader(reader)
                .processor(compositeProcessor) // Utilisation du CompositeItemProcessor
                .writer(writer)
                .build();
    }

    @Bean
    public ItemReader<DossierMutuelleDTO> jsonItemReader() throws IOException {
        List<DossierMutuelleDTO> dossierList = new JsonReader().readDossierMutuelle();
        return new ItemReader<>() {
            private final Iterator<DossierMutuelleDTO> iterator = dossierList.iterator();

            @Override
            public DossierMutuelleDTO read() {
                return iterator.hasNext() ? iterator.next() : null;
            }
        };
    }
    @Bean
    public CompositeItemProcessor<DossierMutuelleDTO, DossierMutuelle> compositeProcessor(
            ValidationProcessor validationProcessor,
            CalculProcessor calculProcessor) {

        CompositeItemProcessor<DossierMutuelleDTO, DossierMutuelle> compositeProcessor = new CompositeItemProcessor<>();
        compositeProcessor.setDelegates(Arrays.asList(validationProcessor, calculProcessor));
        return compositeProcessor;
    }

    @Bean
    public DossierMutuelleProcessor dossierMutuelleProcessor() {
        return new DossierMutuelleProcessor();
    }
}
