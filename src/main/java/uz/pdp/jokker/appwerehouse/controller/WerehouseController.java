package uz.pdp.jokker.appwerehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.jokker.appwerehouse.entity.Werehouse;
import uz.pdp.jokker.appwerehouse.payload.ApiResponce;
import uz.pdp.jokker.appwerehouse.payload.WerehouseDto;
import uz.pdp.jokker.appwerehouse.repository.WerehouseRepository;
import uz.pdp.jokker.appwerehouse.service.WerehouseService;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/werehouse")
public class WerehouseController {

    @Autowired
    WerehouseService werehouseService;

    //    Ishladi
    @PostMapping
    public HttpEntity<?> add(@Valid @RequestBody WerehouseDto werehouseDto) {
        ApiResponce responce = werehouseService.add(werehouseDto);
        return ResponseEntity.status(responce.isSucces() ? 200 : 409).body(responce);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> edit(@PathVariable Integer id, @RequestBody WerehouseDto werehouseDto) {
        ApiResponce responce = werehouseService.edit(id, werehouseDto);
        return ResponseEntity.status(responce.isSucces() ? 200 : 409).body(responce);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleted(@PathVariable Integer id) {
        ApiResponce responce = werehouseService.delete(id);
        return ResponseEntity.status(responce.isSucces() ? 200 : 409).body(responce);
    }

    //    Ishladi
    @GetMapping("/{id}")
    public Werehouse getOne(@PathVariable Integer id) {
        Werehouse responce = werehouseService.getOne(id);
        return responce;
    }

    //    Ishladi
    @GetMapping()
    public List<Werehouse> getAll() {
        List<Werehouse> all = werehouseService.getAll();
        return all;
    }

}
