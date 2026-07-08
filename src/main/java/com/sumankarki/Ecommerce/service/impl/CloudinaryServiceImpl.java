package com.sumankarki.Ecommerce.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.sumankarki.Ecommerce.dto.ImageUploadResultDto;
import com.sumankarki.Ecommerce.service.interf.CloudinaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CloudinaryServiceImpl implements CloudinaryService {

    private final Cloudinary cloudinary;

    @Override
    public ImageUploadResultDto uploadImage(MultipartFile image) {
       try{
           Map<?,?> uploadResult = cloudinary.uploader().upload(
                   image.getBytes(),
                   ObjectUtils.emptyMap()
           );
           return  new ImageUploadResultDto(
                   uploadResult.get("secure_url").toString(),
                   uploadResult.get("public_id").toString()
           );
       }
       catch (IOException e){
           throw new RuntimeException("Error uploading image");
       }

    }

    @Override
    public void deleteImage(String publicId) {
        try {
            cloudinary.uploader().destroy(
                    publicId,
                    ObjectUtils.emptyMap()
            );
        } catch (IOException e) {
            throw new RuntimeException("Failed to delete image");
        }
    }
}
