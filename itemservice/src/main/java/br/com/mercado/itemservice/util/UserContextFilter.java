package br.com.mercado.itemservice.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class UserContextFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(UserContextFilter.class);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        UserContextHolder.getContext().setCorrelationId(request.getHeader(UserContext.CORRELATION_ID));
        UserContextHolder.getContext().setUserId(request.getHeader(UserContext.USER_ID));
        UserContextHolder.getContext().setAuthToken(request.getHeader(UserContext.AUTH_TOKEN));

        logger.debug("UserContextFilter CorrelationId: {}", UserContextHolder.getContext().getCorrelationId());
//        logger.debug("UserContextFilter UserId: {}", UserContextHolder.getContext().getUserId());
        logger.debug("UserContextFilter AuthToken: {}", UserContextHolder.getContext().getAuthToken());

        filterChain.doFilter(request, servletResponse);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
