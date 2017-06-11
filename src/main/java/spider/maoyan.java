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

import bean.Comment_Maoyan;
import bean.Movie_Maoyan;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import service.MovieService_Maoyan;
import service.impl.MovieService_MaoyanImpl;

public class maoyan {
	public final int limit=10;//每次获取数量
	public final int loop=100;//循环次数
	public final int climit=5;
	public final int cloop=10;//评论获取循环次数
	MovieService_Maoyan ms=new MovieService_MaoyanImpl();
//	CommentService_Maoyan cs=new CommentService_MaoyanImpl();
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
	
	//猫眼搜索总入口
	public void mainMoivePrase(){
//		String url="http://m.maoyan.com/movie/list.json?type=hot&offset=0&limit=10";
		for(int i=1;i<=loop;i++){
			String url=mainMovieAdddressBuilder(i);
			String movieList=loadJson(url);
			JSONObject jo=JSONObject.fromObject(movieList);
			JSONArray array=jo.getJSONObject("data").getJSONArray("movies");
			for(int j=0;j<array.size();j++){	
				JSONObject movie=array.getJSONObject(j);
				String showInfo;
				if(movie.has("showInfo")){
					showInfo=movie.getString("showInfo");
				}
				else{
					showInfo="error";
				}
				String nm;
				if(movie.has("nm")){
					nm=movie.getString("nm");
				}
				else{
					nm="error";
				}
				double sc;
				if(movie.has("sc")){
					sc=movie.getDouble("sc");
				}
				else{
					sc=-1;
				}
				String ver;
				if(movie.has("ver")){
					ver=movie.getString("ver");
				}
				else{
					ver="error";
				}
				String rt;
				if(movie.has("rt")){
					rt=movie.getString("rt");
				}
				else{
					rt="error";
				}
				String scm;
				if(movie.has("scm")){
					scm=movie.getString("scm");
				}
				else{
					scm="error";
				}
				String dir;
				if(movie.has("dir")){
					dir=movie.getString("dir");
				}
				else{
					dir="error";
				}
				String star;
				if(movie.has("star")){
					star=movie.getString("star");
				}
				else{
					star="error";
				}
				String cat;
				if(movie.has("cat")){
					cat=movie.getString("cat");
				}
				else{
					cat="error";
				}
				boolean b3d;
				if(movie.has("3d")){
					b3d=movie.getBoolean("3d");
				}
				else{
					b3d=false;
				}
				boolean imax;
				if(movie.has("imax")){
					imax=movie.getBoolean("imax");
				}
				else{
					imax=false;
				}
				int wish;
				if(movie.has("wish")){
					wish=movie.getInt("wish");
				}
				else{
					wish=-1;
				}
				int dur;
				if(movie.has("dur")){
					dur=movie.getInt("dur");
				}
				else{
					dur=-1;
				}
				int snum;
				if(movie.has("snum")){
					snum=movie.getInt("snum");
				}
				else{
					snum=-1;
				}
				String id = "1";
				if(movie.has("id")){
					id=movie.getString("id");
				}
				else{
					id="error";
				}
				String img;
				if(movie.has("img")){
					img=movie.getString("img");
				}
				else{
					img="error";
				}
				
				url=commentsAddressBulider(id);
				String detailList=loadJson(url);
				JSONObject djo=JSONObject.fromObject(detailList);
				JSONObject datajo=djo.getJSONObject("data");
				String dra;
				if(datajo.has("dra")){
					dra=movie.getString("dra");
				}
				else{
					dra="error";
				}
				boolean isShowing;
				if(datajo.has("imax")){
					isShowing=movie.getBoolean("imax");
				}
				else{
					isShowing=false;
				}
				/*
				 * 此处缺少一个javabean整合与数据层传输
				 */
				Movie_Maoyan mmy=new Movie_Maoyan();
				mmy.setShowInfo(showInfo);
				mmy.setNm(nm);
				mmy.setSc(sc);
				mmy.setVer(ver);
				mmy.setRt(rt);
				mmy.setScm(scm);
				mmy.setDir(dir);
				mmy.setStar(star);
				mmy.setCat(cat);
				mmy.setIs3d(b3d);
				mmy.setIsimax(imax);
				mmy.setWish(wish);
				mmy.setDur(dur);
				mmy.setSnum(snum);
				mmy.setImg(img);
				mmy.setId(id);
				mmy.setDra(dra);
				mmy.setShowing(isShowing);
				ms.addMovie(mmy);
				
				JSONArray dataArray=djo.getJSONObject("CommentResponseModel").getJSONArray("cmts");
				for(int k=0;k<dataArray.size();j++){
					commentParse(dataArray.getJSONObject(k),id);
				}//详情页自带的15个评论
				for(int k=1;k<=loop;k++){
					url=moreCommentsAddressBulider(id,k);
					String commentList=loadJson(url);
					JSONObject cjo=JSONObject.fromObject(commentList);
					JSONObject cdjo=cjo.getJSONObject("data");
					JSONArray commentArray=cdjo.getJSONObject("CommentResponseModel").getJSONArray("cmts");
					for(int l=0;l<commentArray.size();l++){
						commentParse(commentArray.getJSONObject(l),id);
					}
					if(!cdjo.getBoolean("hasNext")){
						break;
					}
				}//剩余评论
				
			}
			if(!jo.getJSONObject("data").getBoolean("hasNext")){
				break;
			}
		}
		
		
	}
	
