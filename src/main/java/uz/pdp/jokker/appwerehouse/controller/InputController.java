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
import uz.pdp.jokker.appwerehouse.service.InputService;

@RestController
@RequestMapping("/api/inputt")
public class InputController {

    @Autowired
    InputService inputService;

    @PostMapping
    public HttpEntity<?> addI(@RequestBody InputDto inputDto) {
        ApiResponce responce = inputService.addInput(inputDto);
        return ResponseEntity.status(responce.isSucces() ? 200 : 409).body(responce);
    }


}
