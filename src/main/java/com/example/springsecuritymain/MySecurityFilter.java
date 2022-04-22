package com.example.springsecuritymain;

import javax.servlet.*;
import java.io.IOException;

public class MySecurityFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        //Before filter
        filterChain.doFilter(servletRequest,servletResponse);
        //After filter

    }
}
