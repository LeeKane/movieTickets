package service;

import bean.Comment_Maoyan;
import bean.Movie_Maoyan;

import java.util.List;

/**
 * Created by 50210 on 2017/6/10.
 */
public interface MovieService_Maoyan {
    public boolean addMovie(Movie_Maoyan movie);
    public boolean deleteMovie(String id);
    public List getMovieByName(String name);
    public List getAllMovie();
    public Movie_Maoyan getMovieById(String id);

    public boolean addComment(Comment_Maoyan comment);
    public boolean deleteComment(String id);
    public List getCommentByMovieId(String movieId);
}
