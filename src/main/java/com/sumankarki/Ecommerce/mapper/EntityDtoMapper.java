package com.sumankarki.Ecommerce.mapper;

import com.sumankarki.Ecommerce.dto.*;
import com.sumankarki.Ecommerce.entity.*;
import com.sumankarki.Ecommerce.enums.UserRole;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EntityDtoMapper {

    // user entity to user DTO

    public UserDto mapUserToDtoBasic(User user){
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
//        userDto.setPassword(user.getPassword());
        userDto.setPhoneNumber(user.getPhoneNumber());
        userDto.setRole(user.getRole());
        userDto.setCreatedAt(user.getCreatedAt());

return userDto;

    }

    // Address to Dto

    public AddressDto mapAddressToDtoBasic(Address address){

        return AddressDto
                .builder()
                .id(address.getId())
                .state(address.getState())
                .street(address.getStreet())
                .city(address.getCity())
                .zipCode(address.getZipCode())
                .country(address.getCountry())
                .createdAt(address.getCreatedAt())
                .build();
    }

    // Category to Dto

    public CategoryDto mapCategoryToDtoBasic(Category category){

        return CategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }

    // OrderItem to Dto basic

    public OrderItemDto mapOrderItemToDtoBasic(OrderItem orderItem){
        OrderItemDto orderItemDto = new OrderItemDto();
        orderItemDto.setId(orderItem.getId());
        orderItemDto.setQuantity(orderItem.getQuantity());
        orderItemDto.setPrice(orderItem.getPrice());
        orderItemDto.setOrderStatus(orderItem.getOrderStatus());
        orderItemDto.setCreatedAt(orderItem.getCreatedAt());
        return orderItemDto;
    }


    // Product to Dto basic
    public ProductDto mapProductToDtoBasic(Product product){
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setPrice(product.getPrice());
        productDto.setDescription(product.getDescription());
        productDto.setCategory(mapCategoryToDtoBasic(product.getCategory()));
        productDto.setImageUrl(product.getImageUrl());
        productDto.setPublicId(product.getPublicId());

        return productDto;
    }

    public UserDto mapUserToDtoPlusAddress(User user) {

        UserDto userDto = mapUserToDtoBasic(user);

        if (user.getAddress() != null) {

            List<AddressDto> addressDtos = user.getAddress()
                    .stream()
                    .map(this::mapAddressToDtoBasic)
                    .toList();

            userDto.setAddress(addressDtos);
        }

        return userDto;
    }

    // OrderItem to Dto plus product

    public OrderItemDto mapOrderItemToDtoPlusProduct(OrderItem orderItem) {
        OrderItemDto orderItemDto = mapOrderItemToDtoBasic(orderItem);

        if (orderItem.getProduct() != null) {
            ProductDto productDto = mapProductToDtoBasic(orderItem.getProduct());
            orderItemDto.setProduct(productDto);

        }
        return orderItemDto;
    }

    // orderItem to Dto plus Product and  User

    public OrderItemDto mapOrderItemToDtoPlusProductAndUser(OrderItem orderItem) {
        OrderItemDto orderItemDto = mapOrderItemToDtoPlusProduct(orderItem);
        if(orderItem.getUser() != null){
            UserDto userDto = mapUserToDtoBasic(orderItem.getUser());
            orderItemDto.setUser(userDto);
        }
        return orderItemDto;
    }

    // user to Dto with address and order items

    public UserDto mapUserToDtoPlusAddressAndOrderItem(User user){
        UserDto userDto = mapUserToDtoPlusAddress(user);
        if (user.getOrderItemList() != null && !user.getOrderItemList().isEmpty()) {
//            OrderItemDto orderItemDto = mapOrderItemToDtoBasic(user.getOrderItemList().get(0));
            List<OrderItemDto> orderItemDto = user.getOrderItemList()
                    .stream()
                    .map(this::mapOrderItemToDtoPlusProduct)
                    .toList();

            userDto.setOrderItemList(orderItemDto);

        }
        return userDto;
    }
}
