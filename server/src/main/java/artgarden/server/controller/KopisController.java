package artgarden.server.controller;

import artgarden.server.service.KopisService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URL;

@RestController
@RequiredArgsConstructor
public class KopisController {

    private final KopisService kopisService;

    @GetMapping("/api")
    public ResponseEntity<String> kopisAPI(){


        return ResponseEntity.ok("Data save successfully");
    }
}
