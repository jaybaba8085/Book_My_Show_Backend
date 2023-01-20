package com.example.BOOK_MY_SHOW_BACKEND.Controllers;

import com.example.BOOK_MY_SHOW_BACKEND.Models.TheaterEntity;
import com.example.BOOK_MY_SHOW_BACKEND.RequestDto.TheaterRequestDto;
import com.example.BOOK_MY_SHOW_BACKEND.ResponseDto.TheaterResponseDto;
import com.example.BOOK_MY_SHOW_BACKEND.Services.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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


    //Get All Theaters By Movie Name
    @GetMapping("/find_theaters_by_movie_name")
    public ResponseEntity<List<TheaterResponseDto>> getAllTheaterByMovie(@RequestParam String movieName){
        try{
            List<TheaterResponseDto> theaterResponseDtoList=theaterService.getAllTheaterByMovie(movieName);
            return new ResponseEntity<>(theaterResponseDtoList,HttpStatus.FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

//        Response Come only in Format of JSON  that Why In this I get Complicated Output

//    @GetMapping("/find_theater_by_movie_name")
//    public ResponseEntity<List<TheaterEntity>> getAllTheatersByMovie(@RequestParam String movieName){
//        try{
//            List<TheaterEntity> theaterList=theaterService.getAllTheatersByMovie(movieName);
//            return new ResponseEntity<>(theaterList,HttpStatus.FOUND);
//        }catch (Exception e){
//            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
//        }
//    }

}
