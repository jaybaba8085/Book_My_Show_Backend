package com.example.BOOK_MY_SHOW_BACKEND.Services;


import com.example.BOOK_MY_SHOW_BACKEND.Convertors.UserConvertor;
import com.example.BOOK_MY_SHOW_BACKEND.Models.UserEntity;
import com.example.BOOK_MY_SHOW_BACKEND.Repository.UserRepository;
import com.example.BOOK_MY_SHOW_BACKEND.RequestDto.UserRequestDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {
    @Autowired
    UserRepository userRepository;

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
}
