package com.MyApp.FaceBookModel;

public class universityFeed {
	private String message;
	private String story;
	private String createTime;
	private String id;
	public universityFeed(String message,String story,String createTime,String id) {
		// TODO Auto-generated constructor stub
		this.message = message;
		this.story=story;
		this.createTime=createTime;
		this.id=id;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getStory() {
		return story;
	}
	public void setStory(String story) {
		this.story = story;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
}
