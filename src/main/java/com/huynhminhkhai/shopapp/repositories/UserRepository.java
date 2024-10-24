package com.huynhminhkhai.shopapp.repositories;

import com.huynhminhkhai.shopapp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Tìm người dùng bằng email
    Optional<User> findByEmail(String email);
    // Các phương thức truy vấn tùy chỉnh nếu cần

}
