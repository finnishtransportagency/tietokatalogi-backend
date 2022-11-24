package fi.liike.rest.util;

import fi.liike.rest.auth.Constants;
import fi.liike.rest.auth.JwtRequestFilter;
import fi.liike.rest.auth.UserInfo;
import org.slf4j.MDC;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class RequestFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
        throws IOException {
        try {
            if (servletRequest instanceof HttpServletRequest) {
                JwtRequestFilter jwtRequestFilter = new JwtRequestFilter();
                UserInfo userInfo = jwtRequestFilter.getUserInfo((HttpServletRequest) servletRequest);
                MDC.put("user_name", userInfo.getUserName() == null ? "unknown user" : userInfo.getUserName());
                servletRequest.setAttribute(Constants.JWT_USER_GROUPS_ATTRIBUTE, userInfo.getUserGroups());
                servletRequest.setAttribute(Constants.JWT_USER_NAME_ATTRIBUTE, userInfo.getUserName());
            }

            filterChain.doFilter(servletRequest, servletResponse);
        } catch (Exception e) {
            HttpServletResponse res = (HttpServletResponse) servletResponse;
            res.setStatus(500);
            PrintWriter writer = res.getWriter();
            writer.print("Tietokatalogi: virhe (500)");
            writer.flush();
        } finally {
            MDC.remove("user_name");
        }
    }

    @Override
    public void destroy() {

    }
}
