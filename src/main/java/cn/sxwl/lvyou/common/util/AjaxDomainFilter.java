package cn.sxwl.lvyou.common.util;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebFilter(urlPatterns = {"/*"})
public class AjaxDomainFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse httpServletResponse=(HttpServletResponse)servletResponse;
        httpServletResponse.setHeader("Access-Control-Allow-Headers","Content-Type");
        httpServletResponse.setHeader("Access-Control-Allow-Origin","*");
        httpServletResponse.setHeader("Access-Control-Allow-Methods","GET,POST,PUT,DELETE");
        filterChain.doFilter(servletRequest,servletResponse);
    }


    @Override
    public void destroy() {

    }
}
