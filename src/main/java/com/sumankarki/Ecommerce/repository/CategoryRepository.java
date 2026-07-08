package com.sumankarki.Ecommerce.repository;

import com.sumankarki.Ecommerce.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}