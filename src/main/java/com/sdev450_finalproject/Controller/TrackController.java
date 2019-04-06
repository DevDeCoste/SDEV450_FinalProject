package com.sdev450_finalproject.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sdev450_finalproject.persistance.TrackEntity;

@RestController
@RequestMapping("")
public class TrackController {
	
	//pseudocode:
	//get searchtext from url
	//iterate through csv to see if there's match
	//match = return track object
	//if no match, generate a random line and fetch that track object
	//return a track object
	
    @GetMapping("/findTrack/{trackName}")
    public TrackEntity findTrack() {
     TrackEntity track = null;
    	return track;
    }
	

}
