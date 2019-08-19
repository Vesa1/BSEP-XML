package auth.service.authservice.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;

public class TokenBasedAuthentication extends AbstractAuthenticationToken{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private String token;
	private UserDetails principle;
	
	public TokenBasedAuthentication(UserDetails userDetails) {
		// TODO Auto-generated constructor stub
		super(userDetails.getAuthorities());
		this.principle=userDetails;
	}

	@Override
	public Object getCredentials() {
		// TODO Auto-generated method stub
		return token;
	}

	@Override
	public Object getPrincipal() {
		// TODO Auto-generated method stub
		return principle;
	}
	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	@Override
	public boolean isAuthenticated() {
		return true;
	}

	
}