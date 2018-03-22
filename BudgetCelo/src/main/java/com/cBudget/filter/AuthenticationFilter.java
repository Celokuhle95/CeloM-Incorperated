package com.cBudget.filter;


import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cBudget.controller.utils.JsfUtil;
import com.cBudget.controller.utils.PasswordUtil;

//@WebFilter(urlPatterns = { "/views/*" })
public class AuthenticationFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("FUCK THIS SHIT");
		HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
		if (PasswordUtil.isAuthenticated()) {
			res.sendRedirect(req.getContextPath() + JsfUtil.redirectable("/login.xhtml"));
		} else {
			chain.doFilter(request, response);
		}
	}

}
