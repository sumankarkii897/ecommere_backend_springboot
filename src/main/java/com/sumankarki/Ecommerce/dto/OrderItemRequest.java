package com.sumankarki.Ecommerce.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderItemRequest {

    private Long productId;

    private int quantity;
}
