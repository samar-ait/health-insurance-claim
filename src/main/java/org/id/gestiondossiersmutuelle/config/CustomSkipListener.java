package org.id.gestiondossiersmutuelle.config;

import org.springframework.batch.core.SkipListener;
import org.springframework.stereotype.Component;

@Component
public class CustomSkipListener implements SkipListener<Object, Object> {

    @Override
    public void onSkipInRead(Throwable t) {
        System.out.println("Item skipped during read: " + t.getMessage());
    }

    @Override
    public void onSkipInWrite(Object item, Throwable t) {
        System.out.println("Item skipped during write: " + item + " due to " + t.getMessage());
    }

    @Override
    public void onSkipInProcess(Object item, Throwable t) {
        System.out.println("Item skipped during processing: " + item + " due to " + t.getMessage());
    }
}
