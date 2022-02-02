package kz.iitu.restapi.service;

import kz.iitu.restapi.model.Movie;
import kz.iitu.restapi.repo.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Service
public class MovieService {
    MovieRepository movieRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<Movie> findAll(){
        return movieRepository.findAll();
    }
    public Movie addMovie(Movie movie){
        return movieRepository.save(movie);
    }
    public Movie createMovie(String title, String category, String director, String imdb, Integer yearRelease, Integer rateMovie, String fileName){
        Movie movie = new Movie();
        movie.setImage(fileName);
        movie.setTitle(title);
        movie.setCategory(category);
        movie.setDirector(director);
        movie.setImdb(imdb);
        movie.setYearRelease(yearRelease);
        movie.setRateMovie(rateMovie);
        movieRepository.save(movie);
        return movie;
    }
    public Movie getMovieById(Integer id){
        return movieRepository.getOne(id);
    }
    public void deleteMovieById(Integer id){
        movieRepository.deleteById(id);
    }
    public boolean existMovieById(Integer id){
        return movieRepository.existsById(id);
    }
    public Movie saveToDbAndReturn(MultipartFile image, String title, String category, String director, String imdb, Integer yearRelease, Integer rateMovie){
        Movie movie = new Movie();
        movie.setTitle(title);
        movie.setCategory(category);
        movie.setDirector(director);
        movie.setImdb(imdb);
        movie.setYearRelease(yearRelease);
        movie.setRateMovie(rateMovie);
        try {
            movie.setImage(Base64.getEncoder().encodeToString(image.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        movieRepository.save(movie);
        return movie;
    }
    public void updateMovie(Integer id, Movie updatedMovie){
        Movie movie = movieRepository.getById(id);
        movie.setTitle(updatedMovie.getTitle());
        movie.setCategory(updatedMovie.getCategory());
        movie.setDirector(updatedMovie.getDirector());
        movie.setImdb(updatedMovie.getImdb());
        movie.setYearRelease(updatedMovie.getYearRelease());
        movie.setRateMovie(updatedMovie.getRateMovie());
        movieRepository.save(movie);
    }
}
