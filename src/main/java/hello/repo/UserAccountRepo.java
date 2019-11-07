package hello.repo;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

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
	
	@Modifying
	@Query("UPDATE UserAccount u SET u.reset_password = :reset_password WHERE u.username = :username") 
    int updateResetPassword(@Param("username") String username, @Param("reset_password") String reset_password);
	
	@Query("SELECT u.email as email FROM UserAccount u WHERE u.username = :username") 
	String getEmailByUsername(@Param("username") String username);
	
	@Modifying
    @Query(value = "insert into user_account (username,password_hash,salt,mobile_number,email,status,role) VALUES "
    		+ "(:username,:password_hash,:salt,:mobile_number,:email,:status,:role)", nativeQuery = true)
    @Transactional
    void createNormalUser(@Param("username") String username,@Param("password_hash") String password_hash,
    		@Param("salt") String salt,
    		@Param("mobile_number") String mobile_number, @Param("email") String email,
    		@Param("status") String status, @Param("role") boolean role);
	
	@Modifying
	@Query("UPDATE UserAccount u SET u.password_hash = :password_hash, u.salt = :salt, u.reset_password = :reset_password "
			+ " WHERE u.username = :username") 
    int updateNewPassword(@Param("username") String username, @Param("password_hash") String password_hash,
    		@Param("salt") String salt, @Param("reset_password") String reset_password );
	
	@Query("SELECT u.reset_password as reset_password FROM UserAccount u WHERE u.username = :username") 
	String checkResetPasswordNull(@Param("username") String username);
	
	@Modifying
	@Query("UPDATE UserAccount u SET u.password_hash = '', u.salt = '' WHERE u.username = :username") 
    int removePasswordHashAndSalt(@Param("username") String username);
	
}
