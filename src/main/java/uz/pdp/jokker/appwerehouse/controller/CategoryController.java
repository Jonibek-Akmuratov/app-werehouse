package uz.pdp.jokker.appwerehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.jokker.appwerehouse.payload.ApiResponce;
import uz.pdp.jokker.appwerehouse.payload.CategoryDto;
import uz.pdp.jokker.appwerehouse.payload.MeasurementDto;
import uz.pdp.jokker.appwerehouse.service.CategoryService;
import uz.pdp.jokker.appwerehouse.service.MeasurementService;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @PostMapping
    public HttpEntity<?> add(@RequestBody CategoryDto categoryDto) {
        ApiResponce responce = categoryService.add(categoryDto);
        return ResponseEntity.status(responce.isSucces()?200:409).body(responce);
    }

}
