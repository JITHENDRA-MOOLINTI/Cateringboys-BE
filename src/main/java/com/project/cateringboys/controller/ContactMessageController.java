package com.project.cateringboys.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.cateringboys.model.ContactMessage;
import com.project.cateringboys.service.ContactMessageService;

@Controller
@RequestMapping("/cateringboys")
@CrossOrigin(origins = "*")
public class ContactMessageController {
       

    @Autowired
    private ContactMessageService contactMessageService;

    @PostMapping("/contact")
    public ResponseEntity<?> submitContact(@RequestBody ContactMessage message) {
        try {
            contactMessageService.saveMessage(message);
            return ResponseEntity.ok(Map.of("message", "Your message has been sent successfully!"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
}
