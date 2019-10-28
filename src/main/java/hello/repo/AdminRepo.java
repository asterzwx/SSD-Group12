package hello.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import hello.model.Admin;

public interface AdminRepo extends JpaRepository<Admin, String>{

	@Query("SELECT username, profile_picture FROM Admin a WHERE a.username = :username") 
	Object getAdminByUsername(@Param("username") String username);
}
