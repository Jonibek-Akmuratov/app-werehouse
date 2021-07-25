package uz.pdp.jokker.appwerehouse.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pdp.jokker.appwerehouse.payload.ApiResponce;
import uz.pdp.jokker.appwerehouse.service.AttachmentService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping
public class AttachmentController {
    @Autowired
    AttachmentService attachmentService;

//Ishladi
    @PostMapping("/uploadFile")
    public HttpEntity<?> uploadFile(MultipartHttpServletRequest request) throws IOException {
        ApiResponce responce = attachmentService.upload(request);
        return ResponseEntity.status(responce.isSucces() ? 200 : 409).body(responce);

    }

    //Ishladi
    @GetMapping("/getFile/{id}")
    public HttpEntity<?> getFile(@PathVariable Integer id, HttpServletResponse response) {

        HttpServletResponse httpServletResponse = attachmentService.get(id, response);
        return ResponseEntity.ok(httpServletResponse);
    }


}
