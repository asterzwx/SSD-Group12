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
	UserAccountRepo userRepository;

	public List<UserAccount> getAll() {
		// TODO Auto-generated method stub				
		return userRepository.findAll();
	}
	
	public UserAccount saveUser(UserAccount userAccount) {
		return userRepository.save(userAccount);
	}
	
	public Optional<UserAccount> findById(String username) {
		// TODO Auto-generated method stub
        return userRepository.findById(username);
	}
	
	public void deleteById(String username) {
        userRepository.deleteById(username);
    }

}
