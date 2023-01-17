package com.example.BOOK_MY_SHOW_BACKEND.Repository;

import com.example.BOOK_MY_SHOW_BACKEND.Models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity,Integer> {
}
