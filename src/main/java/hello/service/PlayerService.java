package hello.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Optional;
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
		for (int i = 1; i < 3; i++) {
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
					String first_name = "";
					String last_name = "";
					String team_name = "";
					String name = "";
					for (API_Player u : response.body()) {
						int id = u.getId();
						videogame = Optional.fromNullable(u.getVideogame().getName()).or(NULL_STRING);

						if (!videogame.equals(null)) {
							try {
								name = Optional.fromNullable(u.getName()).or(NULL_STRING);
								current_team = Optional.fromNullable(u.getCurrent_team().getId()).or(NULL_INT);
								videogame = Optional.fromNullable(u.getVideogame().getName()).or(NULL_STRING);
								hometown = Optional.fromNullable(u.getHometown()).or(NULL_STRING);
								image_url = Optional.fromNullable(u.getImage_url()).or(NULL_IMAGE);
								role = Optional.fromNullable(u.getRole()).or(NULL_STRING);
								first_name = Optional.fromNullable(u.getFirst_name()).or(NULL_STRING);
								last_name = Optional.fromNullable(u.getLast_name()).or(NULL_STRING);
								team_name = Optional.fromNullable(u.getCurrent_team().getName()).or(NULL_STRING);

							} catch (Exception e) {
								// TODO: handle exception
								System.out.println("ERROR: getDotaPlayers " + e.getMessage());
							}

							savePlayerDetails(id, name, current_team, videogame, hometown, image_url, role, first_name,
									last_name, team_name);
						}

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
					String first_name = "";
					String last_name = "";
					String team_name = "";
					String name = "";
					for (API_Player u : response.body()) {
						int id = u.getId();

						try {
							name = Optional.fromNullable(u.getName()).or(NULL_STRING);
							current_team = Optional.fromNullable(u.getCurrent_team().getId()).or(NULL_INT);
							videogame = Optional.fromNullable(u.getVideogame().getName()).or(NULL_STRING);
							hometown = Optional.fromNullable(u.getHometown()).or(NULL_STRING);
							image_url = Optional.fromNullable(u.getImage_url()).or(NULL_IMAGE);
							role = Optional.fromNullable(u.getRole()).or(NULL_STRING);
							first_name = Optional.fromNullable(u.getFirst_name()).or(NULL_STRING);
							last_name = Optional.fromNullable(u.getLast_name()).or(NULL_STRING);
							team_name = Optional.fromNullable(u.getCurrent_team().getName()).or(NULL_STRING);

						} catch (Exception e) {
							// TODO: handle exception
							System.out.println("ERROR: getLoLPlayers " + e.getMessage());
						}

						savePlayerDetails(id, name, current_team, videogame, hometown, image_url, role, first_name,
								last_name, team_name);
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
			String image_url, String role, String first_name, String last_name, String team_name) {
		Player player = new Player();
		player.setId(id);
		player.setName(name);
		player.setCurrent_team(current_team);
		player.setVideogame(videogame);
		player.setHometown(hometown);
		player.setImage_url(image_url);
		player.setRole(role);
		player.setFirst_name(first_name);
		player.setLast_name(last_name);
		player.setTeam_name(team_name);
		return playerRepo.save(player);
	}

}
