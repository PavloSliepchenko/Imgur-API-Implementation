package com.example.imgursaverapi.service;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    String uploadImageToImgur(MultipartFile imageFile);

    String deleteImageFromImgur(String deleteHash);
}
