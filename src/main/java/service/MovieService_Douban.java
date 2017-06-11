package service;

import model.Douban.Cast_Douban;
import model.Douban.Movie_Douban;
import model.Douban.Rating_Douban;
import spider.douban.Cast;

import java.util.List;

/**
 * Created by 50210 on 2017/6/11.
 */
public interface MovieService_Douban {
        public Movie_Douban getMovieById(String id);
        public List getMovieByName(String name);
        public List getAllMovie();
        public boolean addMovie(Movie_Douban movie);
        public boolean deleteMovie(String id);

        public Rating_Douban getRatingByMovieId(String id);
        public boolean addRating(Rating_Douban rating);
        public boolean deleteRating(String id);

        public Cast_Douban getCastById(String id);
        public List getCastByMovie(String movieId);
        public boolean addCast(Cast_Douban cast);
        public boolean deleteCast(String id);

}
