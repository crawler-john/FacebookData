package com.MyApp.FaceBookData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.MyApp.FaceBookCralwer.CrawlBase;
import com.MyApp.FaceBookModel.universityAlbum;
import com.MyApp.FaceBookModel.universityCategory;
import com.MyApp.FaceBookModel.universityCoverModel;
import com.MyApp.FaceBookModel.universityFeed;
import com.MyApp.FaceBookModel.universityInfoModel;
import com.MyApp.FaceBookModel.universityLocation;
import com.MyApp.FaceBookModel.universityMilestone;
import com.MyApp.FaceBookModel.universityPhoto;
import com.MyApp.FaceBookModel.universityVideo;
import com.MyApp.FaceBookUtil.jsonUtil;
import com.fasterxml.jackson.databind.JsonNode;

public class universityInfo extends CrawlBase {
	// 设置头信息头信息
	private static HashMap<String, String> params;
	static {
		params = new HashMap<String, String>();
		params.put("Host", "graph.facebook.com");
		params.put("User-Agent",
				"Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1)");
	}
	public universityInfo(String url) {
		readJsonByGet(url, params, "utf-8");
	}
	
	public static int count_feeds=0;
	public static String accessToken = "CAATvgqd5bWoBAAaZC1SBopDBg1YZB88DNO89GXv1bAguGGVA6aZCAZCi6ZAi2VZBE7UpBIqu4wJWZAMu9dhiznHKT1MMkE0U9ob4veZCrq45gjD6hFSk8G7Wxu139tkpanZCHcOgc6Wt2VBESdUunyXFUNDhrPLLQRXoZASHzwrs5Fq5kZCgbUojbCEElCToaEnBZCwZD";
	private static List<universityAlbum> albums = new ArrayList<universityAlbum>();
	private static List<universityFeed> feeds = new ArrayList<universityFeed>();
	private static List<universityMilestone> milestones = new ArrayList<universityMilestone>();
	private static List<universityPhoto> photos = new ArrayList<universityPhoto>();
	private static List<universityVideo> videos = new ArrayList<universityVideo>();
	//通过json数据解析到相应的数据
	public universityInfoModel analyzer(){
		long before = System.currentTimeMillis();
		universityInfoModel info = new universityInfoModel();
		info.setId(Long.parseLong(getValueByKey("id")));
		info.setName(getValueByKey("name"));
		info.setAbout(getValueByKey("about"));
		info.setCanPost(Boolean.parseBoolean(getValueByKey("can_post")));
		info.setCategoryList(getCategoryList());
		info.setCover(getCover());
		info.setDescription(getValueByKey("description"));
		info.setEmails(getEmails());
		info.setFounded(getValueByKey("founded"));
		info.setGeneralInfo(getValueByKey("general_info"));
		info.setGlobalBrandPageName(getValueByKey("global_brand_page_name"));
		info.setAlwaysOpen(Boolean.parseBoolean(getValueByKey("is_always_open")));
		info.setCommunityPage(Boolean.parseBoolean(getValueByKey("is_community_page")));
		info.setPublished(Boolean.parseBoolean(getValueByKey("is_published")));
		info.setVerified(Boolean.parseBoolean(getValueByKey("is_verified")));
		info.setLink(getValueByKey("link"));
		info.setLocation(getLocation());
		info.setPhone(getValueByKey("phone"));
		info.setTalkingAboutCount(Integer.parseInt(getValueByKey("talking_about_count")));
		info.setUsername(getValueByKey("username"));
		info.setWebsite(getValueByKey("website"));
		info.setWereHereCount(Integer.parseInt(getValueByKey("were_here_count")));
		info.setAlbums(getAlbums());
		info.setCheckins(Integer.parseInt(getValueByKey("checkins")));
		info.setFeeds(getFeeds());
		info.setLikes(Integer.parseInt(getValueByKey("likes")));
		info.setMilestones(getMilestone());
		info.setPhotos(getPhotos());
		info.setVideos(getVideos());
		long after = System.currentTimeMillis();
		System.out.println(getValueByKey("name")+"获取数据成功"+",抓取数据用时"+(after-before)+"ms");
		albums.clear();
		feeds.clear();
		milestones.clear();
		photos.clear();
		videos.clear();
		return info;
	}
	
