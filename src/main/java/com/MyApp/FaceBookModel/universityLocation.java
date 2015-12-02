package com.MyApp.FaceBookModel;

public class universityLocation {
	private String city;
	private String county;
	private double latitude;
	private double longitude;
	private String state;
	private String street;
	private String zip;
	
//	public universityLocation(String city,String county,double latitude,
//			double longitude,String state,String street,String zip) {
//		this.city = city;
//		this.county=county;
//		this.latitude=latitude;
//		this.longitude = longitude;
//		this.state=state;
//		this.street=street;
//		this.zip=zip;
//	}
	
	
	
	
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCounty() {
		return county;
	}
	public void setCounty(String county) {
		this.county = county;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
}
