package com.MyApp.FaceBookData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.MyApp.FaceBookCralwer.CrawlBase;
import com.MyApp.FaceBookModel.universityModel;
import com.MyApp.FaceBookUtil.jsonUtil;
import com.db.manager.FaceBookDB;
import com.fasterxml.jackson.databind.JsonNode;

public class UniversionList extends CrawlBase {
	// 设置头信息头信息

	private static HashMap<String, String> params;
	static {
		params = new HashMap<String, String>();
		params.put("Host", "graph.facebook.com");
		params.put("User-Agent",
				"Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1)");
	}
	public UniversionList(String url) {
		readJsonByGet(url, params, "utf-8");

	}
	public static String accessToken = "CAATvgqd5bWoBAPF91aJVlVbTtSw0nz6rUci1fBjRWcjtBrSGsWkkGAqeXMYDq08s7f1FMt0wymFYNE8N1PbMRMxZAajOql5fumgELCZAviBvARNBvCIKmsdtT6CILReVXYAaThTIZBNSJHht08maq5lYDn8K1EZAVyclhL7IttemyDZCN52TUO4mH8Xj3mYcZD";
	private static List<universityModel> schoolList = new ArrayList<universityModel>();
	
	
	/**
	 * 获取学校列表	
	 * @param universionList
	 * @return
	 */
	public static List<universityModel> getSchoolList(UniversionList universionList) {
		
		JsonNode root = jsonUtil.jsonToObject(universionList
				.getJsonSourceCode());
		
		JsonNode data = root.findValue("data");
		for (int i = 0; i < data.size(); i++) {
			schoolList.add(new universityModel(
					data.get(i).get("name").asText(), data.get(i).get("id")
							.asText()));
		}
		if (root.findValue("next") != null) {
			getSchoolList(new UniversionList(root.findValue("next").asText()));
		}else{
			return schoolList;
		}
		return schoolList;
		
	}

	public static void main(String[] args) {
		List<universityModel> schoollist = getSchoolList(new UniversionList("https://graph.facebook.com/v2.5/me?fields=likes{id,name}&access_token="+accessToken.trim()));
		FaceBookDB db = new FaceBookDB();
		for(universityModel u : schoollist){
			System.out.println(u.getSchoolId() + "     " +u.getSchoolNameFaceBook());
			db.updateSchoolId(u.getSchoolNameFaceBook(), u.getSchoolId());
			
		}
	}
}
