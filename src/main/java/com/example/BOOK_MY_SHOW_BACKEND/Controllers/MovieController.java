package com.example.BOOK_MY_SHOW_BACKEND.Controllers;

import com.example.BOOK_MY_SHOW_BACKEND.RequestDto.MovieRequestDto;
import com.example.BOOK_MY_SHOW_BACKEND.Services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    MovieService movieService;

    @PostMapping("/add")
    public String addMovie(@RequestBody MovieRequestDto movieRequestDto){

        return movieService.addMovie(movieRequestDto);
    }

    //Get movie by Name


    //get movie by id
}