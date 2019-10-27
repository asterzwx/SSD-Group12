package hello.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hello.model.Admin;
import hello.repo.AdminRepo;

@Service
@Transactional
public class AdminService {
	
	@Autowired
	AdminRepo adminRepo;
	
	public List<Admin> getAll() {
		// TODO Auto-generated method stub				
		return adminRepo.findAll();
	}
	
	public Admin saveUser(Admin adminAccount) {
		return adminRepo.save(adminAccount);
	}
	
	public Optional<Admin> findById(String username) {
		// TODO Auto-generated method stub
        return adminRepo.findById(username);
	}
	
	public void deleteById(String username) {
		adminRepo.deleteById(username);
    }
	
	

}
