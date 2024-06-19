package com.example.imgursaverapi.controller;

import com.example.imgursaverapi.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/images")
public class ImageController {
    private final ImageService imageService;

    @GetMapping
    public String startPage() {
        return "start";
    }

    @PostMapping(value = "/uploadImage")
    public ResponseEntity<String> uploadImage(@RequestParam("imageFile") MultipartFile file) {
        String response = imageService.uploadImageToImgur(file);
        return ResponseEntity
                .ok()
                .body(response);
    }

    @DeleteMapping(value = "/delete")
    public ResponseEntity<String> deleteImage(@RequestParam("deleteHash") String deleteHash) {
        String response = imageService.deleteImageFromImgur(deleteHash);
        return ResponseEntity
                .ok()
                .body(response);
    }
}