	private  List<universityVideo> getVideos() {	
		JsonNode root = jsonUtil.jsonToObject(this.getJsonSourceCode());
		JsonNode videosNode = root.findValue("videos");
		if(null != videosNode){
			JsonNode videosData = videosNode.findValue("data");
			for(int i = 0;i<videosData.size();i++){
				videos.add(new universityVideo((null == videosData.get(i).get("description"))?null:videosData.get(i).get("description").asText(), videosData.get(i).get("updated_time").asText(), videosData.get(i).get("id").asLong()));
			}
			if(null != videosNode.findValue("next")){
				getVideos(videosNode.findValue("next").asText());
			}
		}
		
		System.out.println(getValueByKey("name") + "获取vedios成功");
		return videos;
	}
	 private static void getVideos(String nextUrl){
		JsonNode videosNode = jsonUtil.jsonToObject(new universityInfo(nextUrl).getJsonSourceCode());
		
		JsonNode videosData = videosNode.findValue("data");
		if(null != videosData){
			for(int i = 0;i<videosData.size();i++){
				//System.out.println(i);
				videos.add(new universityVideo((null ==videosData.get(i).get("description"))?null:videosData.get(i).get("description").asText(), videosData.get(i).get("updated_time").asText(), videosData.get(i).get("id").asLong()));
			}
			if(null != videosNode.findValue("next")){
				//System.out.println(videosNode.findValue("next").asText());
				getVideos(videosNode.findValue("next").asText());
			}
		}
		
	}
	
	
	
	//采集照片
	private  List<universityPhoto> getPhotos() {	
		JsonNode root = jsonUtil.jsonToObject(this.getJsonSourceCode());
		JsonNode photosNode = root.findValue("photos");
		if(null != photosNode){
			JsonNode photosData = photosNode.findValue("data");
			for(int i = 0;i<photosData.size();i++){
				photos.add(new universityPhoto(photosData.get(i).get("created_time").asText(),(null == photosData.get(i).get("name"))?"": photosData.get(i).get("name").asText(), photosData.get(i).get("id").asLong()));
			}
			if(null != photosNode.findValue("next")){
				getPhotos(photosNode.findValue("next").asText());
			}
		}
		
		System.out.println(getValueByKey("name") + "获取photos成功");
		return photos;
	}
	 private static void getPhotos(String nextUrl){
		JsonNode photosNode = jsonUtil.jsonToObject(new universityInfo(nextUrl).getJsonSourceCode());
		
		JsonNode photosData = photosNode.findValue("data");
		for(int i = 0;i<photosData.size();i++){
			photos.add(new universityPhoto(photosData.get(i).get("created_time").asText(),(null == photosData.get(i).get("name"))?"": photosData.get(i).get("name").asText(), photosData.get(i).get("created_time").asInt()));
		}
		if(null != photosNode.findValue("next")){
			getPhotos(photosNode.findValue("next").asText());
		}
	}
	
	
	
	
	
	//获取里程碑列表
	private  List<universityMilestone> getMilestone() {	
		JsonNode root = jsonUtil.jsonToObject(this.getJsonSourceCode());
		JsonNode milestonesNode = root.findValue("milestones");
		if(null != milestonesNode){
			JsonNode milestonesData = milestonesNode.findValue("data");
			for(int i = 1;i<milestonesData.size();i++){
				universityMilestone t_Milestone = new universityMilestone();
				t_Milestone.setId(milestonesData.get(i).get("id").asLong());
				t_Milestone.setCreateTime(milestonesData.get(i).get("created_time").asText());
				t_Milestone.setDescription((null == milestonesData.get(i).get("description"))?"":milestonesData.get(i).get("description").asText());
				t_Milestone.setEndTime(milestonesData.get(i).get("end_time").asText());
				t_Milestone.setHidden(milestonesData.get(i).get("is_hidden").asBoolean());
				t_Milestone.setFromId(milestonesData.get(i).get("from").get("id").asLong());
				t_Milestone.setFromName(milestonesData.get(i).get("from").get("name").asText());
				t_Milestone.setStartTime(milestonesData.get(i).get("start_time").asText());
				t_Milestone.setTitle(milestonesData.get(i).get("id").asText());
				t_Milestone.setUpdateTime(milestonesData.get(i).get("updated_time").asText());
				
				milestones.add(t_Milestone);
			}
			if(null != milestonesNode.findValue("next")){
				
				getMilestone(milestonesNode.findValue("next").asText());
			}
		}
	
		System.out.println(getValueByKey("name") + "获取milestones成功");
		return milestones;
	}
	 private static void getMilestone(String nextUrl){
		JsonNode milestonesNode = jsonUtil.jsonToObject(new universityInfo(nextUrl).getJsonSourceCode());
		
		JsonNode milestonesData = milestonesNode.findValue("data");
		for(int i = 0;i<milestonesData.size();i++){
			universityMilestone t_Milestone = new universityMilestone();
			t_Milestone.setId(milestonesData.get(i).get("id").asLong());
			t_Milestone.setCreateTime(milestonesData.get(i).get("created_time").asText());
			t_Milestone.setDescription((null == milestonesData.get(i).get("description"))?"":milestonesData.get(i).get("description").asText());
			t_Milestone.setEndTime(milestonesData.get(i).get("end_time").asText());
			t_Milestone.setHidden(milestonesData.get(i).get("is_hidden").asBoolean());
			t_Milestone.setFromId(milestonesData.get(i).get("from").get("id").asLong());
			t_Milestone.setFromName(milestonesData.get(i).get("from").get("name").asText());
			t_Milestone.setStartTime(milestonesData.get(i).get("start_time").asText());
			t_Milestone.setTitle(milestonesData.get(i).get("id").asText());
			t_Milestone.setUpdateTime(milestonesData.get(i).get("updated_time").asText());
			milestones.add(t_Milestone);
		}
		if(null != milestonesNode.findValue("next")){
			getMilestone(milestonesNode.findValue("next").asText());
		}
	}
	
	
	
