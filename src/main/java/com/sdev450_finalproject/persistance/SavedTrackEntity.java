package com.sdev450_finalproject.persistance;

import java.sql.Time;

import javax.persistence.Entity;
import javax.persistence.Id;


//I Added the saved abum entity and the saved track entity. Depending on our final
//mapping design. Maybe these entities and repos will be deleted. 

@Entity
public class SavedTrackEntity {

	@Id
	Long id;
	
	TrackEntity track;
	Time added_at;
	


	public TrackEntity getTrack() {
		return track;
	}

	public void setTrack(TrackEntity track) {
		this.track = track;
	}

	public Time getAdded_at() {
		return added_at;
	}

	public void setAdded_at(Time added_at) {
		this.added_at = added_at;
	}

}
