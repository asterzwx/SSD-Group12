package hello.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Optional;
import com.google.gson.Gson;

import hello.APIConfiguration;
import hello.RepositoryInterface;
import hello.model.API_Player;
import hello.model.API_Serie;
import hello.model.API_Team;
import hello.model.Player;
import hello.model.Team;
import hello.repo.SerieRepo;
import hello.repo.TeamRepo;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Service
public class TeamService implements APIConfiguration {
	@Autowired
	TeamRepo teamRepo;
	
	private RepositoryInterface service;
	List<API_Team> teams = null;
	

	public TeamService() {
		Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api.pandascore.co/")
				.addConverterFactory(GsonConverterFactory.create()).build();
		service = retrofit.create(RepositoryInterface.class);
	}

	public List<API_Team> getDotaTeams() throws IOException {
		for (int i = 1; i < 5; i++) {
			Call<List<API_Team>> call = service.listDotaTeams(API_KEY, 100, i);
			call.enqueue(new Callback<List<API_Team>>() {
				@Override
				public void onResponse(Call<List<API_Team>> call, Response<List<API_Team>> response) {

					teams = response.body();
					Gson responseGson = new Gson();
					responseGson.toJson(response.body());
					String videogame = "";
					String image_url = "";
					String acronym = "";
					int id = 0;
					String name = "";
					System.out.println("@@@@@@@@@@@@@@@@");
					for (API_Team u : response.body()) {						
						try {
							id = Optional.fromNullable(u.getId()).or(NULL_INT);
							acronym = Optional.fromNullable(u.getAcronym()).or(NULL_STRING);
							name = Optional.fromNullable(u.getName()).or(NULL_STRING);
							image_url = Optional.fromNullable(u.getImage_url()).or(NULL_IMAGE);
							videogame = Optional.fromNullable(u.getVideogame().getName()).or(NULL_STRING);
						
							saveTeamDetails(id, acronym, name, image_url, videogame);

						} catch (Exception e) {
							// TODO: handle exception
							System.out.println("ERROR getDotaTeams " + e.getMessage());
						}
					}
					System.out.println("Saved all Dota teams to DB");
//				}				
				}

				@Override
				public void onFailure(Call<List<API_Team>> call, Throwable t) {
					// TODO Auto-generated method stub
					System.out.println("ERROR: " + t.getMessage());
				}
			});

		}
		return teams;
	}
	
	
	public List<API_Team> getLoLTeams() throws IOException {
		for (int i = 1; i < 5; i++) {
			Call<List<API_Team>> call = service.listLoLTeams(API_KEY, 100, i);
			call.enqueue(new Callback<List<API_Team>>() {
				@Override
				public void onResponse(Call<List<API_Team>> call, Response<List<API_Team>> response) {

					teams = response.body();
					Gson responseGson = new Gson();
					responseGson.toJson(response.body());
					String videogame = "";
					String image_url = "";
					String acronym = "";
					int id = 0;
					String name = "";
					
					for (API_Team u : response.body()) {						
						try {
							id = Optional.fromNullable(u.getId()).or(NULL_INT);
							acronym = Optional.fromNullable(u.getAcronym()).or(NULL_STRING);
							name = Optional.fromNullable(u.getName()).or(NULL_STRING);
							image_url = Optional.fromNullable(u.getImage_url()).or(NULL_IMAGE);
							videogame = Optional.fromNullable(u.getVideogame().getName()).or(NULL_STRING);
							
							saveTeamDetails(id, acronym, name, image_url, videogame);
						} catch (Exception e) {
							// TODO: handle exception
							System.out.println("ERROR getLoLTeams" + e.getMessage());
						}
						
					}
					System.out.println("Saved all Dota teams to DB");
//				}				
				}

				@Override
				public void onFailure(Call<List<API_Team>> call, Throwable t) {
					// TODO Auto-generated method stub
					System.out.println("ERROR: " + t.getMessage());
				}
			});

		}
		return teams;

	}
		
	public List<Team> getAll() {
		// TODO Auto-generated method stub
		return teamRepo.findAll();
	}

	public Team savePlayer(Team team) {
		return teamRepo.save(team);
	}

	public Team saveTeamDetails(int id, String acronym, String name, String image_url, String videogame) {
		Team team = new Team();
		team.setId(id);
		team.setAcronym(acronym);
		team.setName(name);
		team.setImage_url(image_url);
		team.setVideogame(videogame);
		return teamRepo.save(team);
	}
}
