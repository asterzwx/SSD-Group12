package hello.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hello.model.Item;
import hello.repo.ItemRepo;

@Service
@Transactional
public class ItemService {

	@Autowired
	ItemRepo itemRepo;
	
	public List<Item> getAll() {
		// TODO Auto-generated method stub				
		return itemRepo.findAll();
	}
	
	public Item saveItem(Item item) {		
		return itemRepo.save(item);
	}
	
	public Optional<Item> findById(int item_id) {
		// TODO Auto-generated method stub
        return itemRepo.findById(item_id);        
	}
	
	public boolean existById(int item_id) {
		// TODO Auto-generated method stub
        return itemRepo.existsById(item_id);        
	}
	
	public void deleteById(int item_id) {
		itemRepo.deleteById(item_id);
    }

}
