package com.MyApp.FaceBookModel;

public class universityMilestone {
	private long id;
	private String createTime;
	private String description;
	private String endTime;
	private boolean isHidden;
	private String fromName;
	private long fromId;
	private String startTime;
	private String title;
	private String updateTime;

//	public universityMilestone(long id, String createTime, String description,
//			String endTime, boolean isHidden, String fromName, long fromId,
//			String startTime, String title, String updateTime) {
//		// TODO Auto-generated constructor stub
//		this.id=id;
//		this.createTime=createTime;
//		this.description=description;
//		this.endTime=endTime;
//		this.isHidden=isHidden;
//		this.fromName=fromName;
//		this.fromId=fromId;
//		this.startTime=startTime;
//		this.title=title;
//		this.updateTime=updateTime;
//	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public boolean isHidden() {
		return isHidden;
	}

	public void setHidden(boolean isHidden) {
		this.isHidden = isHidden;
	}

	public String getFromName() {
		return fromName;
	}

	public void setFromName(String fromName) {
		this.fromName = fromName;
	}

	public long getFromId() {
		return fromId;
	}

	public void setFromId(long fromId) {
		this.fromId = fromId;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	
}
