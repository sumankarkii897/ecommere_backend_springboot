package com.sumankarki.Ecommerce.service.impl;
import com.sumankarki.Ecommerce.dto.OrderDto;
import com.sumankarki.Ecommerce.dto.OrderItemDto;
import com.sumankarki.Ecommerce.dto.OrderRequest;
import com.sumankarki.Ecommerce.dto.Response;
import com.sumankarki.Ecommerce.entity.Order;
import com.sumankarki.Ecommerce.entity.OrderItem;
import com.sumankarki.Ecommerce.entity.Product;
import com.sumankarki.Ecommerce.entity.User;
import com.sumankarki.Ecommerce.enums.OrderStatus;
import com.sumankarki.Ecommerce.exception.NotFoundException;
import com.sumankarki.Ecommerce.mapper.EntityDtoMapper;
import com.sumankarki.Ecommerce.repository.OrderItemRepository;
import com.sumankarki.Ecommerce.repository.OrderRepository;
import com.sumankarki.Ecommerce.repository.ProductRepository;
import com.sumankarki.Ecommerce.service.interf.OrderItemService;
import com.sumankarki.Ecommerce.service.interf.UserService;
import com.sumankarki.Ecommerce.specification.OrderItemSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderItemServiceImpl implements OrderItemService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ProductRepository productRepository;
    private final UserService userService;
    private final EntityDtoMapper entityDtoMapper;
    @Override
    public Response placeOrder(OrderRequest orderRequest) {
        User user = userService.getLoginUser();

        // map order request items to order entities

        List<OrderItem> orderItemList = orderRequest.getItems()
                .stream()
                .map(orderItemRequest -> {
                    Product product = productRepository.findById(orderItemRequest.getProductId())
                            .orElseThrow(() -> new NotFoundException("Product not found"));
                    OrderItem orderItem = new OrderItem();
                    orderItem.setProduct(product);
                    orderItem.setQuantity(orderItemRequest.getQuantity());
                    orderItem.setPrice(product.getPrice().multiply(BigDecimal.valueOf(orderItemRequest.getQuantity())));
                    orderItem.setOrderStatus(OrderStatus.PENDING);
                    orderItem.setUser(user);
                    return orderItem;
                }).toList();
// calculate the total price



        BigDecimal totalPrice = orderRequest.getTotalPrice()!=null
                && orderRequest.getTotalPrice().compareTo(BigDecimal.ZERO)>0
                ?
                orderRequest.getTotalPrice()
                :
                orderItemList.stream().map(OrderItem::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
      // create order entity

        Order order = new Order();
        order.setOrderItemsList(orderItemList);
        order.setTotalPrice(totalPrice);

        // set the order reference in each order item

        orderItemList.forEach(orderItem -> orderItem.setOrder(order));

        orderRepository.save(order);

        return Response.builder()
                .status(200)
                .message("Order Placed Successfully")
                .build();
    }

    @Override
    @Transactional
    public Response updateOrderItemStatus(Long orderItemId, OrderStatus status) {
      OrderItem orderItem = orderItemRepository.findById(orderItemId).orElseThrow(()->
              new NotFoundException("Order Item Not Found"));
//      orderItem.setOrderStatus(OrderStatus.valueOf(status.toUpperCase()));
orderItem.setOrderStatus(status);
      orderItemRepository.save(orderItem);

      return Response.builder()
              .status(200)
              .message("Order status updated Successfully")
              .build();


    }

    @Override
    public Response filterOrderItem(OrderStatus orderStatus, LocalDateTime startDate, LocalDateTime endDate, Long itemId, Pageable pagable) {
        Specification<OrderItem> spec = Specification.where(OrderItemSpecification.hasStatus(orderStatus))
                .and(OrderItemSpecification.createdBetween(startDate,endDate))
                .and(OrderItemSpecification.hasItemId(itemId));

        Page<OrderItem> orderItemPage = orderItemRepository.findAll(spec,pagable);
        if(orderItemPage.isEmpty()){
            throw new NotFoundException("Order Item Not Found");
        }
        List<OrderItemDto> orderItemDtos = orderItemPage.getContent().stream()
                .map(entityDtoMapper::mapOrderItemToDtoPlusProductAndUser)
                .toList();

        return Response.builder()
                .status(200)
                .orderItemList(orderItemDtos)
                .totalPage(orderItemPage.getTotalPages())
                .totalElement(orderItemPage.getTotalElements())
                .build();



    }

}
