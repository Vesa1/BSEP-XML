package auth.service.authservice.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


@Component
public class JwtTokenProvider {
	
	@Value("jwtAuthSecretKey")
	private String SECRET;
	
	@Value("864000")
	private int expiresIn;
	
	@Value("Authorization")
	private String AUTH_HEADER;
	
	@Autowired
	private TimeProvider timeProvider;
	
	private SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS512;
	
	public String generateToken(String username) {
		
		return Jwts.builder()
				.setSubject(username)
				.setIssuedAt(timeProvider.now())
				.setExpiration(timeProvider.expiresDate(expiresIn))
				.setHeaderParam("typ", "JWT")
				.signWith(SIGNATURE_ALGORITHM, SECRET)
				.compact();	
	}
	
	private Claims getAllClaimsFromToken(String token) {
		Claims claims;
		try {
			claims = Jwts.parser()
					.setSigningKey(SECRET)
					.parseClaimsJws(token)
					.getBody();
		} catch (Exception e) {
			claims = null;
		}
		return claims;
	}
	
	
	public String getUsernameFromToken(String token) {
		String username;
		try {
			final Claims claims = this.getAllClaimsFromToken(token);
			username = claims.getSubject();
		} catch (Exception e) {
			username = null;
		}
		return username;
	}
	
	public String getAuthHeaderFromHeader(HttpServletRequest request) {
		return request.getHeader(AUTH_HEADER);
	}
	
	public String getToken(HttpServletRequest request) {
		String authHeader = getAuthHeaderFromHeader(request);

		if (StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer ")) {
			return authHeader.substring(7);
		}

		return null;
	}
	
}
