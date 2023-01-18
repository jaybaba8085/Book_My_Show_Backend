package com.example.BOOK_MY_SHOW_BACKEND.Repository;

import com.example.BOOK_MY_SHOW_BACKEND.Models.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<TicketEntity,Integer> {
}
