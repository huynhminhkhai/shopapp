package com.huynhminhkhai.shopapp.repositories;

import com.huynhminhkhai.shopapp.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    // Các phương thức truy vấn tùy chỉnh nếu cần
}
