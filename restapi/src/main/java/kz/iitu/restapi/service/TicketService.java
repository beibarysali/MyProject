package kz.iitu.restapi.service;

import kz.iitu.restapi.model.Ticket;
import kz.iitu.restapi.repo.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TicketService {
    TicketRepository ticketRepository;

    @Autowired
    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public List<Ticket> findAllTickets(){
        return ticketRepository.findAll();
    }
    public Ticket buyTicket(Integer movieID, Integer buyerId){
        Ticket ticket = new Ticket();
        ticket.setBuyerId(buyerId);
        ticket.setDateTime(LocalDateTime.now());
        ticket.setMovieId(movieID);
        ticket.setActive(ticket.getDateTime().isAfter(LocalDateTime.now()));
        ticketRepository.save(ticket);
        return ticket;
    }
}
