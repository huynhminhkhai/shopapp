package com.huynhminhkhai.shopapp.repositories;

import com.huynhminhkhai.shopapp.models.Welcome;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WelcomeRepository extends JpaRepository<Welcome, Long> {

}
