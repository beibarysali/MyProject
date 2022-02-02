package kz.iitu.restapi.controller;

import kz.iitu.restapi.model.Movie;
import kz.iitu.restapi.service.FileUploadUtil;
import kz.iitu.restapi.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class MovieController {
    MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping({"/", "/home"})
    public String getHome(Model model){
        model.addAttribute("movieList", movieService.findAll());
        return "index";
    }

    @RequestMapping("/create")
    public String getPageNewMovie(Model model){
        Movie movie = new Movie();
        model.addAttribute("movie", movie);
        return "addmovie";
    }

    @PostMapping("/add")
    public String addMovieById(Model model,
                               @RequestParam(name = "title") String title,
                               @RequestParam(name = "category") String category,
                               @RequestParam(name = "director") String director,
                               @RequestParam(name = "imdb") String imdb,
                               @RequestParam(name = "yearRelease") Integer yearRelease,
                               @RequestParam(name = "rateMovie") Integer rateMovie,
                               @RequestParam(name = "image") MultipartFile multipartFile) throws IOException{
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        Movie movie = movieService.createMovie(title, category, director, imdb, yearRelease, rateMovie, fileName);
        String uploadDir = "images/" + movie.getId();
        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
        model.addAttribute("movie", movie);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String getUpdatePage(@PathVariable("id") Integer id, Model model){
        model.addAttribute("movie", movieService.getMovieById(id));
        return "edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable("id") Integer id, @ModelAttribute("movie") Movie updatedMovie){
        movieService.updateMovie(id, updatedMovie);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteMovieById(@PathVariable("id") Integer id) throws IOException{
        if(movieService.existMovieById(id)) {
            Movie movie = movieService.getMovieById(id);
            movieService.deleteMovieById(id);
            String deleteDir = "images/" + movie.getId();
            FileUploadUtil.deleteFile(deleteDir);
        }
        return "redirect:/";
    }

    @DeleteMapping("/{id}")
    public String deleteMovie(@PathVariable("id") Integer id){
        if(movieService.existMovieById(id)) {
            movieService.deleteMovieById(id);
        }
        return "redirect:/";
    }
    @GetMapping("/details/{id}")
    public String movieDetails(@PathVariable("id") Integer id, Model model){
        model.addAttribute("movie", movieService.getMovieById(id));
        return "details";
    }
    @RequestMapping("/about")
    public String getPageAbout(){
        return "about";
    }


}
