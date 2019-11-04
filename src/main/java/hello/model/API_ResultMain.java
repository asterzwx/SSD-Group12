package hello.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class API_ResultMain {
	public API_ResultMain() {}
	
	
	public API_ResultMain(API_Result results) {
		super();
		this.results = results;
	}




	@SerializedName("results")
	@Expose
	public API_Result results;


	public API_Result getResults() {
		return results;
	}


	public void setResults(API_Result results) {
		this.results = results;
	}
	
	

}
