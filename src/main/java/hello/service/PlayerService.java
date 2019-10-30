package hello.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import hello.APIConfiguration;
import hello.RepositoryInterface;
import hello.model.API_League;
import hello.model.API_Player;
import hello.model.League;
import hello.model.Opponent;
import hello.model.Player;
import hello.repo.PlayerRepo;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Service
public class PlayerService implements APIConfiguration {
	@Autowired
	PlayerRepo playerRepo;
	private RepositoryInterface service;

	List<API_Player> players = null;

	public PlayerService() {
		Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api.pandascore.co/")
				.addConverterFactory(GsonConverterFactory.create()).build();
		service = retrofit.create(RepositoryInterface.class);
	}

	public List<API_Player> getDotaPlayers() throws IOException {
		for (int i = 1; i < 5; i++) {
			Call<List<API_Player>> call = service.listDotaPlayers(API_KEY, 100, i);
			call.enqueue(new Callback<List<API_Player>>() {
				@Override
				public void onResponse(Call<List<API_Player>> call, Response<List<API_Player>> response) {

					players = response.body();
					Gson responseGson = new Gson();
					responseGson.toJson(response.body());
					String videogame = "";
					String image_url = "";
					String hometown = "";
					int current_team = 0;
					String role = "";

					for (API_Player u : response.body()) {
						int id = u.getId();
						String name = u.getName();

						try {
							if (u.getVideogame().getName().equals(null)) {
								videogame = "";
							} else {
								videogame = u.getVideogame().getName();
							}
							if (u.getImage_url().equals(null)) {
								image_url = "";
							} else {
								image_url = u.getImage_url();
							}
							if (u.getHometown().equals(null)) {
								hometown = "";
							} else {
								hometown = u.getHometown();
							}
							if (u.getCurrent_team().getId() == 0) {
								current_team = 0;
							} else {
								current_team = u.getCurrent_team().getId();
							}
							if (u.getRole().equals(null)) {
								role = "";
							} else {
								role = u.getRole();
							}

						} catch (Exception e) {
							// TODO: handle exception
							System.out.println("ERROR: " + e.getMessage());
						}

						savePlayerDetails(id, name, current_team, videogame, hometown, image_url, role);
					}
					System.out.println("Saved all Dota players to DB");
//				}				
				}

				@Override
				public void onFailure(Call<List<API_Player>> call, Throwable t) {
					// TODO Auto-generated method stub
					System.out.println("ERROR: " + t.getMessage());
				}
			});

		}
		return players;

	}

	public List<API_Player> getLoLPlayers() throws IOException {
		for (int i = 1; i < 3; i++) {
			Call<List<API_Player>> call = service.listLoLPlayers(API_KEY, 100, i);
			call.enqueue(new Callback<List<API_Player>>() {
				@Override
				public void onResponse(Call<List<API_Player>> call, Response<List<API_Player>> response) {

					players = response.body();
					Gson responseGson = new Gson();
					responseGson.toJson(response.body());
					String videogame = "";
					String image_url = "";
					String hometown = "";
					int current_team = 0;
					String role = "";

					for (API_Player u : response.body()) {
						int id = u.getId();
						String name = u.getName();

						try {
							if (u.getVideogame().getName().equals(null)) {
								videogame = "";
							} else {
								videogame = u.getVideogame().getName();
							}
							if (u.getImage_url().equals(null)) {
								image_url = "";
							} else {
								image_url = u.getImage_url();
							}
							if (u.getHometown().equals(null)) {
								hometown = "";
							} else {
								hometown = u.getHometown();
							}
							if (u.getCurrent_team().getId() == 0) {
								current_team = 0;
							} else {
								current_team = u.getCurrent_team().getId();
							}
							if (u.getRole().equals(null)) {
								role = "";
							} else {
								role = u.getRole();
							}

						} catch (Exception e) {
							// TODO: handle exception
							System.out.println("ERROR: " + e.getMessage());
						}

						savePlayerDetails(id, name, current_team, videogame, hometown, image_url, role);
					}
					System.out.println("Saved all LoL players to DB");
//					}				
				}

				@Override
				public void onFailure(Call<List<API_Player>> call, Throwable t) {
					// TODO Auto-generated method stub
					System.out.println("ERROR: " + t.getMessage());
				}
			});
		}

		return players;

	}

	public List<Player> getAll() {
		// TODO Auto-generated method stub
		return playerRepo.findAll();
	}

	public Player savePlayer(Player player) {
		return playerRepo.save(player);
	}

	public Player savePlayerDetails(int id, String name, int current_team, String videogame, String hometown,
			String image_url, String role) {
		Player player = new Player();
		player.setId(id);
		player.setName(name);
		player.setCurrent_team(current_team);
		player.setVideogame(videogame);
		player.setHometown(hometown);
		player.setImage_url(image_url);
		player.setRole(role);
		return playerRepo.save(player);
	}

}
