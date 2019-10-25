package hello.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.constraints.Null;

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
import hello.model.API_League;
import hello.model.Item;
import hello.model.League;
import hello.model.API_League;
import hello.repo.DotaLeagueRepo;
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
	DotaLeagueRepo leagueRepo;
	private RepositoryInterface service;
	
	List<API_League> leagues = null;

	public LeagueService() {
		Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api.pandascore.co/")
				.addConverterFactory(GsonConverterFactory.create()).build();
		service = retrofit.create(RepositoryInterface.class);
	}

	public List<API_League> getLeagues() throws IOException {	
		Call<List<API_League>> call = service.listLeagues(API_KEY);
		call.enqueue(new Callback<List<API_League>>() {
			@Override
			public void onResponse(Call<List<API_League>> call, Response<List<API_League>> response) {
			
				leagues = response.body();
				Gson responseGson = new Gson();
				responseGson.toJson(response.body());
				
				if(getAll().size() == 0) {
					for (API_League u : response.body()) {
				           int id = u.getId();
				           String name = u.getName();
				           String slug = u.getSlug();
				           
				           saveLeagueDetails(id, name, slug);			           
				        }
					System.out.println("Saved all league details to DB");					
				}				
			}
			@Override
			public void onFailure(Call<List<API_League>> call, Throwable t) {
				// TODO Auto-generated method stub
				System.out.println("ERROR: " + t.getMessage());
			}
		});
		return leagues;
		 
	}
	
	public List<League> getAll() {
		// TODO Auto-generated method stub				
		return leagueRepo.findAll();
	}
	
	
	public League saveLeague(League league) {		
		return leagueRepo.save(league);
	}
	
	public League saveLeagueDetails(int id, String name, String slug) {	
		League league = new League();
		league.setLeague_id(id);
		league.setLeague_name(name);
		league.setLeague_slug(slug);
		return leagueRepo.save(league);
	}
	
	

}
