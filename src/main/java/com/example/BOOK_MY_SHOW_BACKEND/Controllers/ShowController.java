package com.example.BOOK_MY_SHOW_BACKEND.Controllers;

import com.example.BOOK_MY_SHOW_BACKEND.RequestDto.ShowRequestDto;
import com.example.BOOK_MY_SHOW_BACKEND.Services.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/show")
public class ShowController {

    @Autowired
    ShowService showService;

    @PostMapping("/add")
    public String addShow(@RequestBody ShowRequestDto showRequestDto){

        return showService.addShow(showRequestDto);
    }

}
