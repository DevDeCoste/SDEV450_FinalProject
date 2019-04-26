package com.sdev450_finalproject.persistance.Track;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Track")
public class TrackEntity {

	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	private String id;


	String trackTitle;
	String genreType;
	String trackLength;
	String yearPublished;
	


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}




	public String getTrackTitle() {
		return trackTitle;
	}

	public void setTrackTitle(String trackTitle) {
		this.trackTitle = trackTitle;
	}

	public String getGenreType() {
		return genreType;
	}

	public void setGenreType(String genreType) {
		this.genreType = genreType;
	}

	public String getTrackLength() {
		return trackLength;
	}

	public void setTrackLength(String trackLength) {
		this.trackLength = trackLength;
	}

	public String getYearPublished() {
		return yearPublished;
	}

	public void setYearPublished(String yearPublished) {
		this.yearPublished = yearPublished;
	}



	@Override
	public String toString() {
		return "TrackEntity [id= " + id  +  ", trackTitle="
				+ trackTitle + ", genreType=" + genreType + ", trackLength=" + trackLength + ", yearPublished="
				+ yearPublished + "]";
	}

}