package spider.douban;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangpan on 2017/6/10.
 */
public class DouBanUtil {
    public DouBanUtil(){
        super();
    }
    public static InputStream getInputStreamByPost(String url) {
        try {
            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setReadTimeout(50000);
            conn.setConnectTimeout(50000);
            conn.setRequestMethod("GET");
            return conn.getInputStream();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String inputStreamToString(InputStream is) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(is));) {
            String line = null;
            while ((line = br.readLine()) != null) {
                return line;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 官方解析方法 --> 不管多复杂都可以解析，但是非常复杂！！！
     *
     * @param json
     */
    public static void jsonParse(String json) {
//        List<Movie> movies = new ArrayList<>();
        try {
            JSONObject object = new JSONObject(json);
            Movie movie = JSONArray_movieInfo(object);
//            movies = JSONArray_movieId(object);
//            for (Movie movie : movies) {
//                System.out.println(movie.getId());
//            }
            print(movie);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * Json数组
     *
     * @param object
     * @return List<Detail>
     * @throws JSONException
     */
    public static List<Movie> JSONArray_movieId(JSONObject object) throws JSONException {
        List<Movie> movies = new ArrayList<>();
        JSONArray array = object.getJSONArray("subjects");
//        System.out.println(array.length());
        for (int i = 0; i < array.length(); i++) {
            JSONObject subject = array.getJSONObject(i);
            String id = subject.getString("id");
//            System.out.println(i+" "+id);
            Movie movie = new Movie();
            movie.setId(id);
            movies.add(movie);
        }
        return movies;
    }

    public static Movie JSONArray_movieInfo(JSONObject object){
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

    private static List<Cast> jsonArrayToCasts(JSONArray casts_J){
        List<Cast> casts = new ArrayList<>();
        for (int i = 0; i < casts_J.length();i++){
            JSONObject cast_J = casts_J.getJSONObject(i);
            Cast cast = new Cast();
            cast.setId(cast_J.getString("id"));
            cast.setName(cast_J.getString("name"));
            cast.setAlt(cast_J.getString("alt"));
            JSONObject avatars_J = cast_J.getJSONObject("avatars");
            cast.setAvatars(jsonObjectToImage(avatars_J));
            casts.add(cast);
        }
        return casts;
    }

    private static Rating jsonObjectToRating(JSONObject rating_J){
        Rating rating = new Rating();
        rating.setMax(rating_J.getInt("max"));
        rating.setAverage(rating_J.getDouble("average"));
        rating.setMin(rating_J.getInt("min"));
        rating.setStar(rating_J.getInt("stars"));
        return rating;
    }

    private static Image jsonObjectToImage(JSONObject image_J){
        Image images = new Image();
        images.setLarge(image_J.getString("large"));
        images.setMedium(image_J.getString("medium"));
        images.setSmall(image_J.getString("small"));
        return images;
    }

    private static String jsonArrayToString(JSONArray array){
        String result ="";
        for (int i = 0;i < array.length();i++){
            result = result+array.getString(i)+"/";
        }
        return result.substring(0,result.length()-1);
    }

    private static void print(Movie movie){
        System.out.println(movie.getId()+" "+movie.getCountries()+"  "+movie.getTitle()+"  "+movie.getAka()+"  "+movie.getGenres());
    }

    public static void main(String[] args) {

        String url = "https://api.douban.com/v2/movie/in_theaters";
//        String parms = "currentPage=" + currentPage + "&size=" + size;
        // 得到服务器响应的数据并转换成String
        String json = DouBanUtil.inputStreamToString(DouBanUtil.getInputStreamByPost(url));
        // Json解析
        JSONObject object = new JSONObject(json);
        List<Movie> movies = DouBanUtil.JSONArray_movieId(object);
        for (Movie movie:movies){
//            url = "https://api.douban.com/v2/movie/subject/"+movie.getId();
            url = "https://api.douban.com/v2/movie/subject/1234";
            json = DouBanUtil.inputStreamToString(DouBanUtil.getInputStreamByPost(url));
            System.out.print(movies.indexOf(movie)+"  ");
            DouBanUtil.jsonParse(json);
        }
    }

}
