package com.github.nechai.aeroflot.web.filter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;

import java.io.IOException;

@WebFilter(filterName = "Filter",
        urlPatterns = "/*")
public class EncodingFilter implements Filter {
    private static final Logger logger = LogManager.getLogger();
    @Override
    public void init(FilterConfig config) {
        logger.info("Filter init() start");
        ServletContext context = config.getServletContext();
        FilterRegistration registration2 = context.getFilterRegistration("Filter2");
         registration2.addMappingForUrlPatterns(null, true, "/airport", "/flights", "/planes", "/workers", "/Admin","/crew","/add*");
        registration2.addMappingForServletNames(null, true, "/airport", "/flights", "/planes", "/workers", "/Admin","/crew");

        FilterRegistration registration3 = context.getFilterRegistration("Filter3");
        registration3.addMappingForServletNames(null, true, "/Admin");
        registration3.addMappingForUrlPatterns(null, true, "/admin");

        FilterRegistration registration4 = context.getFilterRegistration("Filter4");
        registration4.addMappingForServletNames(null, true, "/airport","/flights","/planes","/workers","/crew");
        registration4.addMappingForUrlPatterns(null, true, "/airport","/flights","/planes","/workers","/crew","/add*");
    }

    @Override
    public void doFilter(ServletRequest rq, ServletResponse rs, FilterChain filterChain) throws IOException, ServletException {
        rq.setCharacterEncoding("UTF8");
        filterChain.doFilter(rq, rs);
        }
    }

