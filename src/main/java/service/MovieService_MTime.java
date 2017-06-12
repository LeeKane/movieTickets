package service;

import bean.MTime.Comment_MTime;
import bean.MTime.Movie_MTime;

import java.util.List;

/**
 * Created by 50210 on 2017/6/11.
 */
public interface MovieService_MTime {
    public boolean addMovie(Movie_MTime movie);
    public boolean deleteMovieByMTimeId(String id);
    public boolean deleteMovieByMaoyanId(String id);
    public List getMovieByName(String name);
    public List getAllMovie();
    public Movie_MTime getMovieByMTimeId(String id);
    public Movie_MTime getMovieByMaoyanId(String id);

    public boolean addComment(Comment_MTime comment);
    public boolean deleteComment(String id);
    public Comment_MTime getCommentByMTimeId(String id);



}
