package hello.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import hello.model.UserAccount;


public interface UserAccountRepo extends JpaRepository<UserAccount, String>{
	

}
