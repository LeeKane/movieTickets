package controller;

import bean.Movie;
import bean.user;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;


@Controller
public class UsersController {
    @Autowired
    private MovieService movieService;

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

}