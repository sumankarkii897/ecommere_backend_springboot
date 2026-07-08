package com.sumankarki.Ecommerce.entity;

import com.sumankarki.Ecommerce.enums.UserRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name="users")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Password is required")
    private String password;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    @Column(unique = true)
    private String email;

    @Column(name="phone_number")
    @NotBlank(message = "Phone number is required")
    private String phoneNumber;

@Enumerated(EnumType.STRING)
    private UserRole role;

@OneToMany(mappedBy = "user",fetch=FetchType.LAZY,cascade=CascadeType.ALL)
    private List<OrderItem> orderItemList;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Address> address;

@CreationTimestamp
    private LocalDateTime createdAt;
}
