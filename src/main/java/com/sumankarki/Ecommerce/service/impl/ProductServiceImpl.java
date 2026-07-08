package com.sumankarki.Ecommerce.service.impl;
import com.sumankarki.Ecommerce.dto.*;
import com.sumankarki.Ecommerce.entity.Category;
import com.sumankarki.Ecommerce.entity.Product;
import com.sumankarki.Ecommerce.exception.NotFoundException;
import com.sumankarki.Ecommerce.mapper.EntityDtoMapper;
import com.sumankarki.Ecommerce.repository.CategoryRepository;
import com.sumankarki.Ecommerce.repository.ProductRepository;
import com.sumankarki.Ecommerce.service.interf.CloudinaryService;
import com.sumankarki.Ecommerce.service.interf.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CloudinaryService cloudinaryService;
private final EntityDtoMapper entityDtoMapper;
private final CategoryRepository categoryRepository;
    @Override
    @Transactional
    public Response createProduct(ProductRequestDto productDto, MultipartFile productImage) {

        Category category = categoryRepository.findById(productDto.getCategoryId()).orElseThrow(() -> new NotFoundException("Category not found"));

        ImageUploadResultDto uploadResult = cloudinaryService.uploadImage(productImage);


        Product product = new Product();
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setImageUrl(uploadResult.getImageUrl());
        product.setPublicId(uploadResult.getPublicId());
        product.setDescription(productDto.getDescription());
        product.setCategory(category);
        System.out.println("Hello");
        productRepository.save(product);

        return Response.builder()
                .status(200)
                .message("Product Created Successfully")
                .build();

    }

    @Override
    @Transactional
    public Response updateProduct(Long productId, UpdateProductRequestDto productRequestDto, MultipartFile productImage) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new NotFoundException("Product Not Found"));
        if(productRequestDto.getName()!=null){
            product.setName(productRequestDto.getName());

        }
        if(productRequestDto.getDescription()!=null){
            product.setDescription(productRequestDto.getDescription());

        }
        if(productRequestDto.getPrice()!=null){
            product.setPrice(productRequestDto.getPrice());
        }

        if(productRequestDto.getCategoryId()!=null){
            Category category = categoryRepository.findById(productRequestDto.getCategoryId()).orElseThrow(() -> new NotFoundException("Category not found"));

            product.setCategory(category);
        }

        if(productImage!=null && !productImage.isEmpty()){
            if(product.getPublicId()!= null){
                cloudinaryService.deleteImage(product.getPublicId());
                log.info("Delete Image Successfully");


            }
            ImageUploadResultDto uploadResult = cloudinaryService.uploadImage(productImage);
            product.setImageUrl(uploadResult.getImageUrl());
            product.setPublicId(uploadResult.getPublicId());
        }




        productRepository.save(product);

        return Response.builder()
                .status(200)
                .message("Product Updated Successfully")
//                .product(entityDtoMapper.mapProductToDtoBasic(product))
                .build();
    }

    @Override
    @Transactional
    public Response deleteProduct(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(()-> new NotFoundException("Product not found"));

        if(product.getPublicId()!=null){
            cloudinaryService.deleteImage(product.getPublicId());

        }
        log.info("Deleting product {}",product.getId());
        productRepository.delete(product);

        return Response.builder()
                .status(200)
                .message("Product has been deleted")
                .build();
    }

    @Override
    public Response getProducts() {
        List<Product> productList = productRepository.findAll(Sort.by(Sort.Direction.DESC,"id"));
        List<ProductDto> productDtoList = productList.stream().map(entityDtoMapper::mapProductToDtoBasic)
                .toList();
        return Response.builder()
                .status(200)
                .message("Product list has been fetched")
                .productList(productDtoList)
                .build();
    }

    @Override
    public Response getProductById(Long productId) {
        Product product =  productRepository.findById(productId).orElseThrow(()-> new NotFoundException("Product not found"));
        ProductDto productDto = entityDtoMapper.mapProductToDtoBasic(product);

        return Response.builder()
                .status(200)
                .message("Product details has been fetched")
                .product(productDto)

                .build();
    }

    @Override
    public Response getProductByCategory(Long categoryId) {

        List<Product> productList = productRepository.findByCategoryId(categoryId);
        if(productList.isEmpty()){
            throw new NotFoundException("Product Not Found for This Category");
        }
        List<ProductDto> productDtoList = productList.stream().map(entityDtoMapper::mapProductToDtoBasic)
                .toList();
        return Response.builder()
                .status(200)
                .message("Product details has been fetched")
                .productList(productDtoList)
                .build();
    }

    @Override
    public Response searchProduct(String searchValue) {

        List<Product> products = productRepository
                .findByNameContainingOrDescriptionContaining(searchValue,searchValue);
        if(products.isEmpty()){
            throw new NotFoundException("Product Not Found");
        }
        List<ProductDto> productDtoList = products.stream()
                .map(entityDtoMapper::mapProductToDtoBasic)
                .toList();
        return Response.builder()
                .status(200)
                .productList(productDtoList)
                .build();
    }
}
