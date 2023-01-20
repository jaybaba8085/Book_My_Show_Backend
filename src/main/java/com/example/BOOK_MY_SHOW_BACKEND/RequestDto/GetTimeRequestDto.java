package com.example.BOOK_MY_SHOW_BACKEND.RequestDto;




import lombok.Data;
import java.time.LocalDateTime;

@Data
public class GetTimeRequestDto {

       private LocalDateTime from;

       private LocalDateTime to;
}
