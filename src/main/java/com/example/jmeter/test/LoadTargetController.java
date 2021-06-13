package com.example.jmeter.test;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class LoadTargetController {

    @PostMapping(value = "/load")
    public ResponseEntity response(@RequestBody Map<String, String> values){
        for (Map.Entry<String, String> entry : values.entrySet()) {
            if (values.containsKey("error")) {
                System.out.println("Bad Request with error");
                return ResponseEntity.badRequest().build();
            }
            System.out.println("Key : " + entry.getKey() + " | Value : "+ entry.getValue());
        }
        return ResponseEntity.ok().build();
    }
}
