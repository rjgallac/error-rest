package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

@RestController
public class TestSend {

    private RestTemplate restTemplate = new RestTemplate();

    @GetMapping("testsend")
    public ResponseEntity<String> send(){
        ResponseEntity<String> forEntity = restTemplate.getForEntity("http://localhost:8080/test", String.class);
        return forEntity;
    }

    @GetMapping("testsenderror")
    public ResponseEntity<String> senderror() {
        ResponseEntity<String> forEntity;
        try {
            forEntity = restTemplate.getForEntity("http://localhost:8080/testerror", String.class);

        } catch (HttpClientErrorException hcee) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("client bad");
        }
        catch (HttpServerErrorException hsee) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("server bad");
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("bad");
        }
        return forEntity;
    }
}
