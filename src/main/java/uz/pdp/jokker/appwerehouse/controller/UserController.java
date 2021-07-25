package uz.pdp.jokker.appwerehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.jokker.appwerehouse.payload.ApiResponce;
import uz.pdp.jokker.appwerehouse.payload.UserDto;
import uz.pdp.jokker.appwerehouse.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    UserService userService;


    @PostMapping
    public HttpEntity<?> add(@RequestBody UserDto userDto) {
        ApiResponce responce = userService.add(userDto);
        return ResponseEntity.status(responce.isSucces() ? 200 : 409).body(responce);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> edit(@PathVariable Integer id, @RequestBody UserDto userDto) {
        ApiResponce responce = userService.edit(id, userDto);
        return ResponseEntity.status(responce.isSucces() ? 200 : 409).body(responce);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getOne(@PathVariable Integer id) {
        ApiResponce responce = userService.getOne(id);
        return ResponseEntity.status(responce.isSucces() ? 200 : 409).body(responce.getObject());
    }

    @GetMapping
    public HttpEntity<?> getAll() {
        ApiResponce responce = userService.getAll();
        return ResponseEntity.status(responce.isSucces() ? 200 : 409).body(responce.getObject());
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleted(@PathVariable Integer id) {
        ApiResponce responce = userService.deleted(id);
        return ResponseEntity.status(responce.isSucces() ? 200 : 409).body(responce.isSucces());
    }


}
