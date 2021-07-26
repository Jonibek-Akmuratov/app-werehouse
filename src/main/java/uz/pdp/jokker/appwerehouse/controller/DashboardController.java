package uz.pdp.jokker.appwerehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.jokker.appwerehouse.payload.ApiResponce;
import uz.pdp.jokker.appwerehouse.payload.DateDto;
import uz.pdp.jokker.appwerehouse.repository.InputRepository;
import uz.pdp.jokker.appwerehouse.service.DashboardService;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    @Autowired
    DashboardService dashboardService;

    @PostMapping("/kunkirimsummasi")
    public HttpEntity<?> summa(@RequestBody DateDto dateDto){
      ApiResponce responce= dashboardService.method(dateDto);
      return ResponseEntity.ok(responce);
    }

}
