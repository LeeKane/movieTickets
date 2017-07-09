package spider.douban;

import model.Douban.*;
import net.sf.json.JSONException;
import net.sf.json.JSONNull;
import org.json.JSONArray;
import org.json.JSONObject;
import service.MovieService_Douban;
import spider.douban.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangpan on 2017/7/9.
 */
public class DouBan {
    MovieService_Douban msd;
    public DouBan(MovieService_Douban msd){
        this.msd=msd;
    }

    public static String loadJson(String urlPath){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
//		防止封ip
        StringBuffer sb = new StringBuffer();
        try {
            URL url = new URL(urlPath);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
            connection.connect();
            connection.setConnectTimeout(30000);
            connection.setReadTimeout(30000);

            //读取urlPath的内容
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
            String str = null;

            while ((str = bufferedReader.readLine()) != null) {
                sb.append(str);
            }
            bufferedReader.close();
            connection.disconnect();
        }catch(Exception e){
            e.printStackTrace();
        }
        return sb.toString();
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
        movie.setDirectors(jsonArrayToCasts(directors));

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
            System.out.println(cast_J.toString());
            Cast cast = new Cast();
            if (!(cast_J.get("id") instanceof JSONNull)) {
                cast.setId(cast_J.getString("id"));
            }
            cast.setName(cast_J.getString("name"));
            if (!(cast_J.get("alt") instanceof JSONNull))
                cast.setAlt(cast_J.getString("alt"));
            if (!(cast_J.get("avatars") instanceof JSONNull)) {
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

    public void searchMovie(String query){
        List<Movie> temps = new ArrayList<>();
        List<Movie> movies = new ArrayList<>();
        String url = "https://api.douban.com/v2/movie/search?q="+query+"&count=1";
        String json = loadJson(url);
        if (json != null && !json.isEmpty()){
            JSONObject object = new JSONObject(json);
            temps = JSONArray_movieId(object);
        }
        Movie movie = new Movie();
        if (temps.size()>0)
            movie = temps.get(0);
        url = "https://api.douban.com/v2/movie/subject/" + movie.getId();
//           System.out.println(movie.getId());
        json = loadJson(url);
        if (json != null && !json.isEmpty()) {
            JSONObject object = new JSONObject(json);
            movie = JSONArray_movieInfo(object);
            Movie_Douban movie_douban = new Movie_Douban();
            Rating_Douban rating_douban = new Rating_Douban();
            movie_douban.setAka(movie.getAka());
            movie_douban.setAlt(movie.getAlt());
            movie_douban.setId(movie.getId());
            movie_douban.setCollect_count(movie.getCollect_count());
            movie_douban.setComments_count(movie.getComments_count());
            movie_douban.setCountries(movie.getCountries());
            movie_douban.setGenres(movie.getGenres());
            movie_douban.setYear(movie.getYear());
            movie_douban.setImages_large(movie.getImages().getLarge());
            movie_douban.setImages_middle(movie.getImages().getMedium());
            movie_douban.setImages_small(movie.getImages().getSmall());
            movie_douban.setOriginal_title(movie.getOriginal_title());
            movie_douban.setWish_count(movie.getWish_count());
            movie_douban.setTitle(movie.getTitle());
            movie_douban.setSummary(movie.getSummary());
            movie_douban.setReviews_count(movie.getReviews_count());
            movie_douban.setRatings_count(movie.getRatings_count());
            movie_douban.setSubtype(movie.getSubtype());
            List<Cast> casts = new ArrayList<Cast>();
            casts = movie.getCasts();
            String casrStr = "";
            for (int i = 0;i<casts.size();i++){
                Cast_Douban cast_douban = new Cast_Douban();
                Cast cast = casts.get(i);
                cast_douban.setName(cast.getName());
                cast_douban.setId(cast.getId());
                cast_douban.setAlt(cast.getAlt());
//                    System.out.println(movie.toString()+" "+object.toString()+" "+movie.getTitle()+" "+cast.getName()+" "+cast.getAvatars());

                cast_douban.setAvatars_large(cast.getAvatars().getLarge());
                cast_douban.setAvatars_middle(cast.getAvatars().getMedium());
                cast_douban.setAvatars_small(cast.getAvatars().getSmall());
                msd.addCast(cast_douban);
                casrStr += cast.getId()+",";
            }
            if (casrStr.length()>0)
                casrStr.substring(0,casrStr.length()-1);
            movie_douban.setCasts(casrStr);
            List<Cast> directors = new ArrayList<Cast>();
            directors = movie.getDirectors();
            String dirStr = "";
            for (int i = 0;i<directors.size();i++){
                Cast_Douban cast_douban = new Cast_Douban();
                Cast cast = directors.get(i);
                cast_douban.setName(cast.getName());
                cast_douban.setId(cast.getId());
                cast_douban.setAlt(cast.getAlt());
                cast_douban.setAvatars_large(cast.getAvatars().getLarge());
                cast_douban.setAvatars_middle(cast.getAvatars().getMedium());
                cast_douban.setAvatars_small(cast.getAvatars().getSmall());
                msd.addCast(cast_douban);
                dirStr += cast.getId()+",";
            }
            if (dirStr.length()>0)
                dirStr.substring(0,dirStr.length()-1);
            movie_douban.setDirectors(dirStr);
            msd.addMovie(movie_douban);
            rating_douban.setMovieId(movie.getId());
            rating_douban.setMax(movie.getRating().getMax());
            rating_douban.setAverage(movie.getRating().getAverage());
            rating_douban.setMin(movie.getRating().getMin());
            rating_douban.setStar(movie.getRating().getStar());
            msd.addRating(rating_douban);
            searchComment(movie_douban.getId());
        }

    }

    public void searchComment(String id){
        List<Comment> comments = new ArrayList<>();
        String apikey = "?apikey=0b2bdeda43b5688921839c8ecb20399b";
        String url = "https://api.douban.com/v2/movie/subject/"+id+"/comments"+apikey;
        String json = loadJson(url);
        if (json != null && !json.isEmpty()){
            JSONObject object = new JSONObject(json);
            comments = JSONArray_comments(object);
        }
        for (int i = 0;i<comments.size();i++){
            Comment comment = comments.get(i);
            Comment_Douban comment_douban = new Comment_Douban();
            comment_douban.setMovieId(id);
            comment_douban.setId(comment.getId());
            comment_douban.setContent(comment.getContent());
            comment_douban.setDate(comment.getDate());
            comment_douban.setSubject_id(comment.getSubject_id());
            comment_douban.setUseful(comment.getUseful());
            comment_douban.setAuthorId(comment.getAuthor().getId());
            msd.addComment(comment_douban);
            Author_Douban author_douban = new Author_Douban();
            Author author = comment.getAuthor();
            author_douban.setAvatar(author.getAvatar());
            author_douban.setName(author.getName());
            author_douban.setId(author.getId());
            author_douban.setUid(author.getUid());
            author_douban.setSignature(author.getSignature());
            author_douban.setAlt(author.getAlt());
            msd.addAuthor(author_douban);

        }
    }
    public static void main(String[] args){

    }
}
