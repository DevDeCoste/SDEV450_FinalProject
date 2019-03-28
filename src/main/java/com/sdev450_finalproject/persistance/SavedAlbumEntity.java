package com.sdev450_finalproject.persistance;

import java.sql.Time;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


//I Added the saved abum entity and the saved track entity. Depending on our final
//mapping design. Maybe these entities and repos will be deleted. 

@Entity
@Table(name = "Saved_Album")
public class SavedAlbumEntity {

	@Id
	Long id;
	
//	AlbumEntity album;
//	Time added_at;
//
//	public AlbumEntity getAlbum() {
//		return album;
//	}
//
//	public void setAlbum(AlbumEntity album) {
//		this.album = album;
//	}
//
//	public Time getAdded_at() {
//		return added_at;
//	}
//
//	public void setAdded_at(Time added_at) {
//		this.added_at = added_at;
//	}

}
