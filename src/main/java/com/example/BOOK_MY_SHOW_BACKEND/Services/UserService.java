package com.example.BOOK_MY_SHOW_BACKEND.Services;


import com.example.BOOK_MY_SHOW_BACKEND.Convertors.UserConvertor;
import com.example.BOOK_MY_SHOW_BACKEND.Models.TicketEntity;
import com.example.BOOK_MY_SHOW_BACKEND.Models.UserEntity;
import com.example.BOOK_MY_SHOW_BACKEND.Repository.MovieRepository;
import com.example.BOOK_MY_SHOW_BACKEND.Repository.ShowRepository;
import com.example.BOOK_MY_SHOW_BACKEND.Repository.TicketRepository;
import com.example.BOOK_MY_SHOW_BACKEND.Repository.UserRepository;
import com.example.BOOK_MY_SHOW_BACKEND.RequestDto.UserRequestDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    ShowRepository showRepository;

    @Autowired
    TicketRepository ticketRepository;

    public String createUser(UserRequestDto userRequestDto)
    {
        try {
          UserEntity user = UserConvertor.convertDtoToEntity(userRequestDto);
           userRepository.save(user);
        } catch (Exception e) {
            log.info("createAuthor has caused an error");
            return "Create User didn't work";
        }
        return "User created successfully";
    }

    public String printTicket(int userId)  throws Exception {

        Optional<UserEntity> userEntity = userRepository.findById(userId);

        if (!userEntity.isPresent()) {
            throw new Exception("Ticket with given id not found");
        }
       
            UserEntity userEntity1 = userEntity.get();
            List<TicketEntity> listOfTickets = userEntity1.getListOfTickets();

            String movieName="";
            String theaterName="";
            LocalTime showTime = null;
            String allotedSeats="";
            String location ="";
            
            for (TicketEntity t : listOfTickets) {

              movieName = t.getShow().getMovie().getMovieName();
              theaterName = t.getShow().getTheater().getName();
              location =t.getShow().getTheater().getCity();
              showTime = t.getShow().getShowTime();
              allotedSeats = t.getAlloted_seats();
            }
       
        return  "Movie Name : "   + movieName         +"\n"+
                "Theater Name : " + theaterName       +"\n"+
                "Location : "     + location          +"\n"+
                "Show Time : "    + showTime          +"\n"+
                "Allotted Seats : "+ allotedSeats;
    }
}
