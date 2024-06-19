package com.example.imgursaverapi.service.impl;

import com.example.imgursaverapi.service.ImageService;
import java.io.File;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageServiceImpl implements ImageService {
    @Value("${imgur.client.id}")
    private String clientId;

    @Override
    public String uploadImageToImgur(MultipartFile imageFile) {
        try {
            File tempFile = File.createTempFile("upload-", imageFile.getOriginalFilename());
            imageFile.transferTo(tempFile);
            HttpResponse<JsonNode> response = Unirest.post("https://api.imgur.com/3/upload")
                    .header("Authorization", "Client-ID " + clientId)
                    .field("image", tempFile)
                    .asJson();
            if (response.getStatus() == 200) {
                String imageUrl = response.getBody().getObject().getJSONObject("data").getString("link");
                String deletehash = response.getBody().getObject().getJSONObject("data").getString("deletehash");
                return String.format("""
                        The picture was successfully uploaded.
                        Image link: %s
                        Delete hash: %s
                        """, imageUrl, deletehash);
            }
        } catch (Exception e) {
            throw new RuntimeException("Something went wrong.", e);
        }
        throw new RuntimeException("Failed to upload the image");
    }

    @Override
    public String deleteImageFromImgur(String deleteHash) {
        HttpResponse<JsonNode> response = Unirest.delete("https://api.imgur.com/3/image/" + deleteHash)
                .header("Authorization", "Client-ID " + clientId)
                .asJson();

        if (response.getStatus() == 200) {
            return "Image deleted successfully.";
        }
        throw new RuntimeException("Failed to delete an image");
    }
}
