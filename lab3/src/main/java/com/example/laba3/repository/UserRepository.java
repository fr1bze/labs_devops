package com.example.laba3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.laba3.models.*;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
}