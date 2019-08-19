package auth.service.authservice.security;

import java.io.Serializable;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class TimeProvider implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Date now() {
		return new Date();
	}
	
	public Date expiresDate(int ms) {
		
		return new Date(this.now().getTime() + ms);
	}
}