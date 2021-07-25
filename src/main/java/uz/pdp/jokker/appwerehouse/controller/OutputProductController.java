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
import uz.pdp.jokker.appwerehouse.payload.OutputProductDto;
import uz.pdp.jokker.appwerehouse.service.InputProductService;
import uz.pdp.jokker.appwerehouse.service.OutputProductService;

@RestController
@RequestMapping("/api/outputproduct")
public class OutputProductController {

    @Autowired
    OutputProductService productService;

    @PostMapping("/add")
    public HttpEntity<?> addd(@RequestBody OutputProductDto outputProductDto) {
        ApiResponce responce = productService.add(outputProductDto);
        return ResponseEntity.status(responce.isSucces() ? 200 : 409).body(responce);
    }
}
