package auth.service.authservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import auth.service.authservice.model.Address;
import auth.service.authservice.model.Authority;
import auth.service.authservice.model.User;
import auth.service.authservice.repository.AddressRepository;
import auth.service.authservice.repository.RoleRepository;
import auth.service.authservice.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	public boolean findByEmail(String email) {
		// TODO Auto-generated method stub
		User user = userRepository.findByEmail(email);
		if(user != null)
			return true;
		return false;
	}

	public Authority findByName(String string) {
		
		return roleRepository.findByName(string);
	}

	public void registerUser(User newUser) {
		// TODO Auto-generated method stub
		userRepository.save(newUser);
	}

	public void saveAddress(Address address) {
		addressRepository.save(address);
	}

	public User getByEmail(String email) {
		// TODO Auto-generated method stub
		return userRepository.findByEmail(email);
	}
}
