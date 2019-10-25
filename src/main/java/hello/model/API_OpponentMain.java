package hello.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class API_OpponentMain {
	public API_OpponentMain() {}
	
	public API_OpponentMain(API_Opponent opponent, String type) {
		super();
		this.opponent = opponent;
		this.type = type;
	}
	@SerializedName("opponent")
	@Expose
	public API_Opponent opponent;
	@Expose
	@SerializedName("type")
	public String type;
	
	public API_Opponent getOpponent() {
		return opponent;
	}
	public void setOpponent(API_Opponent opponent) {
		this.opponent = opponent;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	

}
