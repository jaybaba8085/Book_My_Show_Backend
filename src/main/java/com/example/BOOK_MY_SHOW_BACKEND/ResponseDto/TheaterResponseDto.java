package com.example.BOOK_MY_SHOW_BACKEND.ResponseDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TheaterResponseDto {


    private int id;

    private String name;

    private String city;

    private String address;

}