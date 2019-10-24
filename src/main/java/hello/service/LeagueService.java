package hello.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.apache.catalina.startup.ClassLoaderFactory.Repository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import hello.APIConfiguration;
import hello.RepositoryInterface;
import hello.model.League;
import hello.model.LeagueAPI;
import hello.repo.LeagueRepo;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.*;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Service
//@Transactional
public class LeagueService implements APIConfiguration {
	@Autowired
	LeagueRepo leagueRepo;

	private RepositoryInterface service;
	
	List<LeagueAPI> leagues = null;

	public LeagueService() {

		Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api.pandascore.co/")
				.addConverterFactory(GsonConverterFactory.create()).build();

		service = retrofit.create(RepositoryInterface.class);
	}

	public List<LeagueAPI> getLeagues() throws IOException {		

		Call<List<LeagueAPI>> call = service.listLeagues(API_KEY);
		call.enqueue(new Callback<List<LeagueAPI>>() {

			@Override
			public void onResponse(Call<List<LeagueAPI>> call, Response<List<LeagueAPI>> response) {
				// TODO Auto-generated method stub
				leagues = response.body();
//				System.out.println(response.body());
				Gson responseGson = new Gson();
				responseGson.toJson(response.body());
//				System.out.println("@@@@@@@@@ " + responseGson);
//				JsonArray json = new JsonArray();
//				json.add(responseGson.toJson(response.body()));
//				// iterate the json object to save the details
//				for(int i=0; i<json.size(); i++) {
//					System.out.println(json.get(i));
//				}
				
				for (LeagueAPI u : response.body()) {
			           int id = u.getId();
			           String name = u.getName();
			           String slug = u.getSlug();
			           String img = u.getImageUrl();
			           saveLeagueDetails(id, name, slug, img);			           
			        }
				System.out.println("Saved all league details to DB");
			}

			@Override
			public void onFailure(Call<List<LeagueAPI>> call, Throwable t) {
				// TODO Auto-generated method stub
				System.out.println("ERROR: " + t.getMessage());
			}
		});
		return leagues;
		 
	}
	
	
	public League saveLeague(League league) {		
		return leagueRepo.save(league);
	}
	
	public League saveLeagueDetails(int id, String name, String slug, String img) {	
		League league = new League();
		league.setLeague_id(id);
		league.setLeague_name(name);
		league.setLeague_slug(slug);
		league.setLeague_img(img);
		return leagueRepo.save(league);
	}
	
	
	public static void printJsonObject(JSONObject jsonObj) {
	    jsonObj.keySet().forEach(keyStr ->
	    {
	        Object keyvalue = jsonObj.get(keyStr);
	        System.out.println("key: "+ keyStr + " value: " + keyvalue);

//	        for nested objects iteration if required
	        if (keyvalue instanceof JSONObject)
	            printJsonObject((JSONObject)keyvalue);
	    });
	}

}
