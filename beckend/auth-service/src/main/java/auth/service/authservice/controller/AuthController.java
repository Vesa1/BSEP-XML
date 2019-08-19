package auth.service.authservice.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import auth.service.authservice.model.User;

@RestController
@RequestMapping("api/auth")
public class AuthController {
	
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
	public ResponseEntity<?> registration(@Valid @RequestBody User u) {
		System.out.println(u);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
