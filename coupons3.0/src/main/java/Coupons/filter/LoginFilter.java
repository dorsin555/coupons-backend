package Coupons.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;

import Coupons.session.SessionContext;

public class LoginFilter implements Filter{
	
	private SessionContext sessionContext;

	public LoginFilter(SessionContext sessionContext) {
		super();
		this.sessionContext = sessionContext;
	}
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest hsreq = (HttpServletRequest) request;
		String token = hsreq.getHeader("token");
		System.out.println("-----------" + token + "-----------");
		
		try {
			if(hsreq.getMethod().equals("OPTIONS")) {
				System.out.println("preflight");
				chain.doFilter(hsreq, response);
				return;
			}
			
			if(token != null && sessionContext.getSession(token) != null) {
				System.out.println(token);
				chain.doFilter(hsreq, response);
				return;
			}
			
			HttpServletResponse hsresp = (HttpServletResponse) response;
			System.out.println("Error -------------------------");
			hsresp.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
			hsresp.sendError(HttpStatus.UNAUTHORIZED.value(), "You are Not logged in");
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
