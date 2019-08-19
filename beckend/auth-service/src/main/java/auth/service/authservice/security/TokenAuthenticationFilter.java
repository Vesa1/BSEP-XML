package auth.service.authservice.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

public class TokenAuthenticationFilter extends OncePerRequestFilter {
	
	private UserDetailsService userDetailsService;
	
	
	private JwtTokenProvider tokenProvider;
	
	public TokenAuthenticationFilter(JwtTokenProvider tokenHelper, UserDetailsService userDetailsService) {
		this.tokenProvider = tokenHelper;
		this.userDetailsService = userDetailsService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String authToken=tokenProvider.getToken(request);
		String username;
		
		if(authToken!=null){
			username = tokenProvider.getUsernameFromToken(authToken);
			
			if (username != null) {
				// uzmi user-a na osnovu username-a
				UserDetails userDetails = userDetailsService.loadUserByUsername(username);
				
				// proveri da li je prosledjeni token validan
					
				// kreiraj autentifikaciju
				TokenBasedAuthentication authentication = new TokenBasedAuthentication(userDetails);
				authentication.setToken(authToken);
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		}
		filterChain.doFilter(request, response);
	}

}