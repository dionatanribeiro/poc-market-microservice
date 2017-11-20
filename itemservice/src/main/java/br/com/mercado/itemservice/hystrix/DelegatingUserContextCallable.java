package br.com.mercado.itemservice.hystrix;

import br.com.mercado.itemservice.util.UserContext;
import br.com.mercado.itemservice.util.UserContextHolder;

import java.util.concurrent.Callable;

public class DelegatingUserContextCallable<T> implements Callable<T> {

    private final Callable<T> delegate;

    private UserContext originalUserContext;

    public DelegatingUserContextCallable(Callable<T> delegate, UserContext context) {
        this.delegate = delegate;
        this.originalUserContext = context;
    }

    @Override
    public T call() throws Exception {
        UserContextHolder.setContext(originalUserContext);

        try {
            return delegate.call();
        } finally {
            this.originalUserContext = null;
        }
    }

    public static <T> Callable<T> create(Callable<T> delegate, UserContext userContext) {
        return new DelegatingUserContextCallable<>(delegate, userContext);
    }


}

