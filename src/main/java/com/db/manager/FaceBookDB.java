package com.db.manager;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.MyApp.FaceBookData.universityInfo;
import com.MyApp.FaceBookModel.universityAlbum;
import com.MyApp.FaceBookModel.universityCategory;
import com.MyApp.FaceBookModel.universityFeed;
import com.MyApp.FaceBookModel.universityInfoModel;
import com.MyApp.FaceBookModel.universityMilestone;
import com.MyApp.FaceBookModel.universityPhoto;
import com.MyApp.FaceBookModel.universityVideo;
import com.MyApp.FaceBookUtil.EmojiFilterUtils;

public class FaceBookDB {
	private static final String POOLNAME = "proxool.FaceBook";

	// 查询未获得schoolname_facebook的学校
	public List<String> getEmptySchoolNameList() {
		DBServer dbServer = new DBServer(POOLNAME);
		List<String> list = new ArrayList<String>();
		try {
			String sql = "SELECT * from t_university WHERE id>691&&id<694";
			ResultSet rs = dbServer.select(sql);
			while (rs.next()) {
				list.add(rs.getString("schoolName"));
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbServer.close();

		}
		return list;

	}

	// 查询feeds数据库中是否已经有这个ID
	private boolean selectSchoolID(String Feedid) {
		DBServer dbServer = new DBServer(POOLNAME);
		try {
			String sql = "select * from t_university_feeds where feedid = '"
					+ Feedid + "'";
			ResultSet rs = dbServer.select(sql);
			while (rs.next()) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbServer.close();
		}
		return false;
	}

	// 查询t_university中是否有schoolID
	private boolean selectSchoolID(long schoolid) {
		DBServer dbServer = new DBServer(POOLNAME);
		try {
			String sql = "select * from t_university where schoolid = '"
					+ schoolid + "'";
			ResultSet rs = dbServer.select(sql);
			while (rs.next()) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbServer.close();
		}
		return false;
	}

	// 随机获取一个schoolId
	public String randomGetSchoolID() {
		DBServer dbServer = new DBServer(POOLNAME);
		try {
			String sql = "select * from t_university where state = '1' order by rand() limit 1";
			ResultSet rs = dbServer.select(sql);
			while (rs.next()) {
				return rs.getString("schoolId");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbServer.close();
		}
		return null;
	}

	public void updateUniversityData(universityInfoModel info) {
		long before = System.currentTimeMillis();
		updateUniversityBasic(info);
		updateUniversityEmails(info);
		updateUniversityCategory(info);
		updateUniversityAlbums(info);
		updateUniversityFeeds(info);
		updateUniversityMilestones(info);
		updateUniversityVideos(info);
		updateUniversityPhotos(info);
		updateInfoState(info.getId(), "0");
		long after = System.currentTimeMillis();
		System.out.println("存储" + info.getName() + "所花费的时间是" + (after - before)
				+ "ms");
	}

	// 插入t_university_photos表中的数据
	private void updateUniversityEmails(universityInfoModel info) {
		long before = System.currentTimeMillis();

		List<String> emails = info.getEmails();
		for (String email : emails) {
			updateUniversityEmails(email, info.getId());
		}

		long after = System.currentTimeMillis();
		System.out.println(info.getName() + "插入emails数据的时间是："
				+ (after - before) + "ms");
	}

	private void updateUniversityEmails(String email, long schoolId) {
		DBServer dbServer = new DBServer(POOLNAME);
		try {
			HashMap<Integer, Object> params = new HashMap<Integer, Object>();
			int i = 1;
			params.put(i++, schoolId);
			params.put(i++, email);
			String columns = "schoolId,emails";
			dbServer.insert("t_university_emails", columns, params);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbServer.close();
		}
	}

	// 插入t_university_photos表中的数据
	private void updateUniversityPhotos(universityInfoModel info) {
		long before = System.currentTimeMillis();
		List<universityPhoto> photos = info.getPhotos();
		for (universityPhoto photo : photos) {
			updateUniversityPhotos(photo, info.getId());
		}
		long after = System.currentTimeMillis();
		System.out.println(info.getName() + "插入photos数据的时间是："
				+ (after - before) + "ms");
	}

	private void updateUniversityPhotos(universityPhoto photo, long schoolId) {
		DBServer dbServer = new DBServer(POOLNAME);
		try {
			HashMap<Integer, Object> params = new HashMap<Integer, Object>();
			int i = 1;
			params.put(i++, schoolId);
			params.put(i++, photo.getId());
			params.put(i++, photo.getCreateTime());
			params.put(i++, photo.getName());

			String columns = "schoolId,photoId,created_time,name";
			dbServer.insert("t_university_photos", columns, params);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbServer.close();
		}
	}

	// 插入t_university_videos表中的数据
	private void updateUniversityVideos(universityInfoModel info) {
		long before = System.currentTimeMillis();
		List<universityVideo> videos = info.getVideos();
		for (universityVideo video : videos) {
			updateUniversityVideos(video, info.getId());
		}
		long after = System.currentTimeMillis();
		System.out.println(info.getName() + "插入videos数据的时间是："
				+ (after - before) + "ms");
	}

	private void updateUniversityVideos(universityVideo video, long schoolId) {
		DBServer dbServer = new DBServer(POOLNAME);
		try {
			HashMap<Integer, Object> params = new HashMap<Integer, Object>();
			int i = 1;
			params.put(i++, schoolId);
			params.put(i++, video.getId());
			params.put(i++, video.getDescription());
			params.put(i++, video.getUpdateTime());

			String columns = "schoolId,videoId,description,updated_time";
			dbServer.insert("t_university_videos", columns, params);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbServer.close();
		}
	}

	// 插入t_university_milestones表中的数据
	private void updateUniversityMilestones(universityInfoModel info) {
		long before = System.currentTimeMillis();
		List<universityMilestone> milestones = info.getMilestones();
		for (universityMilestone milestone : milestones) {
			updateUniversityMilestones(milestone, info.getId());
		}
		long after = System.currentTimeMillis();
		System.out.println(info.getName() + "插入milestone数据的时间是："
				+ (after - before) + "ms");
	}

	private void updateUniversityMilestones(universityMilestone milestone,
			long schoolId) {
		DBServer dbServer = new DBServer(POOLNAME);
		try {
			HashMap<Integer, Object> params = new HashMap<Integer, Object>();
			int i = 1;
			params.put(i++, schoolId);
			params.put(i++, milestone.getId());
			params.put(i++, milestone.getCreateTime());
			params.put(i++, milestone.getDescription());
			params.put(i++, milestone.getEndTime());
			params.put(i++, milestone.isHidden());
			params.put(i++, milestone.getFromId());
			params.put(i++, milestone.getFromName());
			params.put(i++, milestone.getStartTime());
			params.put(i++, milestone.getTitle());
			params.put(i++, milestone.getUpdateTime());

			String columns = "schoolId,milestoneId,created_time,description,end_time,is_hidden,from_id,from_name,start_time,title,updated_time";
			dbServer.insert("t_university_milestones", columns, params);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbServer.close();
		}
	}

	// 插入t_university_feed表中的数据
	private void updateUniversityFeeds(universityInfoModel info) {
		long before = System.currentTimeMillis();
		List<universityFeed> feeds = info.getFeeds();
		for (universityFeed feed : feeds) {
			updateUniversityFeeds(feed, info.getId());
		}
		long after = System.currentTimeMillis();
		System.out.println(info.getName() + "插入feeds数据的时间是：" + (after - before)
				+ "ms");
	}

	private void updateUniversityFeeds(universityFeed feed, long schoolId) {
		DBServer dbServer = new DBServer(POOLNAME);
		try {
			HashMap<Integer, Object> params = new HashMap<Integer, Object>();
			params.put(1, schoolId);
			params.put(2, feed.getId());
			params.put(3, feed.getCreateTime());
			params.put(4, feed.getStory());
			params.put(5, EmojiFilterUtils.filterEmoji(feed.getMessage()));
			String columns = "schoolId,feedId,created_time,story,message";
			dbServer.insert("t_university_feeds", columns, params);
		} catch (Exception e) {
			System.out.println(feed.getId() + "已经存在");

		} finally {
			dbServer.close();
		}
	}

	// 插入t_university_albums表中的数据
	private void updateUniversityAlbums(universityInfoModel info) {
		long before = System.currentTimeMillis();

		List<universityAlbum> albums = info.getAlbums();
		for (universityAlbum album : albums) {
			updateUniversityAlbums(album, info.getId());
		}
		long after = System.currentTimeMillis();
		System.out.println(info.getName() + "插入albums数据的时间是："
				+ (after - before) + "ms");
	}

	private void updateUniversityAlbums(universityAlbum album, long schoolId) {
		DBServer dbServer = new DBServer(POOLNAME);
		try {
			HashMap<Integer, Object> params = new HashMap<Integer, Object>();
			params.put(1, schoolId);
			params.put(2, album.getId());
			params.put(3, album.getCreatedTime());
			params.put(4, album.getName());
			String columns = "schoolId,albumId,created_time,name";
			dbServer.insert("t_university_albums", columns, params);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbServer.close();
		}
	}

	// 插入t_university_category表中的数据
	private void updateUniversityCategory(universityInfoModel info) {
		long before = System.currentTimeMillis();

		List<universityCategory> categoryList = info.getCategoryList();
		for (universityCategory category : categoryList) {
			updateUniversityCategory(category, info.getId());
		}

		long after = System.currentTimeMillis();
		System.out.println(info.getName() + "插入category数据的时间是："
				+ (after - before) + "ms");
	}

	private void updateUniversityCategory(universityCategory category,
			long schoolId) {
		DBServer dbServer = new DBServer(POOLNAME);
		try {
			HashMap<Integer, Object> params = new HashMap<Integer, Object>();
			params.put(1, schoolId);
			params.put(2, category.getId());
			params.put(3, category.getName());
			String columns = "schoolId,categoryId,name";
			dbServer.insert("t_university_category", columns, params);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbServer.close();
		}
	}

	// 更新t_university表中的数据
	private void updateUniversityBasic(universityInfoModel info) {
		DBServer dbServer = new DBServer(POOLNAME);
		long before = System.currentTimeMillis();

		try {
			HashMap<Integer, Object> params = new HashMap<Integer, Object>();
			params.put(1, info.getAbout());
			params.put(2, info.isCanPost());
			params.put(3, info.getDescription());
			params.put(4,
					(("''" == info.getFounded()) ? null : info.getFounded()));
			params.put(5, info.getGeneralInfo());
			params.put(6, info.getGlobalBrandPageName());
			params.put(7, info.isAlwaysOpen());
			params.put(8, info.isCommunityPage());
			params.put(9, info.isPublished());
			params.put(10, info.isVerified());
			params.put(11, info.getLink());
			params.put(12, info.getPhone());
			params.put(13, info.getTalkingAboutCount());
			params.put(14, info.getUsername());
			params.put(15, info.getWebsite());
			params.put(16, info.getWereHereCount());
			params.put(17, info.getCheckins());
			params.put(18, info.getLikes());
			params.put(19, info.getLocation().getCity());
			params.put(20, info.getLocation().getCounty());
			params.put(21, (0 == info.getLocation().getLatitude()) ? null
					: info.getLocation().getLatitude());
			params.put(22, (0 == info.getLocation().getLongitude()) ? null
					: info.getLocation().getLongitude());
			params.put(23, info.getLocation().getState());
			params.put(24, info.getLocation().getStreet());
			params.put(25, info.getLocation().getZip());
			String columns = "about,can_post,description,founded,general_info,global_brand_page_name,is_always_open,is_community_page,is_publiced,is_verified,link,phone,talking_about_count,username,website,were_here_count,checkins,likes,location_city,location_country,location_latitude,location_longitude,location_state,location_street,location_zip";
			String condition = "where schoolId = '" + info.getId() + "'";
			dbServer.update("t_university", columns, condition, params);
		} catch (Exception e) {
			System.out.println(info.getFounded());
			e.printStackTrace();
		} finally {

			long after = System.currentTimeMillis();
			System.out.println(info.getName() + "插入basic数据的时间是："
					+ (after - before) + "ms");
			dbServer.close();
		}
	}

	// 修改状态的值
	private void updateInfoState(long schoolId, String state) {
		DBServer dbServer = new DBServer(POOLNAME);
		try {
			String sql = "update t_university set state='" + state
					+ "' where schoolId = '" + schoolId + "'";
			dbServer.update(sql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbServer.close();
		}
	}

	// 更新学校的ID
	public void updateSchoolId(String schoolName_Facebook, String schoolId) {
		if (schoolName_Facebook == null || schoolId == null) {
			return;
		}

		if (false == selectSchoolID(Long.parseLong(schoolId))) {
			DBServer dbServer = new DBServer(POOLNAME);
			try {
				HashMap<Integer, Object> params = new HashMap<Integer, Object>();
				params.put(1, schoolId);
				params.put(2, "1");

				String columns = "schoolId,state";
				String condition = "where schoolName_FaceBook = '"
						+ schoolName_Facebook.replaceAll("'", "\\\\'") + "'";
				dbServer.update("t_university", columns, condition, params);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				dbServer.close();
			}
		}
	}

	public void updateSchoolId(String schoolName, String schoolName_Facebook,
			String schoolId) {
		if (schoolName_Facebook == null || schoolId == null
				|| schoolName == null) {
			return;
		}
		if (false == selectSchoolID(Long.parseLong(schoolId))){
			DBServer dbServer = new DBServer(POOLNAME);
			try {
				HashMap<Integer, Object> params = new HashMap<Integer, Object>();
				params.put(1, schoolName_Facebook);
				params.put(2, Long.parseLong(schoolId));
				params.put(3, "1");

				String columns = "schoolName_FaceBook,schoolId,state";
				String condition = "where schoolName = '"
						+ schoolName.replaceAll("'", "\\\\'") + "'";
				dbServer.update("t_university", columns, condition, params);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				dbServer.close();
			}
		}
		

	}

	// 采集学校的基本数据ID 学校名字 学校在schoolName_FaceBook上的名字
	public void insertBasicInfo(int id, String schoolName,
			String schoolName_FaceBook) {
		DBServer dbServer = new DBServer(POOLNAME);
		try {
			HashMap<Integer, Object> params = new HashMap<Integer, Object>();
			int i = 1;
			params.put(i++, id);
			params.put(i++, schoolName);

			params.put(i++, schoolName_FaceBook);
			dbServer.insert("t_university",
					"id,schoolName,schoolName_FaceBook", params);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbServer.close();
		}
	}

	public void updateBasicData(String schoolName, String schoolName_FaceBook) {
		DBServer dbServer = new DBServer(POOLNAME);
		try {
			HashMap<Integer, Object> params = new HashMap<Integer, Object>();
			int i = 1;
			params.put(i++, schoolName);

			params.put(i++, schoolName_FaceBook);
			String condition = "WHERE schoolName_FaceBook=''&&schoolName='"
					+ schoolName + "'";
			dbServer.update("t_university", "schoolName,schoolName_FaceBook",
					condition, params);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbServer.close();
		}
	}

	public void method() {
		DBServer dbServer = new DBServer(POOLNAME);
		try {

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbServer.close();
		}
	}

	public static void main(String[] args) {
		FaceBookDB db = new FaceBookDB();
		System.out.println(db.randomGetSchoolID());
		System.out.println((null == db.randomGetSchoolID()));
		String accessToken = "CAATvgqd5bWoBAAaZC1SBopDBg1YZB88DNO89GXv1bAguGGVA6aZCAZCi6ZAi2VZBE7UpBIqu4wJWZAMu9dhiznHKT1MMkE0U9ob4veZCrq45gjD6hFSk8G7Wxu139tkpanZCHcOgc6Wt2VBESdUunyXFUNDhrPLLQRXoZASHzwrs5Fq5kZCgbUojbCEElCToaEnBZCwZD";
		universityInfo universityInfo = new universityInfo(
				"https://graph.facebook.com/v2.5/"
						+ "19479053136"
						+ "?fields=id,name,about,can_post,category_list,cover,description,emails,founded,general_info,global_brand_page_name,is_always_open,is_community_page,is_published,is_verified,link,location,phone,talking_about_count,username,website,were_here_count,albums,checkins,feed,likes,milestones,photos,videos&access_token="
						+ accessToken);
		db.updateUniversityData(universityInfo.analyzer());
		System.out.println(111);

	}

}
