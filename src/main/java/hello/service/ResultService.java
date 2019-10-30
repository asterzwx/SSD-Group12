package hello.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hello.APIConfiguration;
import hello.model.Poll;
import hello.model.Result;
import hello.repo.ResultRepo;
@Service
@Transactional
public class ResultService implements APIConfiguration{
	@Autowired
	ResultRepo resultRepo;
	
	public List<Result> getAll() {
		// TODO Auto-generated method stub				
		return resultRepo.findAll();
	}
}
