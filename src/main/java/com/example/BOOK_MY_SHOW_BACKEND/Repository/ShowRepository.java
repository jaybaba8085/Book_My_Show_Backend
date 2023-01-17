package com.example.BOOK_MY_SHOW_BACKEND.Repository;

import com.example.BOOK_MY_SHOW_BACKEND.Models.ShowEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShowRepository extends JpaRepository<ShowEntity,Integer> {
}
