package service.impl;

import bean.MTime.Comment_MTime;
import bean.MTime.Movie_MTime;
import mapper.MovieMapper;
import org.springframework.beans.factory.annotation.Autowired;
import service.MovieService_MTime;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 50210 on 2017/6/11.
 */
public class MovieService_MTimeImpl implements MovieService_MTime{
    @Autowired
    MovieMapper movieMapper;


    @Override
    public boolean addMovie(Movie_MTime movie) {
        try{
            movieMapper.addMovie_MTime(movie.getMovieTitle(),movie.getRatingFinal(), movie.getUsercount(),
                    movie.getTotalBoxOffice(),movie.getTotalBoxOfficeUnit(), movie.getTodayBoxOffice(),
                    movie.getTodayBoxOfficeUnit(),movie.getShowDays(), movie.getEndDate(), movie.isRelease(),
                    movie.getId(), movie.getMaoyanId());
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteMovieByMTimeId(String id) {
        try{
            movieMapper.deleteMovie_MtimeById(id);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteMovieByMaoyanId(String id) {
        try{
            movieMapper.deleteMovie_MtimeByMaoyanId(id);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List getMovieByName(String name) {
        try{
            return movieMapper.getMovieByName_MTime(name);
        }catch (Exception e){
            e.printStackTrace();
            return new ArrayList();
        }

    }

    @Override
    public List getAllMovie() {
        try{
            return movieMapper.getAllMovie_MTime();
        }catch (Exception e){
            e.printStackTrace();
            return new ArrayList();
        }
    }

    @Override
    public Movie_MTime getMovieByMTimeId(String id) {
        try {
            return movieMapper.getMovieById_MTime(id);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Movie_MTime getMovieByMaoyanId(String id) {
        try {
            return movieMapper.getMovieByMaoyanId_MTime(id);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean addComment(Comment_MTime comment) {
        try{
            movieMapper.addComment_MTime(comment.getNickName(),comment.getAvtarurl(),comment.getTime(),
                    comment.getContent(),comment.getApprove(),comment.getReply(),comment.getId(),comment.getmTimeId());
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteComment(String id) {
            try{
                movieMapper.deleteComment_MTime(id);
                return true;
            }catch (Exception e){
                e.printStackTrace();
                return false;
            }
    }

    @Override
    public List<Comment_MTime> getCommentByMTimeId(String id) {
        return movieMapper.getCommentByMTimeId_MTime(id);
    }
}
