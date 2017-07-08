package spider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import bean.MTime.Comment_MTime;
import bean.MTime.Movie_MTime;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import service.MovieService;
import service.MovieService_MTime;
import service.impl.MovieService_MTimeImpl;

public class MTime {
	MovieService_MTime msm;
	public MTime(MovieService_MTime msm){
		this.msm=msm;
	}
	public String  urlBuilder(String movieName){
		String url="http://service.channel.mtime.com/Search.api?Ajax_CallBack=true&Ajax_CallBackType=Mtime.Channel.Services&Ajax_CallBackMethod=GetSearchResult&Ajax_CrossDomain=1&Ajax_RequestUrl=http//search.mtime.com/search/?q="+movieName+"&t=20176103203852154&Ajax_CallBackArgument0="+movieName+"&Ajax_CallBackArgument1=0&Ajax_CallBackArgument2=628&Ajax_CallBackArgument3=0&Ajax_CallBackArgument4=1";
//		System.out.println(url);
		String response=loadJson(url);
		char[] cr=response.toCharArray();
		int i=0;
		for(i=0;i<cr.length;i++){
			if(cr[i]=='{'){
				break;
			}
		}
		StringBuilder result= new StringBuilder();
		for(int j=i;j<cr.length-46;j++){
			result.append(cr[j]);
		}
		String json=result.toString();
//		System.out.println(json);
		JSONObject jo=JSONObject.fromObject(json);
		JSONArray ja=jo.getJSONObject("value").getJSONObject("movieResult").getJSONArray("moreMovies");
		String name=ja.getJSONObject(0).getString("movieTitle");
		if(json.contains(movieName)){
			String id=ja.getJSONObject(0).getString("movieId");
			url="http://movie.mtime.com/"+id+"/";
			return id;
		}
		System.out.println("No movie named "+movieName);
		return "error";
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
			System.out.println("=============================");
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
	
	public void mainMoivePrase(String id,String MaoyanId){
		String url2="http://service.library.mtime.com/Movie.api?Ajax_CallBack=true&Ajax_CallBackType=Mtime.Library.Services&Ajax_CallBackMethod=GetMovieOverviewRating&Ajax_CrossDomain=1&Ajax_RequestUrl=http://Fmovie.mtime.com/"+id+"/&t=20176101237368064&Ajax_CallBackArgument0="+id;
//		System.out.println(url2);
		String response=loadJson(url2);
		char[] cr=response.toCharArray();
		int i=0;
		for(i=0;i<cr.length;i++){
			if(cr[i]=='{'){
				break;
			}
		}
		StringBuilder result= new StringBuilder();
		for(int j=i;j<cr.length-56;j++){
			result.append(cr[j]);
		}
		String json=result.toString();
//		System.out.println(json);
		JSONObject jo=JSONObject.fromObject(json);
		boolean isRelease=jo.getJSONObject("value").getBoolean("isRelease");
		JSONObject rate=jo.getJSONObject("value").getJSONObject("movieRating");
		String MovieId="";
		if(rate.has("MovieId")){
			MovieId=rate.getString("MovieId");
		}
		else{
			MovieId="error";
		}
		double RatingFinal=0.0;
		if(rate.has("RatingFinal")){
			RatingFinal=rate.getDouble("RatingFinal");
		}
		else{
			RatingFinal=-1.0;
		}
		int Usercount=0;
		if(rate.has("Usercount")){
			Usercount=rate.getInt("Usercount");
		}
		else{
			Usercount=-1;
		}
		
		String movieTitle="";
		if(jo.getJSONObject("value").has("movieTitle")){
			movieTitle=jo.getJSONObject("value").getString("movieTitle");
		}
		else{
			movieTitle="error";
		}
		double TotalBoxOffice=0.0;
		double TodayBoxOffice=0.0;
		String TotalBoxOfficeUnit="";
		String TodayBoxOfficeUnit="";
		Date EndDate=null;
		int ShowDays=0;
		if(jo.getJSONObject("value").has("boxOffice")){
			JSONObject boxOffice=jo.getJSONObject("value").getJSONObject("boxOffice");
//		System.out.println(boxOffice.toString());

			if(boxOffice.has("TotalBoxOffice")){
				TotalBoxOffice=boxOffice.getDouble("TotalBoxOffice");
			}
			else{
				TotalBoxOffice=-1.0;
			}

			if(boxOffice.has("TodayBoxOffice")){
				TodayBoxOffice=boxOffice.getDouble("TodayBoxOffice");
			}
			else{
				TodayBoxOffice=-1.0;
			}

			if(boxOffice.has("TotalBoxOfficeUnit")){
				TotalBoxOfficeUnit=boxOffice.getString("TotalBoxOfficeUnit");
			}
			else{
				TotalBoxOfficeUnit="error";
			}

			if(boxOffice.has("TodayBoxOfficeUnit")){
				TodayBoxOfficeUnit=boxOffice.getString("TodayBoxOfficeUnit");
			}
			else{
				TodayBoxOfficeUnit="error";
			}

			if(boxOffice.has("ShowDays")){
				ShowDays=boxOffice.getInt("ShowDays");
			}
			else{
				ShowDays=-1;
			}

			if(boxOffice.has("EndDate")){
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				ParsePosition pos = new ParsePosition(0);
				EndDate=formatter.parse(boxOffice.getString("EndDate"),pos);
			}
			else{
				EndDate=null;
			}
		}

		Movie_MTime m=new Movie_MTime();
		m.setMovieTitle(movieTitle);
		m.setRatingFinal(RatingFinal);
		m.setUsercount(Usercount);
		m.setTodayBoxOffice(TodayBoxOffice);
		m.setTodayBoxOfficeUnit(TodayBoxOfficeUnit);
		m.setTotalBoxOffice(TotalBoxOffice);
		m.setTotalBoxOfficeUnit(TotalBoxOfficeUnit);
		m.setShowDays(ShowDays);
		m.setEndDate(EndDate);
		m.setRelease(isRelease);
		m.setId(id);
		m.setMaoyanId(MaoyanId);
		msm.addMovie(m);
		String url="http://movie.mtime.com/"+id+"/reviews/short/hot.html";
		commentPrase(url,id);
    }
	
	
	public void commentPrase(String url,String mtimeid){
		try {
			Document doc = Jsoup.connect(url).get();
			Element e=doc.select("#tweetRegion > dd.first > div > div.comboxuser > div.pic_58 > a > img").first();
			String avatarurl=e.attr("src");
			String nickName=e.attr("alt");
			e=doc.select("#tweetRegion > dd.first > div > h3").first();
			String content=e.text();
			e=doc.select("#tweetRegion > dd.first > div").first();
			String id=e.attr("tweetid");
			Date date=null;
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			ParsePosition pos = new ParsePosition(0);
			date=formatter.parse(doc.select("#tweetRegion > dd.first > div > div.comboxuser > div.mt10 > a").first().attr("entertime"),pos);
			String url2="http://service.library.mtime.com/Movie.api?Ajax_CallBack=true&Ajax_CallBackType=Mtime.Library.Services&Ajax_CallBackMethod=GetMovieReviewAndTweetCountInfo&Ajax_CrossDomain=1&Ajax_RequestUrl=http%3A%2F%2Fmovie.mtime.com%2F207927%2Freviews%2Fshort%2Fhot.html&t=20176112147482427&Ajax_CallBackArgument0=&Ajax_CallBackArgument1="+id;
			String response=loadJson(url2);

			char[] cr=response.toCharArray();
			int i=0;
			for(i=0;i<cr.length;i++){
				if(cr[i]=='{'){
					break;
				}
			}
			StringBuilder result= new StringBuilder();
			for(int j=i;j<cr.length-68;j++){
				result.append(cr[j]);
			}
			String json=result.toString();
			JSONObject jo=JSONObject.fromObject(json).getJSONObject("value");
			JSONArray ja=jo.getJSONArray("tweetPraiseCount");
			int approve=ja.getInt(0);
			ja=jo.getJSONArray("tweetCommentCount");
			int reply=ja.getInt(0);
			Comment_MTime cm = new Comment_MTime();
			cm.setApprove(approve);
			cm.setAvtarurl(avatarurl);
			cm.setContent(content);
			cm.setId(id);
			cm.setmTimeId(mtimeid);
			cm.setNickName(nickName);
			cm.setReply(reply);
			cm.setTime(date);
			msm.addComment(cm);
//			System.out.println(reply);
			//此处缺少一个bean生成和数据库调用
//			System.out.println(json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}
	public void mtimeStart(String MovieName,String MaoYanId){
		mainMoivePrase(urlBuilder(MovieName), MaoYanId);
	}
	
	
	public static void main(String[] args){
//		mtime m=new mtime();
////		m.commentPrase("http://movie.mtime.com/207927/reviews/short/hot.html");
//		m.mainMoivePrase(m.urlBuilder("新木乃伊"), "1");
//		
	}
}
