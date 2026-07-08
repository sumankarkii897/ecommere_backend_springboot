package com.sumankarki.Ecommerce.repository;

import com.sumankarki.Ecommerce.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}