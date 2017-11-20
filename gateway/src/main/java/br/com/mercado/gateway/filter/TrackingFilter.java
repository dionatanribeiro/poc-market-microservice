package br.com.mercado.gateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sun.misc.Request;

import java.util.UUID;

@Component
public class TrackingFilter extends ZuulFilter {

    private static final int FILTER_ORDER = 1;
    private static final boolean SHOULD_FILTER = true;
    private static final Logger logger = LoggerFactory.getLogger(TrackingFilter.class);

    @Autowired
    private FilterUtils filterUtils;

    @Override
    public String filterType() {
        return filterUtils.PRE_FILTER_TYPE;
    }

    @Override
    public int filterOrder() {
        return FILTER_ORDER;
    }

    @Override
    public boolean shouldFilter() {
        return SHOULD_FILTER;
    }

    private boolean isCorrelationIdPresent() {
        if (filterUtils.getCorrelationId() != null) {
            return true;
        }
        return false;
    }

    public String generateCorrelationId() {
        return UUID.randomUUID().toString();
    }

    public Object run() {
        if (isCorrelationIdPresent()) {
            logger.debug("is-correlation-id found in tracking filter: {}", filterUtils.getCorrelationId());
        } else {
            filterUtils.setCorrelationId(generateCorrelationId());
            logger.debug("is-correlation-id generated in tracking filter: {}", filterUtils.getCorrelationId());
        }

        RequestContext context = RequestContext.getCurrentContext();
        logger.debug("Processing incoming request for {}", context.getRequest().getRequestURI());

        return null;
    }

}
