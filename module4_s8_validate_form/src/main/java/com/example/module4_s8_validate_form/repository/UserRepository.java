package com.example.module4_s8_validate_form.repository;

import com.example.module4_s8_validate_form.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
