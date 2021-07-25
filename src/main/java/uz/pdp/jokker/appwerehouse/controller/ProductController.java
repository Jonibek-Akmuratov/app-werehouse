package uz.pdp.jokker.appwerehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.jokker.appwerehouse.payload.ApiResponce;
import uz.pdp.jokker.appwerehouse.payload.ProductDto;
import uz.pdp.jokker.appwerehouse.service.ProductService;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    ProductService productService;


    @PostMapping
    public HttpEntity<?> add(@RequestBody ProductDto productDto) {
        ApiResponce responce = productService.add(productDto);
        return ResponseEntity.status(responce.isSucces() ? 200 : 409).body(responce);
    }

    @GetMapping("/all")
    public HttpEntity<?> getAll() {
        ApiResponce responce = productService.getAll();
        return ResponseEntity.ok(responce.getObject());
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getAll(@PathVariable Integer id) {
        ApiResponce responce = productService.getOne(id);
        return ResponseEntity.status(responce.isSucces() ? 200 : 409).body(responce);

    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleted(@PathVariable Integer id) {
        ApiResponce responce = productService.deleted(id);
        return ResponseEntity.status(responce.isSucces() ? 200 : 409).body(responce);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> edited(@PathVariable Integer id,@RequestBody ProductDto productDto) {
        ApiResponce responce = productService.edit(id,productDto);
        return ResponseEntity.status(responce.isSucces() ? 200 : 409).body(responce);
    }


}
