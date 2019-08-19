package rs.ac.uns.ftn.service2.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/service2")
public class TestController
{

    @GetMapping("/hello")
    public ResponseEntity get()
    {
        return new ResponseEntity("Hello from service 2!", HttpStatus.OK);
    }
}
