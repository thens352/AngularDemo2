package com.kayi.qrvale.filter;

import java.io.IOException;

import javax.faces.application.ResourceHandler;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(filterName = "loginFilter", urlPatterns = { "/user/*" })
public class LoginFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		try {
			HttpServletRequest req = (HttpServletRequest) request;
			HttpServletResponse res = (HttpServletResponse) response;
			HttpSession ses = req.getSession(false);
			String reqURI = req.getRequestURI();
			boolean resourceRequest = req.getRequestURI().startsWith(
					req.getContextPath() + "/faces"
							+ ResourceHandler.RESOURCE_IDENTIFIER);
			if (reqURI.indexOf("/login.jsf") >= 0
					|| reqURI.indexOf("/adminPanel/") >= 0
					|| (ses != null && ses.getAttribute("ID") != null)) {
				if (!resourceRequest) { // Prevent restricted pages from being
										// cached.
					res.setHeader("Cache-Control",
							"no-cache, no-store, must-revalidate"); // HTTP 1.1.
					res.setDateHeader("Expires", 0); // Proxies.
				}
				chain.doFilter(request, response);
			} else
				res.sendRedirect(req.getContextPath() + "/login.jsf");
		} catch (Throwable t) {
			System.out.println(t.getMessage());
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
