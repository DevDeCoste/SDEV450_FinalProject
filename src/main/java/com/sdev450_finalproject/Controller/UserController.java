package com.sdev450_finalproject.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sdev450_finalproject.persistance.User;
import com.sdev450_finalproject.persistance.UserRepository;

@RestController
@RequestMapping("")
public class UserController {
	
	@Autowired
	UserRepository userRepository;

	@PostMapping("/saveUser/{displayName}/{fName}/{lName}/{saveTrack}")
	public boolean saveUser(@PathVariable("displayName") String displayName,
			@PathVariable("fName") String fName, 
			@PathVariable("lName") String lName,
			@PathVariable ("saveTrack") long trackID) {
		
		
		User user = new User();
		
		user.setDisplay_name(displayName);
		user.setFname(fName);
		user.setLname(lName);
		user.setTrackSavedPlaylist(25);
		//user.setTrackSavedPlaylist(null);
		
		userRepository.save(user);
		return true;
	}
	
	@PostMapping("/findUserByDisplayName/{displayName}")
	public boolean findUser(@PathVariable("displayName") String displayName) {
		
		System.out.println(userRepository.findByLname(displayName).getLname());
		return true;
	}
	
	
}
