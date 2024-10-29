package com.huynhminhkhai.shopapp.repositories;

import com.huynhminhkhai.shopapp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    //Kiểm tra email tồn tại chưa
    boolean existsByEmail(String email);
    // Tìm người dùng bằng email
   User findByEmail(String email);
    // Các phương thức truy vấn tùy chỉnh nếu cần

}
