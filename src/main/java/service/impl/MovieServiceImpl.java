package service.impl;

import bean.Comment_Integrated;
import bean.MTime.Comment_MTime;
import bean.MTime.Movie_MTime;
import bean.Maoyan.Comment_Maoyan;
import bean.Maoyan.Movie_Maoyan;
import bean.Movie;
import bean.Movie_Integrated;
import bean.user;

import mapper.MovieMapper;
import model.Douban.Comment_Douban;
import model.Douban.Movie_Douban;
import org.springframework.beans.factory.annotation.Autowired;
import service.MovieService;
import spider.douban.Comment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class MovieServiceImpl implements MovieService {
    @Autowired
    private MovieMapper movieMapper;

    List<Movie_Maoyan> movielist_maoyan;

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

    @Override
    public List<Movie_Integrated> getAllMoviesIntegrated() {
        movielist_maoyan = movieMapper.getAllMovies_Maoyan();
        List<Movie_Integrated> newmvlist = new ArrayList<Movie_Integrated>();
        for(Movie_Maoyan mv_maoyan:movielist_maoyan){

            Movie_Integrated newmv = new Movie_Integrated();
            List<Comment_Integrated> newcms = new ArrayList<Comment_Integrated>();
            newmv.setId(mv_maoyan.getId());
            newmv.setName(mv_maoyan.getNm());
            newmv.setShowinfo(mv_maoyan.getShowInfo());
            newmv.setScoremaoyan(mv_maoyan.getSc());
            newmv.setVer(mv_maoyan.getVer());
            newmv.setScm(mv_maoyan.getScm());
            newmv.setShowdate(mv_maoyan.getRt());
            newmv.setDescription(mv_maoyan.getDra());
            newmv.setDir(mv_maoyan.getDir());
            newmv.setStar(mv_maoyan.getStar());
            newmv.setCat(mv_maoyan.getCat());
            newmv.setImg(mv_maoyan.getImg());
            newmv.setDur(mv_maoyan.getDur());

            List oldcms = movieMapper.getCommentByMovieId_Maoyan(mv_maoyan.getId());
            for(Object o:oldcms){
                Comment_Maoyan cm = (Comment_Maoyan) o;
                Comment_Integrated newcm = new Comment_Integrated();
                newcm.setSource("maoyan");
                newcm.setUsername(cm.getNickName());
                newcm.setTime(cm.getTime());
                newcm.setContent(cm.getContent());
                newcm.setAvatarurl(cm.getAvatarurl());
                newcm.setApprove(cm.getApprove());
                newcms.add(newcm);
            }

            Movie_MTime mv_mtime = movieMapper.getMovieByMaoyanId_MTime(mv_maoyan.getId());
            if(mv_mtime!=null){
                newmv.setScoremtime(mv_mtime.getRatingFinal());
                oldcms = movieMapper.getCommentByMTimeId_MTime(mv_mtime.getId());
                for(Object o:oldcms){
                    Comment_MTime oldcm = (Comment_MTime) o;
                    Comment_Integrated newcm = new Comment_Integrated();
                    newcm.setSource("mtime");
                    newcm.setUsername(oldcm.getNickName());
                    newcm.setTime(oldcm.getTime());
                    newcm.setContent(oldcm.getContent());
                    newcm.setAvatarurl(oldcm.getAvtarurl());
                    newcms.add(newcm);
                }
            }



            List<Movie_Douban> mvs_douban = movieMapper.getMovieByName_Douban(mv_maoyan.getNm());

            if(!mvs_douban.isEmpty()&& mvs_douban.get(0).getTitle().equals(mv_maoyan.getNm())){
                Movie_Douban mv_douban = mvs_douban.get(0);
                newmv.setDescription(newmv.getDescription().length()<mv_douban.getSummary().length()?mv_douban.getSummary():newmv.getDescription());
                newmv.setScoredouban(movieMapper.getRatingByMovieId_Douban(mv_douban.getId()).getAverage());

                oldcms = movieMapper.getCommentByMovieId_Douban(mv_douban.getId());
                for(Object o:oldcms){
                    Comment_Douban cm = (Comment_Douban)o;
                    Comment_Integrated newcm = new Comment_Integrated();
                    newcm.setSource("douban");
                    newcm.setAvatarurl(movieMapper.getAuthorById_Douban(cm.getAuthorId()).getAvatar());
                    newcm.setContent(cm.getContent());
                    SimpleDateFormat sdf;
                    sdf = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
                    try {
                        newcm.setTime(sdf.parse(cm.getDate()));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    newcm.setUsername(movieMapper.getAuthorById_Douban(cm.getAuthorId()).getName());
                    newcms.add(newcm);
                }
            }
            newmv.setComments(newcms);
            newmvlist.add(newmv);
        }
        return newmvlist;
    }

    @Override
    public List<Movie_Integrated> getMoviesByName(String name) {
        movielist_maoyan = movieMapper.getMovieByName_Maoyan(name);
        List<Movie_Integrated> newmvlist = new ArrayList<Movie_Integrated>();
        for(Movie_Maoyan mv_maoyan:movielist_maoyan){

            Movie_Integrated newmv = new Movie_Integrated();
            List<Comment_Integrated> newcms = new ArrayList<Comment_Integrated>();
            newmv.setId(mv_maoyan.getId());
            newmv.setName(mv_maoyan.getNm());
            newmv.setShowinfo(mv_maoyan.getShowInfo());
            newmv.setScoremaoyan(mv_maoyan.getSc());
            newmv.setVer(mv_maoyan.getVer());
            newmv.setScm(mv_maoyan.getScm());
            newmv.setShowdate(mv_maoyan.getRt());
            newmv.setDescription(mv_maoyan.getDra());
            newmv.setDir(mv_maoyan.getDir());
            newmv.setStar(mv_maoyan.getStar());
            newmv.setCat(mv_maoyan.getCat());
            newmv.setImg(mv_maoyan.getImg());
            newmv.setDur(mv_maoyan.getDur());

            List oldcms = movieMapper.getCommentByMovieId_Maoyan(mv_maoyan.getId());
            for(Object o:oldcms){
                Comment_Maoyan cm = (Comment_Maoyan) o;
                Comment_Integrated newcm = new Comment_Integrated();
                newcm.setSource("maoyan");
                newcm.setUsername(cm.getNickName());
                newcm.setTime(cm.getTime());
                newcm.setContent(cm.getContent());
                newcm.setAvatarurl(cm.getAvatarurl());
                newcm.setApprove(cm.getApprove());
                newcms.add(newcm);
            }

            Movie_MTime mv_mtime = movieMapper.getMovieByMaoyanId_MTime(mv_maoyan.getId());
            if(mv_mtime!=null){
                newmv.setScoremtime(mv_mtime.getRatingFinal());
                oldcms = movieMapper.getCommentByMTimeId_MTime(mv_mtime.getId());
                for(Object o:oldcms){
                    Comment_MTime oldcm = (Comment_MTime) o;
                    Comment_Integrated newcm = new Comment_Integrated();
                    newcm.setSource("mtime");
                    newcm.setUsername(oldcm.getNickName());
                    newcm.setTime(oldcm.getTime());
                    newcm.setContent(oldcm.getContent());
                    newcm.setApprove(oldcm.getApprove());
                    newcm.setAvatarurl(oldcm.getAvtarurl());
                    newcms.add(newcm);
                }
            }



            List<Movie_Douban> mvs_douban = movieMapper.getMovieByName_Douban(mv_maoyan.getNm());

            if(!mvs_douban.isEmpty()&& mvs_douban.get(0).getTitle().equals(mv_maoyan.getNm())){
                Movie_Douban mv_douban = mvs_douban.get(0);
                newmv.setDescription(newmv.getDescription().length()<mv_douban.getSummary().length()?mv_douban.getSummary():newmv.getDescription());
                newmv.setScoredouban(movieMapper.getRatingByMovieId_Douban(mv_douban.getId()).getAverage());

                oldcms = movieMapper.getCommentByMovieId_Douban(mv_douban.getId());
                for(Object o:oldcms){
                    Comment_Douban cm = (Comment_Douban)o;
                    Comment_Integrated newcm = new Comment_Integrated();
                    newcm.setSource("douban");
                    newcm.setAvatarurl(movieMapper.getAuthorById_Douban(cm.getAuthorId()).getAvatar());
                    newcm.setApprove(cm.getUseful());
                    newcm.setContent(cm.getContent());
                    SimpleDateFormat sdf;
                    sdf = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
                    try {
                        newcm.setTime(sdf.parse(cm.getDate()));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    newcm.setUsername(movieMapper.getAuthorById_Douban(cm.getAuthorId()).getName());
                    newcms.add(newcm);
                }
            }
            newmv.setComments(newcms);
            newmvlist.add(newmv);
        }
        return newmvlist;
    }


}
