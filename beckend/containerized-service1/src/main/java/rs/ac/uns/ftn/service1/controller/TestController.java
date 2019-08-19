package rs.ac.uns.ftn.service1.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:4201", "http://localhost:4202"})
@RequestMapping("/api/service1")
public class TestController
{

	@GetMapping("/hello")
	public ResponseEntity get()
	{
		System.out.println("Usao u metodu!");
		return new ResponseEntity(HttpStatus.OK);
	}
}
