package com.example.BOOK_MY_SHOW_BACKEND.Controllers;

import com.example.BOOK_MY_SHOW_BACKEND.RequestDto.TheaterRequestDto;
import com.example.BOOK_MY_SHOW_BACKEND.Services.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/theater")
public class TheaterController {

    @Autowired
    TheaterService theaterService;

    @PostMapping("/add")
    public String addTheater(@RequestBody TheaterRequestDto theaterRequestDto) {

        return theaterService.createTheater(theaterRequestDto);

    }

    //get Theater BY theater Id


    //Get All Theaters
}
