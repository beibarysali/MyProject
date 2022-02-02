package kz.iitu.restapi.controller;

import kz.iitu.restapi.model.Movie;
import kz.iitu.restapi.model.Ticket;
import kz.iitu.restapi.model.User;
import kz.iitu.restapi.service.MovieService;
import kz.iitu.restapi.service.TicketService;
import kz.iitu.restapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TicketController {

    MovieService movieService;
    TicketService ticketService;
    UserService userService;

    @Autowired
    public TicketController(MovieService movieService, TicketService ticketService, UserService userService) {
        this.movieService = movieService;
        this.ticketService = ticketService;
        this.userService = userService;
    }


    @RequestMapping("/buy/{id}")
    public void buyMovie(@PathVariable("id") Integer id, @ModelAttribute("user") User user){
        Movie movie = movieService.getMovieById(id);
        ticketService.buyTicket(movie.getId(), user.getId());
    }
}
