package hello.repo;

import java.util.List;

import hello.model.UserAccount;

public interface UserAccountView {
	
	String getUsername();
	
	String getProfile_picture();
	
	String getMobile_number();
	
	String getEmail();
	
	String getStatus();
	
	String getReset_password();
	
	boolean getRole();
	
	String getDatetime_locked();

}
