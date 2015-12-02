package com.MyApp.FaceBookModel;

public class universityModel {
	private int id;
	private String schoolName;
	private String schoolNameFaceBook;
	private String schoolId;
	
	public universityModel(String schoolNameFaceBook,String schoolId) {
		// TODO Auto-generated constructor stub
		
		schoolName = null;
		this.schoolNameFaceBook=schoolNameFaceBook;
		this.schoolId=schoolId;
	}
	
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}


	

	public String getSchoolNameFaceBook() {
		return schoolNameFaceBook;
	}

	public void setSchoolNameFaceBook(String schoolNameFaceBook) {
		this.schoolNameFaceBook = schoolNameFaceBook;
	}

	public String getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}
}
