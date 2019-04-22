package com.sdev450_finalproject.persistance;

import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "UserName")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name="display_name")
	String displayName;
	
	String fname;
	String lname;
	
	long[] trackSavedPlaylist; //user saves track into his profile by trackIDs. Can do this by Name too

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

 

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public long[] getTrackSavedPlaylist() {
		return trackSavedPlaylist;
	}

	public void setTrackSavedPlaylist(long[] trackSavedPlaylist) {
		this.trackSavedPlaylist = trackSavedPlaylist;
	}



	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", displayName=" + displayName + ", fname=" + fname + ", lname=" + lname
				+ ", trackSavedPlaylist=" + Arrays.toString(trackSavedPlaylist) + "]";
	}
	
	
	


}