	public String mainMovieAdddressBuilder(int num){
		String url="http://m.maoyan.com/movie/list.json?type=hot&offset="+(num-1)*limit+"&limit="+limit;
		return url;
	}
	public String commentsAddressBulider(String id){
		String url="http://m.maoyan.com/movie/"+id+".json";
		return url;
	}
	public String moreCommentsAddressBulider(String id,int num){
		String url="http://m.maoyan.com/comments.json?movieid="+id+"&limit="+climit+"&offset="+((num-1)*climit+15);
		return url;
	}
	
	public void commentParse(JSONObject jo,String movieId){
		String  userId;
		if(jo.has("userId")){
			userId=jo.getString("userId");
		}
		else{
			userId="error";
		}
		String  nickName;
		if(jo.has("nickName")){
			nickName=jo.getString("nickName");
		}
		else{
			nickName="error";
		}
		String  avatarurl;
		if(jo.has("avatarurl")){
			avatarurl=jo.getString("avatarurl");
		}
		else{
			avatarurl="error";
		}
		String  id;
		if(jo.has("id")){
			id=jo.getString("id");
		}
		else{
			id="error";
		}
		String  content;
		if(jo.has("content")){
			content=jo.getString("content");
		}
		else{
			content="error";
		}
		Date time;
		if(jo.has("time")){
			 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			 ParsePosition pos = new ParsePosition(0);
			 time=formatter.parse(jo.getString("time"),pos);
		}
		else{
			time=null;
		}
		int approve;
		if(jo.has("approve")){
			approve=jo.getInt("approve");
		}
		else{
			approve=-1;
		}
		int reply;
		if(jo.has("reply")){
			reply=jo.getInt("reply");
		}
		else{
			reply=-1;
		}
		int oppose;
		if(jo.has("oppose")){
			oppose=jo.getInt("oppose");
		}
		else{
			oppose=-1;
		}
		double score;
		if(jo.has("score")){
			score=jo.getDouble("score");
		}
		else{
			score=-1.0;
		}
		Comment_Maoyan cmy=new Comment_Maoyan();
		cmy.setUserId(userId);
		cmy.setNickName(nickName);
		cmy.setTime(time);
		cmy.setApprove(approve);
		cmy.setAvatarurl(avatarurl);
		cmy.setId(id);
		cmy.setContent(content);
		cmy.setScore(score);
		cmy.setReply(reply);
		cmy.setOppose(oppose);
		cmy.setMovieId(movieId);
//		cs.addComment(cmy);
		
		
		
		
		
	}
	
	
//	public static void main(String[] args) {
//		String str="{\"UserName\":\"ZHULI\",\"age\":\"30\",\"workIn\":\"ALI\",\"Array\":[\"ZHULI\",\"30\",\"ALI\"]}";
//		System.out.println(str);
//		JSONObject jo=JSONObject.fromObject(str);
//		JSONArray ja=jo.getJSONArray("Array");
//	}
}
