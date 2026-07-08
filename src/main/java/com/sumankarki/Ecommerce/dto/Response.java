package com.sumankarki.Ecommerce.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
//@JsonIgnoreProperties(ignoreUnknown = true)
public class Response {

    private int status;

    private String message;

    private final LocalDateTime timestamp = LocalDateTime.now();

    private String token;

    private String role;

    private String expirationTime;

    private int totalPage;

    private Long totalElement;

    private AddressDto address;

    private UserDto user;

    private List<UserDto> userList;

    private CategoryDto category;

    private List<CategoryDto> categoryList;

    private ProductDto product;

    private List<ProductDto> productList;

    private OrderItemDto orderItem;

    private List<OrderItemDto> orderItemList;

    private OrderDto order;

    private List<OrderDto> orderList;


}
