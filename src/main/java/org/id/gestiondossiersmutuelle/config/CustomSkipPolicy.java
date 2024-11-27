package org.id.gestiondossiersmutuelle.config;

import org.springframework.batch.core.step.skip.SkipPolicy;

public class CustomSkipPolicy implements SkipPolicy {

    @Override
    public boolean shouldSkip(Throwable t, long skipCount) {
        if (skipCount < 5) {
            System.out.println("Skipping due to exception: " + t.getMessage() + ". Skip count: " + skipCount);
            return true; // Allow skipping
        }
        return false; // Do not skip after 5 failures
    }
}
