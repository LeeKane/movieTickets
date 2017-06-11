package service.impl;

import bean.Comment_Maoyan;
import bean.Movie_Maoyan;
import mapper.MovieMapper;
import org.springframework.beans.factory.annotation.Autowired;
import service.MovieService_Maoyan;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 50210 on 2017/6/10.
 */
public class MovieService_MaoyanImpl implements MovieService_Maoyan{
    @Autowired
    private MovieMapper movieMapper;

    @Override
    public boolean addMovie(Movie_Maoyan movie) {
        try {
            movieMapper.addMovie_Maoyan(movie.getId(), movie.getShowInfo(), movie.getNm(), movie.getSc(),
                    movie.getVer(), movie.getRt(), movie.getScm(), movie.getDir(), movie.getStar(),
                    movie.getCat(), movie.is3d(), movie.isImax(), movie.getWish(), movie.getDur(), movie.getSnum(),
                    movie.getImg(), movie.getDra(), movie.isShowing());
        }catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteMovie(String id) {
        try{
            movieMapper.deleteMovie_Maoyan(id);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List getMovieByName(String name) {
        try{
            return movieMapper.getMovieByName_Maoyan(name);
        }catch (Exception e){
            e.printStackTrace();
            return new ArrayList();
        }
    }

    @Override
    public List getAllMovie() {
        try{
            return movieMapper.getAllMovies_Maoyan();
        }catch (Exception e){
            e.printStackTrace();
            return new ArrayList();
        }

    }

    @Override
    public Movie_Maoyan getMovieById(String id) {
        try{
            return movieMapper.getMovieById_Maoyan(id);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean addComment(Comment_Maoyan comment) {
        try{
            movieMapper.addMovieComment_Maoyan(comment.getId(),comment.getUserId(),
                    comment.getNickName(),comment.getTime(),comment.getApprove(),
                    comment.getAvatarurl(),comment.getContent(),comment.getScore(),
                    comment.getReply(),comment.getOppose(),comment.getMovieId());
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean deleteComment(String id) {
        try{
            movieMapper.deleteComment_Maoyan(id);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List getCommentByMovieId(String movieId) {
        try{
            return movieMapper.getCommentByMovieId_Maoyan(movieId);
        }catch (Exception e){
            e.printStackTrace();
            return new ArrayList();
        }
    }
}
