package com.example.BOOK_MY_SHOW_BACKEND.Controllers;

import com.example.BOOK_MY_SHOW_BACKEND.RequestDto.GetTimeRequestDto;
import com.example.BOOK_MY_SHOW_BACKEND.RequestDto.ShowRequestDto;
import com.example.BOOK_MY_SHOW_BACKEND.ResponseDto.ShowResponseDto;
import com.example.BOOK_MY_SHOW_BACKEND.Services.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/show")
public class ShowController {

    @Autowired
    ShowService showService;

    @PostMapping("/add")
    public String addShow(@RequestBody ShowRequestDto showRequestDto){

        return showService.addShow(showRequestDto);
    }


    //Get the list of All shows  Between Given Time Range
    @GetMapping("/get_list_of_show")
    public ResponseEntity<List<ShowResponseDto>> getAllShows(@RequestBody GetTimeRequestDto getTimeRequestDto){
        try
        {
            List<ShowResponseDto> showResponseDtoList = showService.getShows(getTimeRequestDto);
            return new ResponseEntity<>(showResponseDtoList,HttpStatus.FOUND);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }


    }

}
