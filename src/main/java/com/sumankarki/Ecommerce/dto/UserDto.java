package com.sumankarki.Ecommerce.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.sumankarki.Ecommerce.enums.UserRole;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor

public class UserDto {
    private Long id;

    private String name;

//    private String password;

    @Column(unique = true, nullable = false)
    private String email;

    private String phoneNumber;

    private UserRole role;

    private List<OrderItemDto> orderItemList;

    private List<AddressDto> address;

    private LocalDateTime createdAt;

}
