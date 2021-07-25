package uz.pdp.jokker.appwerehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.jokker.appwerehouse.payload.ApiResponce;
import uz.pdp.jokker.appwerehouse.payload.InputDto;
import uz.pdp.jokker.appwerehouse.payload.OutputDto;
import uz.pdp.jokker.appwerehouse.service.InputService;
import uz.pdp.jokker.appwerehouse.service.OutputService;

@RestController
@RequestMapping("/api/input")
public class OutputController {

    @Autowired
    OutputService outputService;

    @PostMapping
    public HttpEntity<?> addd(@RequestBody OutputDto outputDto) {
        ApiResponce responce = outputService.addService(outputDto);
        return ResponseEntity.status(responce.isSucces() ? 200 : 409).body(responce);
    }


}
