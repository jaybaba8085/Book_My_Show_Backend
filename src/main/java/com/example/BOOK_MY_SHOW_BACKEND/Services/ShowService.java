package com.example.BOOK_MY_SHOW_BACKEND.Services;

import com.example.BOOK_MY_SHOW_BACKEND.Models.*;
import com.example.BOOK_MY_SHOW_BACKEND.Repository.MovieRepository;
import com.example.BOOK_MY_SHOW_BACKEND.Repository.ShowRepository;
import com.example.BOOK_MY_SHOW_BACKEND.Repository.ShowSeatRepository;
import com.example.BOOK_MY_SHOW_BACKEND.Repository.TheaterRepository;
import com.example.BOOK_MY_SHOW_BACKEND.RequestDto.GetTimeRequestDto;
import com.example.BOOK_MY_SHOW_BACKEND.RequestDto.ShowRequestDto;
import com.example.BOOK_MY_SHOW_BACKEND.ResponseDto.ShowResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ShowService {

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    TheaterRepository theaterRepository;

    @Autowired
    ShowSeatRepository showSeatRepository;

    @Autowired
    ShowRepository showRepository;

    public String addShow(ShowRequestDto showRequestDto){



        //Show Entity
        ShowEntity showEntity = ShowEntity.builder().showDate(showRequestDto.getShowDate()).showTime(showRequestDto.getShowTime()).multiplier(showRequestDto.getMultiplier()).build();


        //You need to get the movieRepo
        MovieEntity movieEntity = movieRepository.findByMovieName(showRequestDto.getMovieName());

        //You need to get the theater Repository

        TheaterEntity theaterEntity = theaterRepository.findById(showRequestDto.getTheaterId()).get();

        showEntity.setTheater(theaterEntity);
        showEntity.setMovie(movieEntity);


        //Optional things :  bcz we are doing a bidirectional mapping..>>
        List<ShowEntity> currentListOfShow = movieEntity.getListOfShows();
        currentListOfShow.add(showEntity);
        movieEntity.setListOfShows(currentListOfShow);



        movieEntity.getListOfShows().add(showEntity);
        theaterEntity.getListOfShows().add(showEntity);
        //theaterRepository.save(theaterEntity);

        List<ShowSeatEntity> seatEntityList = createShowSeats(theaterEntity.getTheaterSeatEntityList());

        showEntity.setListOfSeats(seatEntityList);

        //For each ShowSeat : we need to mark that to which show it belongs (foriegn key will be filled )
        for(ShowSeatEntity showSeat:seatEntityList){
            showSeat.setShow(showEntity);
        }

        movieRepository.save(movieEntity);
        theaterRepository.save(theaterEntity);
        //showRepository.save(showEntity); this doessnt need to be called bcz parent save function is being called.

        return "Show added successfully";

    }

    public List<ShowSeatEntity> createShowSeats(List<TheaterSeatEntity> theaterSeatEntityList){


        List<ShowSeatEntity> seats = new ArrayList<>();

        for(TheaterSeatEntity theaterSeat: theaterSeatEntityList){

            ShowSeatEntity showSeat = ShowSeatEntity.builder().seatNo(theaterSeat.getSeatNo()).seatType(theaterSeat.getSeatType()).build();
            seats.add(showSeat);
        }

        showSeatRepository.saveAll(seats);

        return seats;
    }



    //Get The List of show  between the given time
    public List<ShowResponseDto> getShows(GetTimeRequestDto getTimeRequestDto){

        LocalDateTime from=getTimeRequestDto.getFrom();

        LocalDateTime to=getTimeRequestDto.getTo();


       //It Contains ALl  Shows Which Are Added
        List<ShowEntity> showEntityList=showRepository.findAll();


        List<ShowResponseDto> showResponseDtoList = new ArrayList<>();


        for(ShowEntity showEntity:showEntityList){

            LocalDateTime showDateTime=
                    LocalDateTime.of(showEntity.getShowDate(),showEntity.getShowTime());

            if(showDateTime.compareTo(from)>=0 && showDateTime.compareTo(to)<=0)
            {
                ShowResponseDto showResponseDto
                         = ShowResponseDto.builder()
                        .id(showEntity.getId())
                        .movieName(showEntity.getMovie().getMovieName())
                        .theatreId(showEntity.getTheater().getId())
                        .multiplier(showEntity.getMultiplier()).build();

                showResponseDtoList.add(showResponseDto);
            }
        }

        return showResponseDtoList;
    }
}
