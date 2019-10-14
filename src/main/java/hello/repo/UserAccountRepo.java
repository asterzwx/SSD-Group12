package hello.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import hello.model.UserAccount;

//String because your PK for this table is string data type
public interface UserAccountRepo extends JpaRepository<UserAccount, String>{
	

}
