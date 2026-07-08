package com.sumankarki.Ecommerce.controller;

import com.sumankarki.Ecommerce.dto.OrderRequest;
import com.sumankarki.Ecommerce.dto.Response;
import com.sumankarki.Ecommerce.enums.OrderStatus;
import com.sumankarki.Ecommerce.service.interf.OrderItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderItemController {
    private final OrderItemService orderItemService;

    @PostMapping("/create")
    public ResponseEntity<Response> createOrder( @RequestBody OrderRequest orderRequest){
        return ResponseEntity.ok(orderItemService.placeOrder(orderRequest));
    }

    @PutMapping ("/update-status/{orderId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response> updateOrderItemStatus(@RequestParam OrderStatus status, @PathVariable("orderId") Long orderId){
     return ResponseEntity.ok(orderItemService.updateOrderItemStatus(orderId,status));
    }


@GetMapping("/filter")
@PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response> filterOrderItem(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate,
            @RequestParam(required = false) OrderStatus status,
            @RequestParam(required = false) Long itemId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
            ){

        Pageable pagable = PageRequest.of(page,size,Sort.by(Sort.Direction.DESC,"id"));

        return ResponseEntity.ok(orderItemService.filterOrderItem(status,startDate,endDate,itemId,pagable));


    }

}
