package com.frameworks.core.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author rf
 * @date 2019/10/25
 */
public class XSSFilter implements Filter {


    public void init(FilterConfig filterConfig)throws ServletException {

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)

            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest)request;

        XSSRequestWrapper xssRequestWrapper =new XSSRequestWrapper(req);

        chain.doFilter(xssRequestWrapper,response);

    }

    public void destroy() {

    }
}
