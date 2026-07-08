package com.sumankarki.Ecommerce.service.interf;

import com.sumankarki.Ecommerce.dto.OrderRequest;
import com.sumankarki.Ecommerce.dto.Response;
import com.sumankarki.Ecommerce.enums.OrderStatus;
import org.springframework.data.domain.Pageable;


import java.time.LocalDateTime;

public interface OrderItemService {

    Response placeOrder(OrderRequest orderRequest);

    Response updateOrderItemStatus(Long orderItemId, OrderStatus status);

    Response filterOrderItem(OrderStatus orderStatus, LocalDateTime startDate, LocalDateTime endDate, Long itemId , Pageable pagable);

//    Response getOrders();
}
