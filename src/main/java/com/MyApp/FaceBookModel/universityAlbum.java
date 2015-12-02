package com.MyApp.FaceBookModel;

public class universityAlbum {
	private long id;
	private String name ;
	private String createdTime;
	public universityAlbum(long id,String name,String createdTime) {
		// TODO Auto-generated constructor stub
		this.id=id;
		this.name=name;
		this.createdTime=createdTime;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}
	
}
