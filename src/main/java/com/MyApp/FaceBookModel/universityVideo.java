package com.MyApp.FaceBookModel;

public class universityVideo {
	private String description;
	private String updateTime;
	private long id;
	public universityVideo(String description,String updateTime,long id) {
		this.description=description;
		this.updateTime=updateTime;
		this.id=id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
}
