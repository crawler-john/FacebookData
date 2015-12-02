package com.MyApp.FaceBookData;

import com.db.manager.FaceBookDB;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
		FaceBookDB db = new FaceBookDB();
		//String accessToken = "CAACEdEose0cBAGCAuWE20jZA12D3wqpMXEGDjZAkT4YsZBITO17i3v9HGsouUIuuq1JOCJttkNg1ViEHcYibiGkCO5EqfkV2Xmeg2DjI5TxVZBDKdU9hSpwS8DVcQcyZBJwBhkZAEANwEBHvkwnRgy0RHveSlXt7hmxRzy00YQPPdupAX4B7Wm5OiaOkPSD0Cnqm2Gzo4gLGRwgdHEIRwt";
		String accessToken = args[0];
		while(null != db.randomGetSchoolID()){
			try {
				//通过url读取JSON数据
				universityInfo universityInfo = new universityInfo(
						"https://graph.facebook.com/v2.5/"
								+ db.randomGetSchoolID()
								+ "?fields=id,name,about,can_post,category_list,cover,description,emails,founded,general_info,global_brand_page_name,is_always_open,is_community_page,is_published,is_verified,link,location,phone,talking_about_count,username,website,were_here_count,albums,checkins,feed,likes,milestones,photos,videos&access_token="
								+ accessToken);
				//解析并且存储数据
				db.updateUniversityData(universityInfo.analyzer());
			} catch (Exception e) {
				continue;
		
			}
			
		}
		
    }
}
