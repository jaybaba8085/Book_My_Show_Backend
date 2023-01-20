package com.example.BOOK_MY_SHOW_BACKEND.Services;
import com.example.BOOK_MY_SHOW_BACKEND.Enums.SeatType;
import com.example.BOOK_MY_SHOW_BACKEND.Models.ShowEntity;
import com.example.BOOK_MY_SHOW_BACKEND.Models.TheaterEntity;
import com.example.BOOK_MY_SHOW_BACKEND.Models.TheaterSeatEntity;
import com.example.BOOK_MY_SHOW_BACKEND.Repository.ShowRepository;
import com.example.BOOK_MY_SHOW_BACKEND.Repository.TheaterRepository;
import com.example.BOOK_MY_SHOW_BACKEND.Repository.TheaterSeatRepository;
import com.example.BOOK_MY_SHOW_BACKEND.RequestDto.TheaterRequestDto;
import com.example.BOOK_MY_SHOW_BACKEND.ResponseDto.TheaterResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TheaterService {

    @Autowired
    TheaterSeatRepository theaterSeatRepository;

    @Autowired
    TheaterRepository theaterRepository;

    @Autowired
    ShowRepository showRepository;

    public String createTheater(TheaterRequestDto theaterRequestDto){


        TheaterEntity theater = TheaterEntity.builder().city(theaterRequestDto.getCity()).name(theaterRequestDto.getName()).address(theaterRequestDto.getAddress()).build();

        List<TheaterSeatEntity> theaterSeats = createTheaterSeats();


        theater.setTheaterSeatEntityList(theaterSeats); //Bidirectional mapping


        //For each theater Seat : we need to set the theaterEntity
        for(TheaterSeatEntity theaterSeat : theaterSeats){
            theaterSeat.setTheater(theater);
        }

        theaterRepository.save(theater);

        return "Theater added successfully";

    }

    private List<TheaterSeatEntity> createTheaterSeats(){


        List<TheaterSeatEntity> seats = new ArrayList<>();

//        TheaterSeatEntity theaterSeat1 = new TheaterSeatEntity("1A", SeatType.CLASSIC,100);
//        TheaterSeatEntity theaterSeat2 = new TheaterSeatEntity("1B", SeatType.CLASSIC,100);
//        TheaterSeatEntity theaterSeat3 = new TheaterSeatEntity("1C", SeatType.CLASSIC,100);
//        TheaterSeatEntity theaterSeat4 = new TheaterSeatEntity("1D", SeatType.CLASSIC,100);
//        TheaterSeatEntity theaterSeat5 = new TheaterSeatEntity("1E", SeatType.CLASSIC,100);
//        TheaterSeatEntity theaterSeat6 = new TheaterSeatEntity("2A", SeatType.PLATINUM,200);
//        TheaterSeatEntity theaterSeat7 = new TheaterSeatEntity("2B", SeatType.PLATINUM,200);
//        TheaterSeatEntity theaterSeat8 = new TheaterSeatEntity("2C", SeatType.PLATINUM,200);
//        TheaterSeatEntity theaterSeat9 = new TheaterSeatEntity("2D", SeatType.PLATINUM,200);
//        TheaterSeatEntity theaterSeat10 = new TheaterSeatEntity("2E", SeatType.PLATINUM,200);

        
        for(int i=0;i<5;i++){

            char ch = (char)('A'+i);

            String seatNo  = "1"+ ch;
            TheaterSeatEntity theaterSeat = new TheaterSeatEntity(seatNo,SeatType.CLASSIC,100);
            seats.add(theaterSeat);
        }
        for(int i=0;i<5;i++){
            char ch = (char)('A'+i);
            String seatNo  = "2"+ ch;
            TheaterSeatEntity theaterSeat = new TheaterSeatEntity(seatNo,SeatType.PLATINUM,200);
            seats.add(theaterSeat);
        }

//        seats.add(theaterSeat1);
//        seats.add(theaterSeat2);
//        seats.add(theaterSeat3);
//        seats.add(theaterSeat4);
//        seats.add(theaterSeat5);
//        seats.add(theaterSeat6);
//        seats.add(theaterSeat7);
//        seats.add(theaterSeat8);
//        seats.add(theaterSeat9);
//        seats.add(theaterSeat10);

        theaterSeatRepository.saveAll(seats);

        return seats;

    }



    //Get All the Theaters...
    public List<TheaterResponseDto> getAllTheaterByMovie(String movieName) {

        List<ShowEntity> showEntityList= showRepository.findAll();

        List<TheaterResponseDto> theaterResponseDtoList = new ArrayList<>();

        for (ShowEntity shows : showEntityList) {

            if (shows.getMovie().getMovieName().equals(movieName))
            {
                TheaterEntity theater = shows.getTheater();
                TheaterResponseDto theatreResponseDto = TheaterResponseDto.builder()
                        .id(theater.getId())
                        .name(theater.getName())
                        .city(theater.getCity())
                        .address(theater.getAddress()).build();

                    theaterResponseDtoList.add(theatreResponseDto);
            }
        }
        return theaterResponseDtoList;
    }

//
//    public List<TheaterEntity> getAllTheatersByMovie(String movieName) {
//
//        List<ShowEntity> showEntityList= showRepository.findAll();
//        List<TheaterEntity> theaterEntityList= new ArrayList<>();
//
//        TheaterEntity theaterEntity=new TheaterEntity();
//
//        for (ShowEntity shows : showEntityList) {
//
//            if (shows.getMovie().getMovieName().equals(movieName))
//            {
//                theaterEntity = shows.getTheater();
//               theaterEntityList.add(theaterEntity);
//            }
//        }
//        return theaterEntityList;
//
//
//    }
}