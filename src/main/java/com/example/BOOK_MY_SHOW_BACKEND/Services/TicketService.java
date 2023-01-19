package com.example.BOOK_MY_SHOW_BACKEND.Services;
import com.example.BOOK_MY_SHOW_BACKEND.Enums.SeatType;
import com.example.BOOK_MY_SHOW_BACKEND.Models.*;
import com.example.BOOK_MY_SHOW_BACKEND.Repository.*;
import com.example.BOOK_MY_SHOW_BACKEND.RequestDto.BookTicketRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TicketService {

    @Autowired
    ShowRepository showRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TicketRepository ticketRepository;
    @Autowired
    private ShowSeatRepository showSeatRepository;

    @Autowired
    TheaterSeatRepository theaterSeatRepository;

    public String bookTicket(BookTicketRequestDto bookTicketRequestDto)throws Exception{

        //Get the request
        List<String> requestedSeats = bookTicketRequestDto.getRequestSeats();

        ShowEntity showEntity = showRepository.findById(bookTicketRequestDto.getShowId()).get();

        UserEntity userEntity = userRepository.findById(bookTicketRequestDto.getUserId()).get();

        //Get the showSeats from showEntity
        List<ShowSeatEntity> showSeats = showEntity.getListOfSeats();

        //Validate if I can allocate these seats to the requested by user.

        List<ShowSeatEntity> bookedSeats = new ArrayList<>();

        for(ShowSeatEntity showSeat : showSeats){

            String seatNo = showSeat.getSeatNo();

            if(showSeat.isBooked()==false&&requestedSeats.contains(seatNo)){
                bookedSeats.add(showSeat);
            }
        }


        //FAILURE
        if(bookedSeats.size()!=requestedSeats.size()){
            //Some the seats the useRequested are not available
            throw new Exception("Requested seats are not available");
        }


        //SUCCESS
        //This means the bookedSeats actually contains the booked Seats.
        TicketEntity ticketEntity = new TicketEntity();

        double totalAmout = 0;
        double multiplier = showEntity.getMultiplier();


        String allotedSeats  = "";

        int rate = 0;
        //Calculating amount,setting bookedStatus, setting
        for(ShowSeatEntity bookedSeat: bookedSeats){

            bookedSeat.setBooked(true);
            bookedSeat.setBookedAt(new Date());
            bookedSeat.setTicket(ticketEntity);
            bookedSeat.setShow(showEntity);

            String seatNo = bookedSeat.getSeatNo();

            allotedSeats = allotedSeats + seatNo + ",";

            if(seatNo.charAt(0)=='1')
                rate = 100;
            else
                rate = 200;

            totalAmout = totalAmout + multiplier*rate;
        }

        ticketEntity.setBooked_at(new Date());
        ticketEntity.setAmount((int)totalAmout);
        ticketEntity.setShow(showEntity);
        ticketEntity.setUser(userEntity);
        ticketEntity.setBookedSeats(bookedSeats);
        ticketEntity.setAlloted_seats(allotedSeats);

        //Bidirectional mapping part is pending

        ticketRepository.save(ticketEntity);

        return "Successfully created a ticket";
    }

    public String cancelBookedTicked(int ticketId) throws Exception {

        Optional<TicketEntity> ticketEntity = ticketRepository.findById(ticketId);
        TicketEntity ticket = ticketEntity.get();


        List<ShowSeatEntity> bookedSeats = ticket.getBookedSeats();

        ShowEntity showEntity = ticketEntity.get().getShow();
        List<ShowSeatEntity> showSeats = showEntity.getListOfSeats();

        List<TheaterSeatEntity> seats = new ArrayList<>();

        for(ShowSeatEntity bookedseats : showSeats) {

            String seatNo=bookedseats.getSeatNo();
            SeatType st =bookedseats.getSeatType();
            TheaterSeatEntity theaterSeat;
            if(seatNo.charAt(0)=='1') {
                theaterSeat = new TheaterSeatEntity(seatNo, st, 100);
                seats.add(theaterSeat);
            }
            else {
                theaterSeat = new TheaterSeatEntity(seatNo, st, 200);
                seats.add(theaterSeat);
            }

        }

        List<ShowSeatEntity> bookedSeatss = ticket.getBookedSeats();
        for (ShowSeatEntity seat : bookedSeatss) {
            seat.setBooked(false);
            seat.setBookedAt(null);
            seat.setTicket(null);
            showSeatRepository.save(seat);
        }
        theaterSeatRepository.saveAll(seats);
        ticketRepository.delete(ticket);

        double charge =ticket.getAmount()* 0.2;

        return  "Ticket Successfully Cancelled And Charge Deducted :" + charge ;
    }

}
