package ir.dotin.core.filter;


import com.sun.jsf.model.SecurityHeader;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {
        "/accounting/*",
        "/budget/*",
        "/common/*",
        "/generic/*",
        "/treasury/*",
        "/workgroup/*",
        "/home.htm",
        "/exception.htm",
        "/password.htm",
        "/layout/*",
        "/menu/menu.htm",
        "/change-password.htm",
        "chooseOrgPage.xhtml",
        "/top.htm"
})
public class RestrictFilter implements Filter {

    private static final String targetEncoding = "UTF-8";

    public void init(FilterConfig filterConfig) throws ServletException {
    }


    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        request.setCharacterEncoding(targetEncoding);
        response.setCharacterEncoding(targetEncoding);

        HttpSession session = request.getSession(false);


        if (session == null || session.getAttribute("username") == null) {
            response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/index.html"));
        } else {
            SecurityHeader securityHeader = new SecurityHeader();
            securityHeader.apply((HttpServletRequest) request, (HttpServletResponse) response, filterChain);
        }
    }

    public void destroy() {

    }
}