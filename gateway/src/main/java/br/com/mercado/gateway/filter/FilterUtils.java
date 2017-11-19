package br.com.mercado.gateway.filter;

import com.netflix.zuul.context.RequestContext;
import org.springframework.stereotype.Component;

@Component
public class FilterUtils {

    public static final String CORRELATION_ID = "is-correlation-id";

    public static final String AUTH_TOKEN = "is-auth-token";

    public static final String USER_ID = "is-user-id";

    public static final String PRE_FILTER_TYPE = "pre";

    public static final String POST_FILTER_TYPE = "post";

    public static final String ROUTE_FILTER_TYPE = "route";

    public String getCorrelationId() {
        RequestContext context = RequestContext.getCurrentContext();

        if (context.getRequest().getHeader(CORRELATION_ID) != null) {
            return context.getRequest().getHeader(CORRELATION_ID);
        } else {
            return context.getZuulRequestHeaders().get(CORRELATION_ID);
        }
    }

    public void setCorrelationId(String correlationId) {
        RequestContext context = RequestContext.getCurrentContext();
        context.addZuulRequestHeader(CORRELATION_ID, correlationId);
    }

    public final String getUserId() {
        RequestContext context = RequestContext.getCurrentContext();

        if (context.getRequest().getHeader(USER_ID) != null) {
            return context.getRequest().getHeader(USER_ID);
        } else {
            return context.getZuulRequestHeaders().get(USER_ID);
        }
    }

    public void setUserId(String userId) {
        RequestContext context = RequestContext.getCurrentContext();
        context.addZuulRequestHeader(USER_ID, userId);
    }

    public final String getAuthToken() {
        RequestContext context = RequestContext.getCurrentContext();
        return context.getRequest().getHeader(AUTH_TOKEN);
    }

}
