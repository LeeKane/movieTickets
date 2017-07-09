package service;

import bean.Movie;
import bean.Movie_Integrated;
import bean.user;

import java.util.List;


public interface MovieService {
    public List<user> getAlluser();
    public List<Movie> getAllMovies();
    public Movie getMovieByName(String name);
    public Movie getMovieById(int id);

    public List<Movie_Integrated> getAllMoviesIntegrated();
    public List<Movie_Integrated> getMoviesByName(String name);
}

