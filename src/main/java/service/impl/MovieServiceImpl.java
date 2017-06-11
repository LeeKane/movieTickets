package service.impl;

import bean.Movie;
import bean.user;

import mapper.MovieMapper;
import org.springframework.beans.factory.annotation.Autowired;
import service.MovieService;

import java.util.List;


public class MovieServiceImpl implements MovieService {
    @Autowired
    private MovieMapper movieMapper;


    @Override
    public List<user> getAlluser() {
        return null;
    }

    @Override
    public List<Movie> getAllMovies() {
        return movieMapper.getAllMovies();
    }

    @Override
    public Movie getMovieByName(String name) {
        return this.movieMapper.getMovieByName(name);
    }

    @Override
    public Movie getMovieById(int id) {
        return movieMapper.getMovieById(id);
    }


}
