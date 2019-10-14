package hello.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hello.model.UserPrediction;
import hello.repo.UserPredictionRepo;

@Service
@Transactional
public class UserPredictionService {
	
	@Autowired
	UserPredictionRepo userPredictionRepo;

	public List<UserPrediction> getAll() {
		// TODO Auto-generated method stub				
		return userPredictionRepo.findAll();
	}
	
	public UserPrediction saveUserPrediction(UserPrediction userPrediction) {
		return userPredictionRepo.save(userPrediction);
	}
	
	public Optional<UserPrediction> findById(int id) {
		// TODO Auto-generated method stub
        return userPredictionRepo.findById(id);
	}
	
	public void deleteById(int id) {
        userPredictionRepo.deleteById(id);
    }

}
