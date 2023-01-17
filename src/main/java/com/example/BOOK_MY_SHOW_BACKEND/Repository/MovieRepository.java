package com.example.BOOK_MY_SHOW_BACKEND.Repository;

//import com.example.Book_my_show_backend.Models.MovieEntity;
import com.example.BOOK_MY_SHOW_BACKEND.Models.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<MovieEntity,Integer> {

}