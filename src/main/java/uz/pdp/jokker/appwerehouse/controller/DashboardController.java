package uz.pdp.jokker.appwerehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.jokker.appwerehouse.payload.DateDto;
import uz.pdp.jokker.appwerehouse.repository.InputRepository;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    @Autowired
    InputRepository inputRepository;

    @GetMapping("/KunKirimSummasi")
    public HttpEntity<?> summa(@RequestBody DateDto dateDto){
return null;
    }

}
