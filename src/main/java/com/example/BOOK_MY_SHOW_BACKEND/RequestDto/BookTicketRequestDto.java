package com.example.BOOK_MY_SHOW_BACKEND.RequestDto;

import lombok.Data;

import java.util.List;


@Data
public class BookTicketRequestDto {


    private List<String> requestSeats;
    private int showId;
    private int userId;
}
