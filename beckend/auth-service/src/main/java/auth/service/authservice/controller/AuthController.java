package auth.service.authservice.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Collections;

import javax.validation.Valid;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import auth.service.authservice.dto.LoggedUserDTO;
import auth.service.authservice.dto.RegistrationDTO;
import auth.service.authservice.model.Address;
import auth.service.authservice.model.User;
import auth.service.authservice.model.UserTokenState;
import auth.service.authservice.security.JwtTokenProvider;
import auth.service.authservice.service.UserService;

@RestController
@RequestMapping("api/auth")
public class AuthController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
    AuthenticationManager authenticationManager;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	
	
	@Autowired
	private JwtTokenProvider  jwtTokenProvider;
	
	@GetMapping("/hello")
    public ResponseEntity get()
    {
		System.out.println("[Auth Demo]");
        return new ResponseEntity(HttpStatus.OK);
    }
	
	@RequestMapping(value = "/registration", 
			method = RequestMethod.POST, 
			consumes =MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> registration(@Valid @RequestBody RegistrationDTO u) {
		System.out.println("[Auth Controller - registration]");
		System.out.println(u);
		System.out.println("Loading...");
		if(!u.getPassword().equals(u.getPasswordR())) {
			System.out.println("Passwords must be equal..");
			return new ResponseEntity<>("Password and Repeated Password must be equal", HttpStatus.BAD_REQUEST);
		}
		
		if(userService.findByEmail(u.getEmail())) {
			System.out.println("Email is wrong...");
			return new ResponseEntity<>("You are already registered!", HttpStatus.BAD_REQUEST);
		}
		System.out.println("Continued..");
		Address address;
		
		try {
			address = getCordinates(u.getAdress(), u.getCity());
			address.setName(u.getAdress());
			userService.saveAddress(address);
String salt = org.springframework.security.crypto.bcrypt.BCrypt.gensalt();
			
			System.out.println("[Auth Controller - registration] Hesiranje lozinke -----------------> ");
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			String hashedPass = org.springframework.security.crypto.bcrypt.BCrypt.hashpw(u.getPassword(), salt);
			System.out.println("hashed " + hashedPass);
			User newUser = new User(u.getName(), u.getSurname(), new byte[10], u.getTelephone(), u.getEmail(), hashedPass, u.getCity(), address, Collections.singleton(userService.findByName("registeredUser")));
			//User newUser = new User(u.getName(), u.getSurname(), new byte[10], u.getTelephone(), u.getEmail(), passwordEncoder.encode(u.getPassword()), u.getCity(), address, Collections.singleton(userService.findByName("registeredUser")));
			userService.registerUser(newUser);

			System.out.println("ISPIS???");
			return new ResponseEntity<>("User is registered", HttpStatus.CREATED);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<>("Address is wrong", HttpStatus.BAD_REQUEST);
		} catch (ParserConfigurationException e) {
			return new ResponseEntity<>("Address is wrong", HttpStatus.BAD_REQUEST);
		} catch (SAXException e) {
			return new ResponseEntity<>("Address is wrong", HttpStatus.BAD_REQUEST);
		}
	}
	
	public Address getCordinates(String address, String city) throws IOException, ParserConfigurationException, SAXException {
		Address ret = new Address();
		String fullAddress = address+","+city;
		fullAddress = fullAddress.replace(" ", "%20");
		fullAddress = fullAddress.replace(",", "%2C");
	    URL url = new URL("https://maps.google.com/maps?width=100%&amp;height=600&amp;hl=en&amp;q=" + fullAddress + "+(My%20Business%20Name)&amp;ie=UTF8&amp;t=&amp;z=14&amp;iwloc=B&amp;output=embed");

	    BufferedReader theHTML = new BufferedReader(new InputStreamReader(url.openStream()));
	    
	    String cordinatesString = "";

	    FileWriter fstream = new FileWriter("url.txt");
	    BufferedWriter out = new BufferedWriter(fstream);
	    String thisLine;
		while ((thisLine = theHTML.readLine()) != null) {
	        out.write(thisLine);
	        if(thisLine.contains("APP_INITIALIZATION_STATE")) {
	        	System.out.println(thisLine);
	        	cordinatesString = thisLine;
	        	break;
	        }
		}
		
		String cordinates[] = cordinatesString.split(",");
		System.out.println(cordinates[1]);
		System.out.println(cordinates[2]);
		int length = cordinates[2].length() - 1;
		double cordX = Double.parseDouble(cordinates[1]);
		double cordY = Double.parseDouble(cordinates[2].substring(0, length));
		ret.setxCord(cordX);
		ret.setyCord(cordY);
		System.out.println("X coord: "+ cordX + " Y cord: " + cordY);
		
	    out.close();

	    System.out.println(fullAddress);
		return ret;
	}
	
	@RequestMapping(value = "/login", 
			method = RequestMethod.POST, 
			consumes =MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> login(@Valid @RequestBody RegistrationDTO u) {
		
		System.out.println("[AuthController][login] Logovanje..." + u.getEmail() + u.getPassword());
		System.out.println("[AuthController][login] Prosledjena pass: " + u.getPassword());
		
		User user = userService.getByEmail(u.getEmail());
		System.out.println("[AuthController][login] Hasovana pass: " + user.getPassword());
		if(org.springframework.security.crypto.bcrypt.BCrypt.checkpw(u.getPassword(), user.getPassword())){	
			System.out.println("[AuthController][login] Uspjesna prijava, email: " + user.getEmail());
		
		}else {
			System.out.println("[AuthController][login] Neuspjesna prijava.");
			return new ResponseEntity<>(new UserTokenState("error",0), HttpStatus.NOT_FOUND);

		}
		
		final Authentication authentication = authenticationManager
               .authenticate(new UsernamePasswordAuthenticationToken(u.getEmail(), u.getPassword()));

		System.out.println("BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB");

		SecurityContextHolder.getContext().setAuthentication(authentication);
       
        String jwt = jwtTokenProvider.generateToken(user.getEmail());
		System.out.println("BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB");

		return new ResponseEntity<>(new LoggedUserDTO(u.getEmail(), jwt), HttpStatus.OK);
		
		
	}
	

}
