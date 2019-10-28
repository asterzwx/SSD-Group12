package hello.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UserInventory {
	
	public UserInventory() {}
	
	public UserInventory(String username, int points, int item_id, boolean item_in_use) {
		super();
		this.username = username;
		this.points = points;
		this.item_id = item_id;
		this.item_in_use = item_in_use;
	}



	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "username")
	private String username;

	@Column(name = "points")
	private int points;

	@Column(name = "item_id")
	private int item_id;
	
	@Column(name = "item_in_use")
	private boolean item_in_use;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public int getItem_id() {
		return item_id;
	}

	public void setItem_id(int item_id) {
		this.item_id = item_id;
	}

	public boolean getItem_in_use() {
		return item_in_use;
	}

	public void setItem_in_use(boolean item_in_use) {
		this.item_in_use = item_in_use;
	}
	
	

}
