package com.sdev450_finalproject.persistance;

 import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

 @Transactional
public interface UserRepository extends JpaRepository<User, Long> {
	 
	 User findById(long id);
	 
	 User findByLname(String lname);
	 
	 User findByDisplayName(String display_name);

}
