package com.example.BOOK_MY_SHOW_BACKEND.Repository;
import com.example.BOOK_MY_SHOW_BACKEND.Models.TheaterEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TheaterRepository extends JpaRepository<TheaterEntity,Integer> {
}
