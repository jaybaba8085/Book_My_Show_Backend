package com.example.BOOK_MY_SHOW_BACKEND.Controllers;


import com.example.BOOK_MY_SHOW_BACKEND.RequestDto.UserRequestDto;
import com.example.BOOK_MY_SHOW_BACKEND.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

@RequestMapping("/User")
public class UserController {

    @Autowired
    UserService userService;


    @PostMapping("/create")
    public String createUser(@RequestBody()UserRequestDto userRequestDto)
    {
         userService.createUser(userRequestDto);
        return "Successfully Added";
    }

   //Find UserBy UserName


    //Find All Users

}
