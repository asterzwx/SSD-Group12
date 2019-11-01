package hello.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import hello.model.Admin;

public interface AdminRepo extends JpaRepository<Admin, String>{

	@Query("SELECT username, profile_picture FROM Admin a WHERE a.username = :username") 
	Object getAdminByUsername(@Param("username") String username);
	
	@Query("SELECT username as username, profile_picture as profile_picture"
			+ " FROM Admin u WHERE u.username = :username") 
	List<AdminView> getDetailsByUsername(@Param("username") String username);

	@Modifying
	@Query("UPDATE Admin a SET a.token = :token WHERE a.username = :username") 
    int updateAdminToken(@Param("username") String username, @Param("token") String token);

}
