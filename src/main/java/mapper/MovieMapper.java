package mapper;

import bean.MTime.Comment_MTime;
import bean.MTime.Movie_MTime;
import bean.Maoyan.Comment_Maoyan;
import bean.Movie;
import bean.Maoyan.Movie_Maoyan;
import model.Douban.*;
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



    @Insert("insert into movie_mtime(movietitle,ratingfinal,usercount,totalboxoffice,totalboxofficeunit,todayboxoffice,todayboxofficeunit,showdays,enddate,isrelease,id,maoyanid) values" +
            " (#{mt},#{rf},#{uc},#{tbo},#{tbou},#{tdbo},#{tdbou},#{sd},#{ed},#{ir},#{id},#{maoyanId})")
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
    @Insert("insert into comment_mtime(nickname,avatarurl,time,content,approve,reply,id,mtimeid) values" +
            "(#{nn},#{ava},#{ti},#{co},#{app},#{re},#{id},#{mid})")
    int addComment_MTime(@Param("nn")String nickName,@Param("ava")String avatarurl,@Param("ti")Date time,
                         @Param("co")String content,@Param("app")int approve,@Param("re")int reply,
                         @Param("id")String id,@Param("mid")String mtimeId);
    @Select("select * from comment_mtime where mtimeid = #{id}")
    Comment_MTime getCommentByMTimeId_MTime(@Param("id")String id);
    @Delete("delete from comment_mtime where id = #{id}")
    int deleteComment_MTime(@Param("id") String id);




    @Select("select * from movie_douban where id = #{id}")
    Movie_Douban getMovieById_Douban(@Param("id") String id);
    @Select("select * from movie_douban where title like concat(concat('%',#{name}),'%') or original_title like concat(concat('%',#{name}),'%')")
    List<Movie_Douban> getMovieByName_Douban(@Param("name")String name);
    @Select("select * from movie_douban")
    List<Movie_Douban> getAllMovie_Douban();
    @Insert("insert into movie_douban(reviews_count,wish_count,countries,genres,collect_count,summary,comments_count,ratings_count,aka,title,casts,original_title,subtype,directors,year,images_large,images_middle,images_small,alt,id)" +
            " values (#{rec},#{wc},#{coun},#{gen},#{collc},#{su},#{commc},#{rac},#{aka},#{t},#{ca},#{ot},#{st},#{di},#{year},#{il},#{im},#{is},#{alt},#{id})")
    int addMovie_Douban(@Param("rec")int rec,@Param("wc")int wc,@Param("coun")String coun,
                        @Param("gen")String gen,@Param("collc")int collc,@Param("su")String su,
                        @Param("commc")int commc,@Param("rac")int rac,@Param("aka")String aka,
                        @Param("t")String t,@Param("ca")String ca,@Param("ot")String ot,@Param("st")String st,
                        @Param("di")String di,@Param("year")String year,@Param("il")String il,@Param("im")String im,
                        @Param("is")String is,@Param("alt")String alt,@Param("id")String id);
    @Delete("delete from movie_douban where id = #{id}")
    int deleteMovie_Douban(@Param("id")String id);

    @Select("select * from rating_douban where movieId = #{id}")
    Rating_Douban getRatingByMovieId_Douban(@Param("id")String id);
    @Insert("insert into rating_douban(max,min,average,star,movieid) values (#{max},#{min},#{average},#{star},#{movieid})")
    int addRating(@Param("max")int max,@Param("min")int min,@Param("average")double average,@Param("star")int star,@Param("movieid")String movieid);
    @Delete("delete from rating_douban where movieid = #{id}")
    int deleteRating(@Param("id")String id);

    @Select("select * from cast_douban where id = #{id}")
    Cast_Douban getCastById_Douban(@Param("id")String id);
    @Insert("insert into cast_douban(alt,avatars_large,avatars_middle,avatars_small,name,id) values (#{alt},#{al},#{am},#{as},#{name},#{id})")
    int addCast_Douban(@Param("alt")String alt,@Param("al")String al,@Param("am")String am,@Param("as")String as,@Param("name")String name,@Param("id")String id);
    @Delete("delete from cast_douban where id = #{id}")
    int deleteCast_Douban(@Param("id") String id);

    @Select("select * from comment_douban where movieid = #{id}")
    List<Comment_Douban> getCommentByMovieId_Douban(@Param("id")String id);
    @Select("select * from comment_douban where id = #{id}")
    Comment_Douban getCommentById_Douban(@Param("id")String id);
    @Delete("delete from comment_douban where id = #{id}")
    int deleteComment_Douban(@Param("id")String id);
    @Insert("insert into comment_douban(id,movieid,date,authorid,content,useful,value,subject_id) values" +
            " (#{id},#{movieid},#{date},#{authorid},#{content},#{useful},#{value},#{subject_id}")
    int addComment_Douban(@Param("id")String id,@Param("movieid")String movieId,@Param("date")String date,@Param("authorid")String authorid,@Param("content")String content,
                          @Param("useful")int useful,@Param("value")int value,@Param("subject_id")String subject_id);

    @Select("select * from author_douban where id = #{id}")
    Author_Douban getAuthorById_Douban(@Param("id")String id);
    @Delete("delete from author_douban where id = #{id}")
    int deleteAuthor_Douban(@Param("id")String id);
    @Insert("insert into author_douban(id,uid,name,signature,alt,avatar)")
    int addAuthor_Douban(@Param("id")String id,@Param("uid")String uid,@Param("name")String name,@Param("signature")String signature,@Param("alt")String alt,@Param("avatar")String avatar);
}
