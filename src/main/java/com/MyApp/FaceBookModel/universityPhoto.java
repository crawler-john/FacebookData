package com.MyApp.FaceBookModel;

public class universityPhoto {
	private String createTime;
	private String name;
	private long id;
	public universityPhoto(String createTime,String name,long id) {
		// TODO Auto-generated constructor stub
		this.createTime=createTime;
		this.name=name;
		this.id=id;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
}
