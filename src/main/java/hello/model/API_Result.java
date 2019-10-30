package hello.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class API_Result {
	
	
	public API_Result(int team_id, int score) {
		super();
		this.team_id = team_id;
		this.score = score;
	}

	public API_Result() {}
	
	@SerializedName("team_id")
	@Expose
	public int team_id;
	
	@SerializedName("score")
	@Expose
	public int score;

	public int getTeam_id() {
		return team_id;
	}

	public void setTeam_id(int team_id) {
		this.team_id = team_id;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	

}
