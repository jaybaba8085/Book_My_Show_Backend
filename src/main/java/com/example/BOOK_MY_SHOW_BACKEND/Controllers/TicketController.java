package com.example.BOOK_MY_SHOW_BACKEND.Controllers;
import com.example.BOOK_MY_SHOW_BACKEND.RequestDto.BookTicketRequestDto;
import com.example.BOOK_MY_SHOW_BACKEND.Services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ticket")
public class TicketController {

    @Autowired
    TicketService ticketService;

    @PostMapping("/book")
    public String bookTicket(@RequestBody BookTicketRequestDto bookTicketRequestDto) {
        try {
            return ticketService.bookTicket(bookTicketRequestDto);
        } catch (Exception e) {
            return "Requested Seats not available";
        }
    }


        @PutMapping("/cancelTicket")
        public String cancelBookedTicket ( @RequestBody int ticketId)
        {
            try {
                return ticketService.cancelBookedTicked(ticketId);
            } catch (Exception e) {
                return "Can Not Be Cancled Time Exceeded";
            }
        }

        @GetMapping("/printTicket")
    public String printTicket(@RequestBody int ticketId)
        {
            return ticketService.printTicket(ticketId);
        }
    }
