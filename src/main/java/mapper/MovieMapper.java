package mapper;

import model.MTime.Movie_MTime;
import model.Maoyan.Comment_Maoyan;
import bean.Movie;
import model.Maoyan.Movie_Maoyan;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;


public interface MovieMapper {
    @Select("SELECT * FROM movie WHERE name = #{name}")
    Movie getMovieByName(@Param("name")String name);
    @Select("SELECT * FROM movie WHERE id = #{id}")
    Movie getMovieById(@Param("id") int id);
    @Select("SELECT * FROM movie")
    List<Movie> getAllMovies();

    @Select("SELECT * FROM movie_maoyan WHERE nm like concat(concat('%',#{name}),'%')")
    List<Movie_Maoyan> getMovieByName_Maoyan(@Param("name")String name);
    @Select("SELECT * FROM movie_maoyan WHERE id = #{id}")
    Movie_Maoyan getMovieById_Maoyan(@Param("id") String id);
    @Select("SELECT * FROM movie_maoyan")
    List<Movie_Maoyan> getAllMovies_Maoyan();
    @Insert("insert into movie_maoyan(id,showinfo,nm,sc,ver,rt,scm,dir,star,cat,3d,imax,wish,dur,snum,img,dra,isshowing) values (#{id},#{showinfo},#{nm},#{sc},#{ver},#{rt},#{scm},#{dir},#{star},#{cat},#{3d},#{imax},#{wish},#{dur},#{snum},#{img},#{dra},#{isshowing})")
    int addMovie_Maoyan(@Param("id")String id,@Param("showinfo")String showInfo,@Param("nm")String nm,
                        @Param("sc")double sc,@Param("ver")String ver,@Param("rt")String rt,
                        @Param("scm")String scm,@Param("dir")String dir,@Param("star")String star,
                        @Param("cat")String cat,@Param("3d")boolean is3d,@Param("imax")boolean isImax,
                        @Param("wish")int wish,@Param("dur")int dur,@Param("snum")int snum,
                        @Param("img")String img,@Param("dra")String dra,@Param("isshowing")boolean isShowing);
    @Delete("delete from movie_maoyan where id = #{id}")
    int deleteMovie_Maoyan(@Param("id")String id);

    @Insert("insert into comment_maoyan(id,userid,nickname,time,approve,avatarurl,content,score,reply,oppose,movieid) values (#{id},#{userid},#{nickname},#{time},#{approve},#{avatarurl},#{content},#{score},#{reply},#{oppose},#{movieid})")
    int addMovieComment_Maoyan(@Param("id")String id, @Param("userid")String userid, @Param("nickname")String nickname,
                               @Param("time")Date time, @Param("approve")int approve, @Param("avatarurl")String avatarurl,
                               @Param("content")String content, @Param("score")double score, @Param("reply")int reply, @Param("oppose")int oppose, @Param("movieid")String movieid);
    @Delete("delete from comment_maoyan where id = #{id}")
    int deleteComment_Maoyan(@Param("id")String id);
    @Select("select * from comment_maoyan where movieid = #{id}")
    List<Comment_Maoyan> getCommentByMovieId_Maoyan(@Param("id")String movieId);



    @Insert("insert into movie_mtime(movietitle,ratingfinal,usercount,totalboxoffice,totalboxofficeunit,todayboxoffice,todayboxofficeunit,showdays,enddate,isrelease,id,maoyanid) values (#{mt},#{rf},#{uc},#{tbo},#{tbou},#{tdbo},#{tdbou},#{sd},#{ed},#{ir},#{id},#{maoyanId})")
    int addMovie_MTime(@Param("mt")String movieTitle,@Param("rf")double raitingFinal,
                       @Param("uc")int usercount,@Param("tbo")double totalBoxOffice,
                       @Param("tbou")String totalBoxOfficeUnit,@Param("tdbo")double todayBoxOffice,
                       @Param("tdbou")String todayBoxOfficeUnit,@Param("sd")int showDays,
                       @Param("ed")Date endDate,@Param("ir")boolean isRelease,@Param("id")String id,@Param("maoyanId")String maoyanId);
    @Delete("delete from movie_mtime where id = #{id}")
    int deleteMovie_MtimeById(@Param("id")String id);
    @Delete("delete from movie_mtime where maoyanid = #{id}")
    int deleteMovie_MtimeByMaoyanId(@Param("id")String id);
    @Select("select * from movie_mtime where movietitle like concat(concat('%',#{name}),'%')")
    List<Movie_MTime> getMovieByName_MTime(@Param("name")String name);
    @Select("select * from movie_mtime")
    List<Movie_MTime> getAllMovie_MTime();
    @Select("select * from movie_mtime where id = #{id}")
    Movie_MTime getMovieById_MTime(@Param("id")String id);
    @Select("select * from movie_mtime where maoyanid = #{id}")
    Movie_MTime getMovieByMaoyanId_MTime(@Param("id")String id);

}
