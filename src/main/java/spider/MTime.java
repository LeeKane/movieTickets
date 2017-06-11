package spider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.util.NodeList;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class MTime {
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
	
	public String loadJson(String url) {
//		try {
//			Thread.sleep(1000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		防止封ip
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
	
	public void mainMoivePrase(String id){
		String url="http://movie.mtime.com/"+id+"/";
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
		System.out.println(json);
		JSONObject jo=JSONObject.fromObject(json);
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
		JSONObject boxOffice=jo.getJSONObject("boxOffice");
		double TotalBoxOffice=0.0;
		if(boxOffice.has("TotalBoxOffice")){
			TotalBoxOffice=boxOffice.getDouble("TotalBoxOffice");
		}
		else{
			TotalBoxOffice=-1.0;
		}
		double TodayBoxOffice=0.0;
		if(boxOffice.has("TodayBoxOffice")){
			TodayBoxOffice=boxOffice.getDouble("TodayBoxOffice");
		}
		else{
			TodayBoxOffice=-1.0;
		}
		String TotalBoxOfficeUnit="";
		if(boxOffice.has("TotalBoxOfficeUnit")){
			TotalBoxOfficeUnit=boxOffice.getString("TotalBoxOfficeUnit");
		}
		else{
			TotalBoxOfficeUnit="error";
		}
		String TodayBoxOfficeUnit="";
		if(boxOffice.has("TodayBoxOfficeUnit")){
			TodayBoxOfficeUnit=boxOffice.getString("TodayBoxOfficeUnit");
		}
		else{
			TodayBoxOfficeUnit="error";
		}
		int ShowDays=0;
		if(boxOffice.has("ShowDays")){
			ShowDays=boxOffice.getInt("ShowDays");
		}
		else{
			ShowDays=-1;
		}
		Date EndDate;
		if(boxOffice.has("EndDate")){
			 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			 ParsePosition pos = new ParsePosition(0);
			 EndDate=formatter.parse(boxOffice.getString("EndDate"),pos);
		}
		else{
			EndDate=null;
		}
		//
		
		
		
//		Parser parser;
//		try {
//			parser = new Parser(new URL(url).openConnection() );
//			NodeFilter filter = new HasAttributeFilter("class","gradebox __r_c_");
//			NodeList nodes = parser.extractAllNodesThatMatch(filter);
//			if(nodes!=null) {
//				System.out.println(nodes.size());
//	            for (int k = 0; k < nodes.size(); k++) {
//	                Node textnode = (Node) nodes.elementAt(k);
//	                
//	                System.out.println("getText:"+textnode.getText());
//	            }
//	        }
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
		
		
    }
	public static void main(String[] args){
		MTime m=new MTime();
		m.mainMoivePrase(m.urlBuilder("新木乃伊"));
//		String str=";var getSearchResult=result_20176103203852154;";
//		System.out.println(str.length());
//		MTime m=new MTime();
//		m.urlBuilder("新木乃伊");
		
	}
}
