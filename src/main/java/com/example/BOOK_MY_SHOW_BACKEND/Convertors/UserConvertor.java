package com.example.BOOK_MY_SHOW_BACKEND.Convertors;

import com.example.BOOK_MY_SHOW_BACKEND.Models.UserEntity;
import com.example.BOOK_MY_SHOW_BACKEND.RequestDto.UserRequestDto;

public class UserConvertor {

    public static UserEntity convertDtoToEntity(UserRequestDto userRequestDto){

       UserEntity user =  UserEntity.builder()
               .name(userRequestDto.getName())
               .mobile(userRequestDto.getMobile()).build();

        return  user;
    }

}
