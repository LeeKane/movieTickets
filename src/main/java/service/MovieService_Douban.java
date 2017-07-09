package service;

import model.Douban.*;
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

        public List<Comment_Douban> getCommentByMovieId(String movieId);
        public Comment_Douban getCommentBtId(String id);
        public boolean addComment(Comment_Douban comment);
        public boolean deleteComment(String id);

        public Author_Douban getAuthor(String id);
        public boolean addAuthor(Author_Douban author);
        public boolean deleteAuthor(String id);



}
