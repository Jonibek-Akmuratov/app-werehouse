package uz.pdp.jokker.appwerehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.jokker.appwerehouse.payload.ApiResponce;
import uz.pdp.jokker.appwerehouse.payload.InputProductDto;
import uz.pdp.jokker.appwerehouse.service.InputProductService;

@RestController
@RequestMapping("/api/inputproduct")
public class InputProductController {

    @Autowired
    InputProductService inputProductService;

    @PostMapping("/add")
    public HttpEntity<?> add(@RequestBody InputProductDto inputProductDto) {
        ApiResponce responce = inputProductService.add(inputProductDto);
        return ResponseEntity.status(responce.isSucces() ? 200 : 409).body(responce);
    }
}
