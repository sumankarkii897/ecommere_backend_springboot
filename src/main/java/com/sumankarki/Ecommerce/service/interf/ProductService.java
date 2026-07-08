package com.sumankarki.Ecommerce.service.interf;
import com.sumankarki.Ecommerce.dto.ProductRequestDto;
import com.sumankarki.Ecommerce.dto. UpdateProductRequestDto;
import com.sumankarki.Ecommerce.dto.Response;
import org.springframework.web.multipart.MultipartFile;

public interface ProductService {

    public Response createProduct(ProductRequestDto productDto , MultipartFile productImage);

    public Response updateProduct (Long productId, UpdateProductRequestDto productRequestDto, MultipartFile productImage);

    public Response deleteProduct (Long productId);

    public Response getProducts();

    public Response getProductById(Long productId);

    public Response getProductByCategory(Long categoryId);

    public Response searchProduct(String searchValue);
}
