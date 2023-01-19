package com.example.BOOK_MY_SHOW_BACKEND.Controllers;


import com.example.BOOK_MY_SHOW_BACKEND.RequestDto.UserRequestDto;
import com.example.BOOK_MY_SHOW_BACKEND.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    //print ticket booked by user

    @GetMapping(value = "/{userId}")
    public String printTicket(@PathVariable int userId) throws Exception {

        try {
            return userService.printTicket(userId);
        } catch (Exception e) {
            return "Ticket Id Does Not Exist";
        }
    }

}
