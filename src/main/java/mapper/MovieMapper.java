package mapper;

import bean.Movie;
import bean.user;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.annotations.Select;

import java.util.List;


public interface MovieMapper {
    @Select("SELECT * FROM movie WHERE name = #{name}")
    Movie getMovieByName(@Param("name")String name);
}
