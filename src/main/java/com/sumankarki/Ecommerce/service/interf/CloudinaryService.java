package com.sumankarki.Ecommerce.service.interf;


import com.sumankarki.Ecommerce.dto.ImageUploadResultDto;
import org.springframework.web.multipart.MultipartFile;

public interface CloudinaryService {

    ImageUploadResultDto uploadImage(MultipartFile file);

    void deleteImage (String publicId);
}
