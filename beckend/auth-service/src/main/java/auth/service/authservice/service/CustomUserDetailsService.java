package auth.service.authservice.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import auth.service.authservice.model.Admin;
import auth.service.authservice.model.User;
import auth.service.authservice.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
//	@Autowired
//	private AuthenticationManager authMenager;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User sysUser=userRepository.findByEmail(username);
		if(sysUser == null) {
			throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
		}else {
			return sysUser;
		}
	}

}