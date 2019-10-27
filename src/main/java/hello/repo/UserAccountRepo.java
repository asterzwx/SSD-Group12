package hello.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import hello.model.UserAccount;

//String because your PK for this table is string data type
public interface UserAccountRepo extends JpaRepository<UserAccount, String>{
	@Modifying
	@Query("UPDATE UserAccount u SET u.status = :status WHERE u.username = :username") 
    int updateUserLoginStatus(@Param("username") String username, @Param("status") String status);

}
