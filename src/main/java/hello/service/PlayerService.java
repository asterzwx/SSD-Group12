package hello.service;

import java.io.IOException;
import java.util.List;

import org.hibernate.query.criteria.internal.expression.function.AggregationFunction.COUNT;
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
					int count = 0;
					for (API_Player u : response.body()) {
						int id = u.getId();
						

						if (!videogame.equals(null) && !u.getName().equals(null)) {
//							try {
								System.out.println("=============");
//								System.out.println(checkNull(u.getHometown()));
//								name = Optional.fromNullable(u.getName()).or(NULL_STRING);
//								current_team = Optional.fromNullable(u.getCurrent_team().getId()).or(NULL_INT);
//								videogame = Optional.fromNullable(u.getVideogame().getName()).or(NULL_STRING);
//								hometown = Optional.fromNullable(u.getHometown()).or(NULL_STRING);
//								image_url = Optional.fromNullable(u.getImage_url()).or(NULL_IMAGE);
//								role = Optional.fromNullable(u.getRole()).or(NULL_STRING);
//								first_name = Optional.fromNullable(u.getFirst_name()).or(NULL_STRING);
//								last_name = Optional.fromNullable(u.getLast_name()).or(NULL_STRING);
//								team_name = Optional.fromNullable(u.getCurrent_team().getName()).or(NULL_STRING);
								try {
									name = checkNullString(u.getName());
								} catch (Exception e) {
									// TODO: handle exception
									name = NULL_STRING;
								}
								try {
									current_team = checkNullInt(u.getCurrent_team().getId());

								} catch (Exception e) {									
									current_team = NULL_INT;				
								}
								try {
									videogame = checkNullString(u.getVideogame().getName());

								} catch (Exception e) {								
									videogame = NULL_STRING;
								}
								try {
									hometown = checkNullString(u.getHometown());

								} catch (Exception e) {
									hometown = NULL_STRING;
								}
								try {
									image_url = checkNullImage(u.getImage_url());

								} catch (Exception e) {
									image_url = NULL_IMAGE;
								}
								try {
									role = checkNullString(u.getRole());

								} catch (Exception e) {
									role = NULL_STRING;
								}
								try {
									first_name = checkNullString(u.getFirst_name());

								} catch (Exception e) {
									first_name = NULL_STRING;
								}
								try {
									last_name = checkNullString(u.getLast_name());

								} catch (Exception e) {
									last_name = NULL_STRING;
								}
								try {
									team_name =	checkNullString(u.getCurrent_team().getName());	

								} catch (Exception e) {
									team_name = NULL_STRING;
								}
								
//							} catch (Exception e) {
//								// TODO: handle exception
//								System.out.println("ERROR: getDotaPlayers " + e.getMessage());
//							}

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

	public String checkNullString(String x) {
		if(x == null || x == "" || x.equals("") || x.equals(null)) {
			return NULL_STRING;
		}
		return x;
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

//						try {
//							name = Optional.fromNullable(u.getName()).or(NULL_STRING);
//							current_team = Optional.fromNullable(u.getCurrent_team().getId()).or(NULL_INT);
//							videogame = Optional.fromNullable(u.getVideogame().getName()).or(NULL_STRING);
//							hometown = Optional.fromNullable(u.getHometown()).or(NULL_STRING);
//							image_url = Optional.fromNullable(u.getImage_url()).or(NULL_IMAGE);
//							role = Optional.fromNullable(u.getRole()).or(NULL_STRING);
//							first_name = Optional.fromNullable(u.getFirst_name()).or(NULL_STRING);
//							last_name = Optional.fromNullable(u.getLast_name()).or(NULL_STRING);
//							team_name = Optional.fromNullable(u.getCurrent_team().getName()).or(NULL_STRING);

							try {
								name = checkNullString(u.getName());
							} catch (Exception e) {
								// TODO: handle exception
								name = NULL_STRING;
							}
							try {
								current_team = checkNullInt(u.getCurrent_team().getId());

							} catch (Exception e) {									
								current_team = NULL_INT;				
							}
							try {
								videogame = checkNullString(u.getVideogame().getName());

							} catch (Exception e) {								
								videogame = NULL_STRING;
							}
							try {
								hometown = checkNullString(u.getHometown());

							} catch (Exception e) {
								hometown = NULL_STRING;
							}
							try {
								image_url = checkNullImage(u.getImage_url());

							} catch (Exception e) {
								image_url = NULL_IMAGE;
							}
							try {
								role = checkNullString(u.getRole());

							} catch (Exception e) {
								role = NULL_STRING;
							}
							try {
								first_name = checkNullString(u.getFirst_name());

							} catch (Exception e) {
								first_name = NULL_STRING;
							}
							try {
								last_name = checkNullString(u.getLast_name());

							} catch (Exception e) {
								last_name = NULL_STRING;
							}
							try {
								team_name =	checkNullString(u.getCurrent_team().getName());	

							} catch (Exception e) {
								team_name = NULL_STRING;
							}
//						} catch (Exception e) {
//							// TODO: handle exception
//							System.out.println("ERROR: getLoLPlayers " + e.getMessage());
//						}

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

	public int checkNullInt(Integer x) {
		x.toString();
		if(x.toString() == null || x.toString() == "" || x.toString().equals("") || x.toString().equals(null)) {
			return NULL_INT;
		}
		return x;
	}
	
	public String checkNullImage(String x) {
		if(x == null || x == "" || x.equals("") || x.equals(null)) {
			return NULL_IMAGE;
		}
		return x;
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
