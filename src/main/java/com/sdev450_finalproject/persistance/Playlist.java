package com.sdev450_finalproject.persistance;

import javax.persistence.Entity;

@Entity
public class Playlist {
	
	Long user_id;
	String display_name;
	String first_name;
	String last_name;
	String external_url;
	String href;
	String type;
	String uri;
	
	String[] images;
	Follower follower; //what is this object? return number of followers? return all followers? 
	//Should I create a class for this? 
	
	
	public Long getUser_id() {
		return user_id;
	}
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
	public String getDisplay_name() {
		return display_name;
	}
	public void setDisplay_name(String display_name) {
		this.display_name = display_name;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getExternal_url() {
		return external_url;
	}
	public void setExternal_url(String external_url) {
		this.external_url = external_url;
	}
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public String[] getImages() {
		return images;
	}
	public void setImages(String[] images) {
		this.images = images;
	}
	public Follower getFollower() {
		return follower;
	}
	public void setFollower(Follower follower) {
		this.follower = follower;
	}
	
	
	
	
	

}
