package uz.pdp.jokker.appwerehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.jokker.appwerehouse.entity.Measurement;
import uz.pdp.jokker.appwerehouse.payload.ApiResponce;
import uz.pdp.jokker.appwerehouse.payload.MeasurementDto;
import uz.pdp.jokker.appwerehouse.service.MeasurementService;

import java.util.List;

@RestController
@RequestMapping("/api/measurement")
public class MeasurementController {
    @Autowired
    MeasurementService measurementService;

    @PostMapping
    public HttpEntity<?> add(@RequestBody MeasurementDto measurementDto) {
        ApiResponce responce = measurementService.add(measurementDto);
        return ResponseEntity.status(responce.isSucces() ? 200 : 409).body(responce);
    }

    @GetMapping()
    public HttpEntity<?> getAll() {
        List<Measurement> responce = measurementService.getAll();
        return ResponseEntity.ok(responce);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getOne(@PathVariable Integer id) {
        Measurement responce = measurementService.getOne(id);
        return ResponseEntity.ok(responce);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleted(@PathVariable Integer id) {
        ApiResponce responce = measurementService.deleted(id);
        return ResponseEntity.status(responce.isSucces() ? 200 : 409).body(responce);
    }


}