	//采集feed信息
	private  List<universityFeed> getFeeds() {	
		System.out.println("正在解析feeds...");
		JsonNode root = jsonUtil.jsonToObject(this.getJsonSourceCode());
		JsonNode feedsNode = root.findValue("feed");
		if(null != feedsNode){
			JsonNode feedsData = feedsNode.findValue("data");
			for(int i = 0;i<feedsData.size();i++){
				count_feeds++;
				feeds.add(new universityFeed(((null == feedsData.get(i).get("message"))?null:feedsData.get(i).get("message").asText()),((null == feedsData.get(i).get("story"))?null:feedsData.get(i).get("story").asText()),feedsData.get(i).get("created_time").asText(), feedsData.get(i).get("id").asText()));
			}
			if(null != feedsNode.findValue("next")){
				//System.out.println(feedsNode.findValue("next").asText());
				getFeeds(feedsNode.findValue("next").asText());
			}
		}
	
		System.out.println(getValueByKey("name") + "获取feeds成功");
		System.out.println("获得的feed的数量是"+count_feeds);
		count_feeds=0;
		return feeds;
	}
	 private static void getFeeds(String nextUrl){
		JsonNode feedsNode = jsonUtil.jsonToObject(new universityInfo(nextUrl).getJsonSourceCode());
		
		JsonNode feedsData = feedsNode.findValue("data");
		for(int i = 0;i<feedsData.size();i++){
			//System.out.println(i);
			count_feeds++;
		feeds.add(new universityFeed((null == feedsData.get(i).get("message"))?null:feedsData.get(i).get("message").asText(),(null == feedsData.get(i).get("story"))?null:feedsData.get(i).get("story").asText(),feedsData.get(i).get("created_time").asText(), feedsData.get(i).get("id").asText()));

		}
		if(null != feedsNode.findValue("next")){
			//System.out.println(feedsNode.findValue("next").asText());
			getFeeds(feedsNode.findValue("next").asText());
		}
	}
	
	
	
