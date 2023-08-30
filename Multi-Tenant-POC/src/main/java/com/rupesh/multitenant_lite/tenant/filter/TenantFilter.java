package com.rupesh.multitenant_lite.context;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Order(1)
@Slf4j
public class TenantFilter extends OncePerRequestFilter {


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException
    {
        String tenant = null;
       try {
            tenant = request.getHeader("X-TenantID");
           if (tenant != null) {
               if(tenant.equals("default"))
               {
                   throw new ServletException("Tenant id cannot be default");
               }
               TenantContext.setCurrentTenant(tenant);
               log.info("Setting tenant to " + tenant);
           }else {
                log.info("No tenant found in the request");
                throw new ServletException("No tenant found in the request");
           }
           filterChain.doFilter(request, response);

       }finally {
           TenantContext.clear();
           log.info("Clearing tenant");
       }

    }
}// class