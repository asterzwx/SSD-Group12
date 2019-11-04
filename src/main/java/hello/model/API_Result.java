package hello.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class API_Result {
	
	
	public API_Result(Integer team_id, Integer score) {
		super();
		this.team_id = team_id;
		this.score = score;
	}

	public API_Result() {}
	
	@SerializedName("team_id")
	@Expose
	public Integer team_id;
	
	@SerializedName("score")
	@Expose
	public Integer score;

	public Integer getTeam_id() {
		return team_id;
	}

	public void setTeam_id(Integer team_id) {
		this.team_id = team_id;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}
	
	

}
