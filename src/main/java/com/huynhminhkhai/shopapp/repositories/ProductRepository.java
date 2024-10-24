package com.huynhminhkhai.shopapp.repositories;

import com.huynhminhkhai.shopapp.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    // Tìm sản phẩm theo Category ID
    List<Product> findByCategoryId(Long categoryId);
    // Các phương thức truy vấn tùy chỉnh nếu cần

}
