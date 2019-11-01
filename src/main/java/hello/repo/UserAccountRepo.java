package hello.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.google.gson.Gson;

import hello.model.UserAccount;

//String because your PK for this table is string data type
public interface UserAccountRepo extends JpaRepository<UserAccount, String>{
	
	
	@Query("SELECT username, profile_picture, mobile_number, email, status"
			+ " FROM UserAccount u WHERE u.username = :username") 
	Object getUserByUsername(@Param("username") String username);
	
	
	@Modifying
	@Query("UPDATE UserAccount u SET u.status = :status WHERE u.username = :username") 
    int updateUserLoginStatus(@Param("username") String username, @Param("status") String status);
	
	@Modifying
	@Query("UPDATE UserAccount u SET u.status = :status, u.token = '' WHERE u.username = :username") 
    int updateUserLogoutStatus(@Param("username") String username, @Param("status") String status);
	
	@Modifying
	@Query("UPDATE UserAccount u SET u.status = :status WHERE u.username = :username") 
    int banUser(@Param("username") String username, @Param("status") String status);
	
	
	
	@Query("SELECT username as username, profile_picture as profile_picture, mobile_number as mobile_number, email as email, status as status"
			+ " FROM UserAccount u WHERE u.username = :username") 
	List<UserAccountView> getDetailsByUsername(@Param("username") String username);
	
	
	@Modifying
	@Query("UPDATE UserAccount u SET u.token = :token WHERE u.username = :username") 
    int updateUserToken(@Param("username") String username, @Param("token") String token);

}