	//采集相册相关信息
	private  List<universityAlbum> getAlbums() {	
		JsonNode root = jsonUtil.jsonToObject(this.getJsonSourceCode());
		JsonNode albumsNode = root.findValue("albums");
		if(null != albumsNode){
			JsonNode albumsData = albumsNode.findValue("data");
			if(null != albumsData){
				for(int i = 0;i<albumsData.size();i++){
					albums.add(new universityAlbum(albumsData.get(i).get("id").asLong(),albumsData.get(i).get("name").asText(), albumsData.get(i).get("created_time").asText()));
				}
				if(null != albumsNode.findValue("next")){
					getAlbums(albumsNode.findValue("next").asText());
				}
			}
		}
		
		
		System.out.println(getValueByKey("name") + "获取albums成功");
		return albums;
	}
	 private static void getAlbums(String nextUrl){
		JsonNode albumsNode = jsonUtil.jsonToObject(new universityInfo(nextUrl).getJsonSourceCode());
		
		JsonNode albumsData = albumsNode.findValue("data");
		for(int i = 0;i<albumsData.size();i++){
			albums.add(new universityAlbum(albumsData.get(i).get("id").asLong(),albumsData.get(i).get("name").asText(), albumsData.get(i).get("created_time").asText()));
			//System.out.println(albumsData.get(i).get("id").asLong()+ " " +albumsData.get(i).get("name").asText()+" " + albumsData.get(i).get("created_time").asText());

		}
		if(null != albumsNode.findValue("next")){
			getAlbums(albumsNode.findValue("next").asText());
		}
	}
	
	 
	 
	 
	//采集位置相关信息
	private universityLocation getLocation(){
		universityLocation location = new universityLocation();
		JsonNode root = jsonUtil.jsonToObject(this.getJsonSourceCode());
		JsonNode locationNode = root.findValue("location");
		if(null != locationNode){
			location.setCity((null == locationNode.findValue("city"))?null:locationNode.get("city").asText());
			location.setCounty((null ==locationNode.findValue("country") )?null:locationNode.get("country").asText());
		
			location.setLatitude(((null == locationNode.findValue("latitude"))?0:locationNode.get("latitude").asDouble()));
			location.setLongitude(((null == locationNode.findValue("longitude"))?0:locationNode.get("longitude").asDouble()));
			location.setState((null==locationNode.findValue("state"))?null:locationNode.get("state").asText());
			location.setStreet((null==locationNode.findValue("street"))?null:locationNode.get("street").asText());
			location.setZip((null==locationNode.findValue("zip"))?null:locationNode.get("zip").asText());
			
		}
		System.out.println(getValueByKey("name") + "获取location成功");
		return location;
		
	}
	
	
	//获得邮箱列表
	private List<String> getEmails(){
		List<String> emailList = new ArrayList<String>();
		JsonNode root = jsonUtil.jsonToObject(this.getJsonSourceCode());
		JsonNode emails = root.findValue("emails");
		if(null != emails){
			for (int i = 0; i < emails.size(); i++) {
				if(!emails.get(i).asText().equals("<<not-applicable>>")){
					emailList.add(emails.get(i).asText());
				}
			}
		}
		
		System.out.println(getValueByKey("name") + "获取emails成功");
		return emailList;
	}
	
	
	
	//获取封面的信息
	private universityCoverModel getCover(){
		universityCoverModel coverModel = new universityCoverModel();
		JsonNode root = jsonUtil.jsonToObject(this.getJsonSourceCode());
		JsonNode cover = root.findValue("cover");
		if(null!=cover){
			coverModel.setCoverId(cover.get("cover_id").asLong());
			coverModel.setOffset_x(cover.get("offset_x").asInt());
			coverModel.setOffset_y(cover.get("offset_y").asInt());
			coverModel.setSource(cover.get("source").asText());
		}
		
		System.out.println(getValueByKey("name") + "获取coverModel成功");
		return coverModel;
	}
	
	 //获取分类列表
	 private  List<universityCategory> getCategoryList() {
		 	List<universityCategory> categoryList=new ArrayList<universityCategory>();
			JsonNode root = jsonUtil.jsonToObject(this.getJsonSourceCode());
			JsonNode list = root.findValue("category_list");
			if(null!=list){
				for (int i = 0; i < list.size(); i++) {
					categoryList.add(new universityCategory(Long.parseLong(list.get(i).get("id").asText()),list.get(i).get("name").asText()));
				}
			}
			
			System.out.println(getValueByKey("name") + "获取categoryList成功");
			return categoryList;
			
		}
	
	//通过json的key获得相应的value
	private String getValueByKey(String key){
		JsonNode root = jsonUtil.jsonToObject(this.getJsonSourceCode());
		JsonNode value = root.findValue(key);
		if(null != value){
			return value.asText();
		}
		return null;
	}


	public static void main(String[] args) {
		System.out.println("https://graph.facebook.com/v2.5/156946994356884?fields=id,name,about,can_post,category_list,cover,description,emails,founded,general_info,global_brand_page_name,is_always_open,is_community_page,is_published,is_verified,link,location,phone,talking_about_count,username,website,were_here_count,albums,checkins,feed,likes,milestones,photos,videos&access_token="+accessToken);
		universityInfo universityInfo = new universityInfo("https://graph.facebook.com/v2.5/156946994356884?fields=id,name,about,can_post,category_list,cover,description,emails,founded,general_info,global_brand_page_name,is_always_open,is_community_page,is_published,is_verified,link,location,phone,talking_about_count,username,website,were_here_count,albums,checkins,feed,likes,milestones,photos,videos&access_token="+accessToken);
		
		universityInfo.analyzer();
	}
}
