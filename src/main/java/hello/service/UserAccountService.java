package hello.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hello.model.UserAccount;
import hello.repo.UserAccountRepo;

@Service
@Transactional
public class UserAccountService {

	@Autowired
	UserAccountRepo userRepo;

	public List<UserAccount> getAll() {
		// TODO Auto-generated method stub				
		return userRepo.findAll();
	}
	
	public UserAccount saveUser(UserAccount userAccount) {
		return userRepo.save(userAccount);
	}
	
	public Optional<UserAccount> findById(String username) {
		// TODO Auto-generated method stub
        return userRepo.findById(username);
	}
	
	public void deleteById(String username) {
        userRepo.deleteById(username);
    }

}
