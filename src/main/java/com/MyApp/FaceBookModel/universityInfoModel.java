package com.MyApp.FaceBookModel;

import java.util.ArrayList;
import java.util.List;

public class universityInfoModel {
	private long id; // 学校的ID
	private String name;// 学校的名字
	private String about;// 学校相关的网站
	private boolean canPost;
	private List<universityCategory> categoryList;// 分类列表
	private universityCoverModel cover;// 封面的信息
	private String description;// 学校相关的描述
	private List<String> emails;// 学校的电子邮箱
	private String founded;// 学校的创建时间
	private String generalInfo;// 学校的基本信息
	private String globalBrandPageName;// 首页的名字
	private boolean isAlwaysOpen;// 是否一致开发
	private boolean isCommunityPage;// 是社区页面啊
	private boolean isPublished;
	private boolean isVerified;// 是否官方认证
	private String link;// 网页链接
	private universityLocation location;// 地理位置
	private String phone;// 电话号码
	private int talkingAboutCount;// 谈论的人数
	private String username;
	private String website;// 官方网站
	private int wereHereCount;// 在这边的人数
	private List<universityAlbum> albums;// 相册
	private int checkins;// 点击的人数
	private List<universityFeed> feeds;
	private int likes;// 赞的人数
	private List<universityMilestone> milestones;// 里程碑列表
	private List<universityPhoto> photos;// 相片
	private List<universityVideo> videos;// 短视频

	public universityInfoModel() {
		categoryList = new ArrayList<universityCategory>();
		emails = new ArrayList<String>();
		albums = new ArrayList<universityAlbum>();
		feeds = new ArrayList<universityFeed>();
		milestones = new ArrayList<universityMilestone>();
		photos = new ArrayList<universityPhoto>();
		videos = new ArrayList<universityVideo>();
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

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public boolean isCanPost() {
		return canPost;
	}

	public void setCanPost(boolean canPost) {
		this.canPost = canPost;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFounded() {
		return founded;
	}

	public void setFounded(String founded) {
		this.founded = founded;
	}

	public String getGeneralInfo() {
		return generalInfo;
	}

	public void setGeneralInfo(String generalInfo) {
		this.generalInfo = generalInfo;
	}

	public String getGlobalBrandPageName() {
		return globalBrandPageName;
	}

	public void setGlobalBrandPageName(String globalBrandPageName) {
		this.globalBrandPageName = globalBrandPageName;
	}

	public boolean isAlwaysOpen() {
		return isAlwaysOpen;
	}

	public void setAlwaysOpen(boolean isAlwaysOpen) {
		this.isAlwaysOpen = isAlwaysOpen;
	}

	public boolean isCommunityPage() {
		return isCommunityPage;
	}

	public void setCommunityPage(boolean isCommunityPage) {
		this.isCommunityPage = isCommunityPage;
	}

	public boolean isPublished() {
		return isPublished;
	}

	public void setPublished(boolean isPublished) {
		this.isPublished = isPublished;
	}

	public boolean isVerified() {
		return isVerified;
	}

	public void setVerified(boolean isVerified) {
		this.isVerified = isVerified;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getTalkingAboutCount() {
		return talkingAboutCount;
	}

	public void setTalkingAboutCount(int talkingAboutCount) {
		this.talkingAboutCount = talkingAboutCount;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public int getWereHereCount() {
		return wereHereCount;
	}

	public void setWereHereCount(int wereHereCount) {
		this.wereHereCount = wereHereCount;
	}

	public int getCheckins() {
		return checkins;
	}

	public void setCheckins(int checkins) {
		this.checkins = checkins;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	public universityCoverModel getCover() {
		return cover;
	}

	public void setCover(universityCoverModel cover) {
		this.cover = cover;
	}

	public List<universityCategory> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(List<universityCategory> categoryList) {
		this.categoryList = categoryList;
	}

	public void addToCategoryList(universityCategory categoryList) {
		this.categoryList.add(categoryList);
	}

	public List<String> getEmails() {
		return emails;
	}

	public void setEmails(List<String> emails) {
		this.emails = emails;
	}

	public void addToEmails(String email) {
		this.emails.add(email);
	}

	public universityLocation getLocation() {
		return location;
	}

	public void setLocation(universityLocation location) {
		this.location = location;
	}

	public List<universityAlbum> getAlbums() {
		return albums;
	}

	public void setAlbums(List<universityAlbum> albums) {
		this.albums.addAll(albums);
	}


	public List<universityFeed> getFeeds() {
		return feeds;
	}

	public void setFeeds(List<universityFeed> feeds) {
		this.feeds.addAll(feeds);
	}

	

	public List<universityMilestone> getMilestones() {
		return milestones;
	}

	public void setMilestones(List<universityMilestone> milestones) {
		this.milestones.addAll(milestones);
	}



	public List<universityPhoto> getPhotos() {
		return photos;
	}

	public void setPhotos(List<universityPhoto> photos) {
		this.photos.addAll(photos);
	}



	public List<universityVideo> getVideos() {
		return videos;
	}

	public void setVideos(List<universityVideo> videos) {
		this.videos.addAll(videos);
	}


}
