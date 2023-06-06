package bankApp.controllers;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping
    public ResponseEntity<String> sayHello() {
        return ResponseEntity.ok("[Anyone] Hello from secured endpoint");
    }

    @GetMapping("/logged")
    public ResponseEntity<String> sayHelloAuth() {
        return ResponseEntity.ok("[Logged] Hello from secured endpoint");
    }


    @GetMapping("/client")
    public ResponseEntity<String> sayHelloClient() {
        return ResponseEntity.ok("[CLIENT] Hello from secured endpoint");
    }

    @GetMapping("/employee")
    public ResponseEntity<String> sayHelloEmployee() {
        return ResponseEntity.ok("[EMPLOYEE] Hello from secured endpoint");
    }

    @GetMapping("/isNotAdmin")
    public ResponseEntity<String> sayNoToAdmins() {
        return ResponseEntity.ok("[NOT AN ADMIN] Hello from secured endpoint");
    }



}