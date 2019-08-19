package auth.service.authservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/demo")
public class DemoController {

	@GetMapping("/demoMethod")
	public ResponseEntity get()
	{
		System.out.println("[AuthService: DemoController]: OK!");
		return new ResponseEntity(HttpStatus.OK);
	}
}
