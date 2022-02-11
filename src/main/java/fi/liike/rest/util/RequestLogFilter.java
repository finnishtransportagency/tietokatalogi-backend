package fi.liike.rest.util;

import fi.liike.rest.auth.Constants;
import fi.liike.rest.auth.JwtRequestFilter;
import fi.liike.rest.auth.UserInfo;
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
            JwtRequestFilter jwtRequestFilter = new JwtRequestFilter();
            UserInfo userInfo = jwtRequestFilter.getUserInfo((HttpServletRequest) servletRequest);
            MDC.put("user_name", userInfo.getUserName() == null ? "unknown user" : userInfo.getUserName());
            servletRequest.setAttribute(Constants.JWT_USER_GROUPS_ATTRIBUTE, userInfo.getUserGroups());
            servletRequest.setAttribute(Constants.JWT_USER_NAME_ATTRIBUTE, userInfo.getUserName());
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
