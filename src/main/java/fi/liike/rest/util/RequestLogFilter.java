package fi.liike.rest.util;

import fi.liike.rest.auth.JwtRequestFilter;
import org.slf4j.MDC;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class RequestLogFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        if (servletRequest instanceof HttpServletRequest) {
            HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
            JwtRequestFilter jwtRequestFilter = new JwtRequestFilter();
            String userName = jwtRequestFilter.getUserName(httpServletRequest);
            MDC.put("user_name", userName == null ? "unknown user" : userName);
        }
        try {
            filterChain.doFilter(servletRequest, servletResponse);
        } finally {
            MDC.remove("user_name");
        }
    }

    @Override
    public void destroy() {

    }
}
