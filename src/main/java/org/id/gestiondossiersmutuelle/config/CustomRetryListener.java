package org.id.gestiondossiersmutuelle.config;

import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.RetryListener;
import org.springframework.stereotype.Component;

@Component
public class CustomRetryListener implements RetryListener {

    @Override
    public <T, E extends Throwable> boolean open(RetryContext context, RetryCallback<T, E> callback) {
        System.out.println("Retry process started.");
        return true;
    }

    @Override
    public <T, E extends Throwable> void close(RetryContext context, RetryCallback<T, E> callback, Throwable throwable) {
        if (throwable == null) {
            System.out.println("Retry process completed successfully.");
        } else {
            System.out.println("Retry process failed after " + context.getRetryCount() + " attempts. Last error: "
                    + throwable.getClass().getSimpleName() + " - " + throwable.getMessage());
        }
    }

    @Override
    public <T, E extends Throwable> void onError(RetryContext context, RetryCallback<T, E> callback, Throwable throwable) {
        System.out.println("Retry attempt " + context.getRetryCount() + " failed. Error: " + throwable.getClass().getSimpleName() + " - " + throwable.getMessage());
    }
}
