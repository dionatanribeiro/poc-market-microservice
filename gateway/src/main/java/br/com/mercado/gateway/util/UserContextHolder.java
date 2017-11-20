package br.com.mercado.gateway.util;

import org.springframework.util.Assert;

public class UserContextHolder {

    private static final ThreadLocal<UserContext> userContext = new ThreadLocal<>();

    public static final UserContext getContext() {
        UserContext context = userContext.get();

        if (context == null) {
            context = createEmptyContext();
            userContext.set(context);
        }

        return userContext.get();
    }

    public static final UserContext createEmptyContext() {
        return new UserContext();
    }

    public static final void setContext(UserContext context) {
        Assert.notNull(context, "Apenas UserContext não nulo são permitidos.");
        userContext.set(context);
    }

}
