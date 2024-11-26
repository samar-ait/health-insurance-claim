package org.id.gestiondossiersmutuelle;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GestionDossiersMutuelleApplication implements CommandLineRunner {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job processDossierMutuelleJob; // The batch job you created

    public static void main(String[] args) {
        SpringApplication.run(GestionDossiersMutuelleApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // Trigger the job
        jobLauncher.run(processDossierMutuelleJob, new org.springframework.batch.core.JobParameters());
    }



}