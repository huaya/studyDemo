package com.maxlong.study.servlet;

import javax.servlet.*;
import java.io.IOException;

/**
 * @describe：
 * @author： ma.xl
 * @datetime： 2019-2-14 11:06
 */
public class ServletDemo implements Servlet {

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {

    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {

    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
