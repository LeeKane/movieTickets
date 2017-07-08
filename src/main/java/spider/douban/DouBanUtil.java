package spider.douban;

import net.sf.json.JSONNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangpan on 2017/6/10.
 */
public class DouBanUtil {
    public DouBanUtil(){
        super();
    }

    private static String loadJson(String url) {
        StringBuilder json = new StringBuilder();
        try {
            URL oracle = new URL(url);
            URLConnection yc = oracle.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    yc.getInputStream(),"UTF-8"));
            String inputLine = null;
            while ((inputLine = in.readLine()) != null) {
                json.append(inputLine);
            }
            in.close();
        } catch (MalformedURLException e) {
        } catch (IOException e) {
        }
        return json.toString();
    }

    private List<Movie> JSONArray_movieId(JSONObject object) throws JSONException {
        List<Movie> movies = new ArrayList<>();
        JSONArray array = object.getJSONArray("subjects");
        for (int i = 0; i < array.length(); i++) {
            JSONObject subject = array.getJSONObject(i);
            String id = subject.getString("id");
            Movie movie = new Movie();
            movie.setId(id);
            movies.add(movie);
        }
        return movies;
    }

    private Movie JSONArray_movieInfo(JSONObject object){

        Movie movie = new Movie();
        movie.setId(object.getString("id"));
        movie.setWish_count(object.getInt("wish_count"));
        movie.setReviews_count(object.getInt("reviews_count"));
        movie.setYear(object.getString("year"));
        movie.setTitle(object.getString("title"));
        movie.setCollect_count(object.getInt("collect_count"));
        movie.setOriginal_title(object.getString("original_title"));
        movie.setSummary(object.getString("summary"));
        movie.setSubtype(object.getString("subtype"));
        movie.setComments_count(object.getInt("comments_count"));
        movie.setRatings_count(object.getInt("ratings_count"));
        movie.setAlt(object.getString("alt"));

        JSONObject rating_J = object.getJSONObject("rating");
        movie.setRating(jsonObjectToRating(rating_J));

        JSONArray casts = object.getJSONArray("casts");
//        System.out.println(object.getJSONArray("casts").toString());
        movie.setCasts(jsonArrayToCasts(casts));

        JSONArray directors = object.getJSONArray("directors");
        movie.setCasts(jsonArrayToCasts(directors));

        JSONObject images = object.getJSONObject("images");
        movie.setImages(jsonObjectToImage(images));

        JSONArray countries = object.getJSONArray("countries");
        movie.setCountries(jsonArrayToString(countries));

        JSONArray genres = object.getJSONArray("genres");
        movie.setGenres(jsonArrayToString(genres));

        JSONArray aka = object.getJSONArray("aka");
        movie.setAka(jsonArrayToString(aka));

        return movie;
    }

    private List<Comment> JSONArray_comments(JSONObject object){
        List<Comment> comments= new ArrayList<>();
        JSONArray array = object.getJSONArray("comments");
        for (int i = 0; i < array.length(); i++) {
            Comment comment = new Comment();
            JSONObject jsonObject = array.getJSONObject(i);

            JSONObject rating = jsonObject.getJSONObject("rating");
            comment.setValue(rating.getInt("value"));

            comment.setUseful(jsonObject.getInt("useful_count"));
            comment.setId(jsonObject.getString("id"));
            comment.setDate(jsonObject.getString("created_at"));
            comment.setContent(jsonObject.getString("content"));
            comment.setSubject_id(jsonObject.getString("subject_id"));

            JSONObject author = jsonObject.getJSONObject("author");
            comment.setAuthor(jsonObjectToAuthor(author));
            comments.add(comment);
        }
        return comments;
    }

    private List<Cast> jsonArrayToCasts(JSONArray casts_J){
        List<Cast> casts = new ArrayList<>();
        for (int i = 0; i < casts_J.length();i++){
            JSONObject cast_J = casts_J.getJSONObject(i);
//            System.out.println(cast_J.toString());
            Cast cast = new Cast();
            if (cast_J.get("id") instanceof JSONNull) {
                cast.setId(cast_J.getString("id"));
            }
            cast.setName(cast_J.getString("name"));
            if (cast_J.get("alt") instanceof JSONNull)
                cast.setAlt(cast_J.getString("alt"));
            if (cast_J.get("avatars") instanceof JSONNull) {
                JSONObject avatars_J = cast_J.getJSONObject("avatars");
                cast.setAvatars(jsonObjectToImage(avatars_J));
            }
            casts.add(cast);
        }
        return casts;
    }

    private Rating jsonObjectToRating(JSONObject rating_J){
        Rating rating = new Rating();
        rating.setMax(rating_J.getInt("max"));
        rating.setAverage(rating_J.getDouble("average"));
        rating.setMin(rating_J.getInt("min"));
        rating.setStar(rating_J.getInt("stars"));
        return rating;
    }

    private Author jsonObjectToAuthor(JSONObject author_J){
        Author author = new Author();
        author.setId(author_J.getString("id"));
        author.setName(author_J.getString("name"));
        author.setAlt(author_J.getString("alt"));
        author.setAvatar(author_J.getString("avatar"));
        author.setSignature(author_J.getString("signature"));
        author.setUid(author_J.getString("uid"));
        return author;
    }

    private Image jsonObjectToImage(JSONObject image_J){
        Image images = new Image();
        images.setLarge(image_J.getString("large"));
        images.setMedium(image_J.getString("medium"));
        images.setSmall(image_J.getString("small"));
        return images;
    }

    private String jsonArrayToString(JSONArray array){
        String result ="";
        for (int i = 0;i < array.length();i++){
            result = result+array.getString(i)+"/";
        }
        if (result.length()>0) {
            result =  result.substring(0, result.length() - 1);
        }
        return result;
    }

    private void printMovie(Movie movie){
        System.out.println(movie.getId()+" "+movie.getCountries()+"  "+movie.getTitle()+"  "+movie.getAka()+"  "+movie.getGenres());
    }

    private void printComment(Comment comment){
        System.out.println(comment.getId()+"  "+comment.getDate()+"  "+comment.getAuthor()+"  "+comment.getUseful()+"  "+comment.getContent());
    }

    public List<Movie> searchMovie(String query){
        List<Movie> temps = new ArrayList<>();
        List<Movie> movies = new ArrayList<>();
        String url = "https://api.douban.com/v2/movie/search?q="+query;
        String json = loadJson(url);
        if (json != null && !json.isEmpty()){
            JSONObject object = new JSONObject(json);
            temps = JSONArray_movieId(object);
        }
        for (Movie movie : temps) {
            url = "https://api.douban.com/v2/movie/subject/" + movie.getId();
//            System.out.println(movie.getId());
            json = loadJson(url);
            if (json != null && !json.isEmpty()) {
                JSONObject object = new JSONObject(json);
                movie = JSONArray_movieInfo(object);
                movies.add(movie);
            }
        }

        return movies;
    }

    public List<Comment> searchComment(String id){
        List<Comment> comments = new ArrayList<>();
        String apikey = "?apikey=0b2bdeda43b5688921839c8ecb20399b";
        String url = "https://api.douban.com/v2/movie/subject/"+id+"/comments"+apikey;
        String json = loadJson(url);
        if (json != null && !json.isEmpty()){
            JSONObject object = new JSONObject(json);
            comments = JSONArray_comments(object);
        }
        return comments;
    }

    	public static void main(String[] args) {
		DouBanUtil douBanUtil = new DouBanUtil();
		List<Movie> movies = douBanUtil.searchMovie("新木乃伊");
		for (Movie movie:movies){
		    douBanUtil.printMovie(movie);
        }

        Movie movie = movies.get(0);
		List<Comment> comments = douBanUtil.searchComment(movie.getId());
		for (Comment comment:comments){
            douBanUtil.printComment(comment);
        }

	}



}
