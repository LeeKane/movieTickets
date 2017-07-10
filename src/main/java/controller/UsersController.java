package controller;

import bean.Comment_Integrated;
import bean.Maoyan.Comment_Maoyan;
import bean.Movie;
import bean.Maoyan.Movie_Maoyan;
import bean.Movie_Integrated;
import bean.user;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import service.MovieService_Maoyan;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
public class UsersController {
    @Autowired
    private MovieService movieService;
    @Autowired
    private MovieService_Maoyan movieService_maoyan;

    @RequestMapping(value = "/showUser",method = RequestMethod.GET)
    public String showUser(@RequestParam("id") String id, ModelMap modelMap) {
        return "showUser";
    }
    @RequestMapping(value = "/overview")
    public String overView() {
        return "index";
    }
    @ResponseBody
    @RequestMapping(value = "/getMovie",method = RequestMethod.POST)
    public Map getMovie(@RequestBody Map requestName , HttpServletRequest request,
                        HttpServletResponse response) throws Exception {
        Movie movie=movieService.getMovieByName((String) requestName.get("name"));
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("movie",movie);
        return result;
    }
    @ResponseBody
    @RequestMapping(value = "/getAllMovies",method = RequestMethod.GET)
    public Map getAllMovies(){
        //List<Movie_Maoyan> movies=movieService_maoyan.getAllMovie();
        List<Movie_Integrated> integratedMoives=movieService.getAllMoviesIntegrated();
        Map<String, Object> result = new HashMap<String, Object>();
        //result.put("movies",movies);
        result.put("movies",integratedMoives);
        return result;
    }
    @RequestMapping(value = "/movieInfo")
    public ModelAndView movieInfo(HttpServletRequest request,
                                  HttpServletResponse response) throws Exception {
        String name = (String) request.getParameter("id");
        //Movie_Maoyan movie=movieService_maoyan.getMovieById(id);
        List<Movie_Integrated> integratedMoive=movieService.getMoviesByName(name);
        //List<Comment_Maoyan> comments = movieService_maoyan.getCommentByMovieId(id);
        Map<String, Object> result = new HashMap<String, Object>();
        List<Comment_Integrated> comments= integratedMoive.get(0).getComments();
        List<Comment_Integrated> maoyanComments= new ArrayList<Comment_Integrated>();
        List<Comment_Integrated> doubanComments= new ArrayList<Comment_Integrated>();
        List<Comment_Integrated> mtimeComments= new ArrayList<Comment_Integrated>();
        for(int i=0;i<comments.size();i++){
            String source=comments.get(i).getSource();
            if(source.equals("maoyan")){
                maoyanComments.add(comments.get(i));
            }
            if(source.equals("douban")){
                doubanComments.add(comments.get(i));
            }
            if(source.equals("mtime")){
                mtimeComments.add(comments.get(i));
            }
        }
        String tabImg="./static/images/"+integratedMoive.get(0).getId()+".png";
        result.put("movie",integratedMoive.get(0));
        result.put("tabImg",tabImg);
        result.put("maoyanComments",maoyanComments);
        result.put("doubanComments",doubanComments);
        result.put("mtimeComments",mtimeComments);
        return new ModelAndView("movieInfo", "result", result);
    }

}