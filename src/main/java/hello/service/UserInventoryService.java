package hello.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hello.model.UserInventory;
import hello.repo.UserAccountRepo;
import hello.repo.UserInventoryRepo;

@Service
@Transactional
public class UserInventoryService {
	
	@Autowired
	UserInventoryRepo userInventoryRepo;

	public List<UserInventory> getAll() {
		// TODO Auto-generated method stub				
		return userInventoryRepo.findAll();
	}
	
	public UserInventory saveUserInventory(UserInventory userAccount) {
		return userInventoryRepo.save(userAccount);
	}
	
	public Optional<UserInventory> findById(String username) {
		// TODO Auto-generated method stub
        return userInventoryRepo.findById(username);
	}
	
	public void deleteById(String username) {
        userInventoryRepo.deleteById(username);
    }

}
