package uz.pdp.jokker.appwerehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.jokker.appwerehouse.payload.ApiResponce;
import uz.pdp.jokker.appwerehouse.payload.ClientDto;
import uz.pdp.jokker.appwerehouse.service.ClientService;

@RestController
@RequestMapping("/api/client")
public class ClientController {
    @Autowired
    ClientService clientService;

    @PostMapping("/add")
    public HttpEntity<?> add(@RequestBody ClientDto clientDto) {
        ApiResponce responce = clientService.add(clientDto);
        return ResponseEntity.status(responce.isSucces() ? 200 : 409).body(responce);
    }
}
