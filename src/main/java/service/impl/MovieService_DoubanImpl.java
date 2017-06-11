package service.impl;

import mapper.MovieMapper;
import model.Douban.Cast_Douban;
import model.Douban.Movie_Douban;
import model.Douban.Rating_Douban;
import org.springframework.beans.factory.annotation.Autowired;
import service.MovieService_Douban;
import spider.douban.Cast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 50210 on 2017/6/11.
 */
public class MovieService_DoubanImpl implements MovieService_Douban{
    @Autowired
    MovieMapper movieMapper;
    @Override
    public Movie_Douban getMovieById(String id) {
        try{
            return movieMapper.getMovieById_Douban(id);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List getMovieByName(String name) {
        try{
            return movieMapper.getMovieByName_Douban(name);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List getAllMovie() {
        try{
            return movieMapper.getAllMovie_Douban();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean addMovie(Movie_Douban movie) {
        try{
            movieMapper.addMovie_Douban(movie.getReviews_count(), movie.getWish_count(), movie.getCountries(),
                    movie.getGenres(), movie.getCollect_count(), movie.getSummary(), movie.getComments_count(),
                    movie.getRatings_count(), movie.getAka(), movie.getTitle(), movie.getCasts(), movie.getOriginal_title(),
                    movie.getSubtype(), movie.getDirectors(), movie.getYear(), movie.getImages_large(), movie.getImages_middle(),
                    movie.getImages_small(), movie.getAlt(), movie.getId());
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteMovie(String id) {
        try{
            movieMapper.deleteMovie_Douban(id);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Rating_Douban getRatingByMovieId(String id) {
        try{
            return movieMapper.getRatingByMovieId_Douban(id);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean addRating(Rating_Douban rating) {
        try{
            movieMapper.addRating(rating.getMax(), rating.getMin(), rating.getAverage(), rating.getStar(), rating.getMovieId());
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteRating(String id) {
        try{
            movieMapper.deleteRating(id);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Cast_Douban getCastById(String id) {
        return movieMapper.getCastById_Douban(id);
    }

    @Override
    public List getCastByMovie(String movieId) {
        Movie_Douban m = movieMapper.getMovieById_Douban(movieId);
        String[] castsid = m.getCasts().split(",");
        List l = new ArrayList();
        for(String id:castsid){
            l.add(movieMapper.getCastById_Douban(id));
        }
        return l;
    }

    @Override
    public boolean addCast(Cast_Douban cast) {
        try{
            movieMapper.addCast_Douban(cast.getAlt(), cast.getAvatars_large(), cast.getAvatars_middle(), cast.getAvatars_small(), cast.getName(),cast.getId());
            return true;
        }catch (Exception e){
            e.printStackTrace();;
            return false;
        }
    }

    @Override
    public boolean deleteCast(String id) {
        try{
            movieMapper.deleteCast_Douban(id);
            return true;
        }catch (Exception e){
            e.printStackTrace();;
            return false;
        }
    }
}
