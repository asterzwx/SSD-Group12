package hello.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UserPrediction {
	public UserPrediction() {}
	
	
	public UserPrediction(int id, String username, int match_id, int result, int prediction,
			boolean points_allocated) {
		super();
		this.id = id;
		this.username = username;
		this.match_id = match_id;
		this.result = result;
		this.prediction = prediction;
		this.points_allocated = points_allocated;
	}


	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "match_id")
	private int match_id;
	
	@Column(name = "result")
	private int result;
	
	@Column(name = "prediction")
	private int prediction;
	
	@Column(name = "points_allocated")
	private boolean points_allocated;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getMatch_id() {
		return match_id;
	}

	public void setMatch_id(int match_id) {
		this.match_id = match_id;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public int getPrediction() {
		return prediction;
	}

	public void setPrediction(int prediction) {
		this.prediction = prediction;
	}


	public boolean isPoints_allocated() {
		return points_allocated;
	}


	public void setPoints_allocated(boolean points_allocated) {
		this.points_allocated = points_allocated;
	}
	
	

}
