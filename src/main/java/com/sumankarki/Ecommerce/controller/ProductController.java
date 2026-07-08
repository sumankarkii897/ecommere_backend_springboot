package com.sumankarki.Ecommerce.controller;

import com.sumankarki.Ecommerce.dto.ProductRequestDto;
import com.sumankarki.Ecommerce.dto.Response;
import com.sumankarki.Ecommerce.dto.UpdateProductRequestDto;
import com.sumankarki.Ecommerce.service.interf.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping(value = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response> createProduct(@ModelAttribute ProductRequestDto productDto,@RequestParam MultipartFile productImage) {
        return ResponseEntity.ok(productService.createProduct(productDto,productImage));
    }

    @DeleteMapping("/{productId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response> deleteProduct(@PathVariable Long productId) {
        return ResponseEntity.ok(productService.deleteProduct(productId));
    }

    @GetMapping("/get-all")
    public ResponseEntity<Response> getAllProducts() {
        return ResponseEntity.ok(productService.getProducts());
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Response> getProduct(@PathVariable Long productId) {
        return ResponseEntity.ok(productService.getProductById(productId));
    }

    @PutMapping(value = "/update/{productId}",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response> updateProduct(@PathVariable Long productId , @ModelAttribute UpdateProductRequestDto productDto, @RequestParam(required = false) MultipartFile productImage) {

        return ResponseEntity.ok(productService.updateProduct(productId,productDto,productImage));
    }
@GetMapping("/category/{categoryId}")
    public ResponseEntity<Response> getAllProductsByCategory(@PathVariable Long categoryId) {
return ResponseEntity.ok(productService.getProductByCategory(categoryId));
    }

    @GetMapping("/search")
    public ResponseEntity<Response> searchForProduct(@RequestParam String searchValue){
        return ResponseEntity.ok(productService.searchProduct(searchValue));
    }

}
