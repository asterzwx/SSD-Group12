package hello.service;

import java.io.IOException;
import java.util.List;

import javax.validation.constraints.Null;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.YamlJsonParser;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import hello.APIConfiguration;
import hello.RepositoryInterface;
import hello.model.API_Game;
import hello.model.API_League;
import hello.model.API_Match;
import hello.model.API_Opponent;
import hello.model.API_OpponentMain;
import hello.model.API_Serie;
import hello.model.API_Tournament;
import hello.model.Game;
import hello.model.League;
import hello.model.Match;
import hello.model.Opponent;
import hello.model.Tournament;
import hello.repo.LeagueRepo;
import hello.repo.GameRepo;
import hello.repo.MatchRepo;
import hello.repo.OpponentRepo;
import hello.repo.TournamentRepo;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Service
public class OpponentService implements APIConfiguration {
	@Autowired
	OpponentRepo opponentRepo;
	@Autowired
	MatchRepo matchRepo;
	@Autowired
	GameRepo gameRepo;

	private RepositoryInterface service;
	List<API_Match> dotaMatches = null;
	List<API_Game> dotaGames = null;
	List<API_Match> lolMatches = null;
	List<API_Game> lolGames = null;
	
	List<API_Tournament> dotaTournaments = null;

	List<API_OpponentMain> dotaMatchOpponents = null;

	List<API_Match> lolPastMatches = null;
	List<API_Match> lolRunningMatches = null;
	List<API_Match> lolUpcomingMatches = null;

	public OpponentService() {
		Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api.pandascore.co/")
				.addConverterFactory(GsonConverterFactory.create()).build();
		service = retrofit.create(RepositoryInterface.class);
	}

	// GET OPPONENTS BY MATCH

//	public List<API_OpponentMain> getDotaMatchOpponentsById(int match_id) throws IOException {
//		Call<List<API_Match>> call = service.listAllPastMatches(API_KEY);
//		call.enqueue(new Callback<List<API_Match>>() {
//			@Override
//			public void onResponse(Call<List<API_Match>> call, Response<List<API_Match>> response) {
//				dotaMatches = response.body();
//				Gson responseGson = new Gson();
//				String acronym = "";
//				for (API_Match u : response.body()) {
//
//					if (match_id == u.getId()) {
//						for (API_OpponentMain g : u.getOpponents()) {
//							try {
//								if (g.getOpponent().getAcronym().equals(null)) {
//									acronym = "";
//								} else {
//									acronym = g.getOpponent().getAcronym();
//								}
//							} catch (Exception e) {
//								// TODO: handle exception
//							}
//							System.out.println("@@@ " + g.getOpponent().getId());
//							System.out.println("@@@ " + g.getOpponent().getAcronym());
//							System.out.println("@@@ " + g.getOpponent().getName());
//							System.out.println("@@@ " + g.getOpponent().getImageUrl());
//							System.out.println("@@@ " + u.getId());
//
//							saveMatchOpponentDetails(g.getOpponent().getId(), acronym, g.getOpponent().getName(),
//									g.getOpponent().getImageUrl(), match_id);
//						}
//					}
//				}
//				System.out.println("Saved Dota opponents for " + match_id + " to DB");
//			}
//
//			@Override
//			public void onFailure(Call<List<API_Match>> call, Throwable t) {
//				// TODO Auto-generated method stub
//				System.out.println("ERROR: " + t.getMessage());
//			}
//		});
//		return dotaMatchOpponents;
//	}

	public List<API_OpponentMain> getLoLPastMatchOpponentsById(int match_id) throws IOException {
		Call<List<API_Match>> call = service.listLoLPastMatches(API_KEY);
		call.enqueue(new Callback<List<API_Match>>() {
			@Override
			public void onResponse(Call<List<API_Match>> call, Response<List<API_Match>> response) {
				dotaMatches = response.body();
				Gson responseGson = new Gson();
				String acronym = "";
				for (API_Match u : response.body()) {

					if (match_id == u.getId()) {
						for (API_OpponentMain g : u.getOpponents()) {
							try {
								if (g.getOpponent().getAcronym().equals(null)) {
									acronym = "";
								} else {
									acronym = g.getOpponent().getAcronym();
								}
							} catch (Exception e) {
								// TODO: handle exception
							}
							System.out.println("@@@ " + g.getOpponent().getId());
							System.out.println("@@@ " + g.getOpponent().getAcronym());
							System.out.println("@@@ " + g.getOpponent().getName());
							System.out.println("@@@ " + g.getOpponent().getImageUrl());
							System.out.println("@@@ " + u.getId());

							saveMatchOpponentDetails(g.getOpponent().getId(), acronym, g.getOpponent().getName(),
									g.getOpponent().getImageUrl(), match_id);
						}
					}
				}
				System.out.println("Saved LoL Past opponents for " + match_id + " to DB");
			}

			@Override
			public void onFailure(Call<List<API_Match>> call, Throwable t) {
				// TODO Auto-generated method stub
				System.out.println("ERROR: " + t.getMessage());
			}
		});
		return dotaMatchOpponents;
	}

	public List<API_OpponentMain> getLoLRunningMatchOpponentsById(int match_id) throws IOException {
		Call<List<API_Match>> call = service.listLoLRunningMatches(API_KEY);
		call.enqueue(new Callback<List<API_Match>>() {
			@Override
			public void onResponse(Call<List<API_Match>> call, Response<List<API_Match>> response) {
				dotaMatches = response.body();
				Gson responseGson = new Gson();
				String acronym = "";
				for (API_Match u : response.body()) {

					if (match_id == u.getId()) {
						for (API_OpponentMain g : u.getOpponents()) {
							try {
								if (g.getOpponent().getAcronym().equals(null)) {
									acronym = "";
								} else {
									acronym = g.getOpponent().getAcronym();
								}
							} catch (Exception e) {
								// TODO: handle exception
							}
							System.out.println("@@@ " + g.getOpponent().getId());
							System.out.println("@@@ " + g.getOpponent().getAcronym());
							System.out.println("@@@ " + g.getOpponent().getName());
							System.out.println("@@@ " + g.getOpponent().getImageUrl());
							System.out.println("@@@ " + u.getId());

							saveMatchOpponentDetails(g.getOpponent().getId(), acronym, g.getOpponent().getName(),
									g.getOpponent().getImageUrl(), match_id);
						}
					}
				}
				System.out.println("Saved LoL Running opponents for " + match_id + " to DB");
			}

			@Override
			public void onFailure(Call<List<API_Match>> call, Throwable t) {
				// TODO Auto-generated method stub
				System.out.println("ERROR: " + t.getMessage());
			}
		});
		return dotaMatchOpponents;
	}

	public List<API_OpponentMain> getLoLUpcomingMatchOpponentsById(int match_id) throws IOException {
		Call<List<API_Match>> call = service.listLoLUpcomingMatches(API_KEY);
		call.enqueue(new Callback<List<API_Match>>() {
			@Override
			public void onResponse(Call<List<API_Match>> call, Response<List<API_Match>> response) {
				dotaMatches = response.body();
				Gson responseGson = new Gson();
				String acronym = "";
				for (API_Match u : response.body()) {

					if (match_id == u.getId()) {
						for (API_OpponentMain g : u.getOpponents()) {
							try {
								if (g.getOpponent().getAcronym().equals(null)) {
									acronym = "";
								} else {
									acronym = g.getOpponent().getAcronym();
								}
							} catch (Exception e) {
								// TODO: handle exception
							}
							System.out.println("@@@ " + g.getOpponent().getId());
							System.out.println("@@@ " + g.getOpponent().getAcronym());
							System.out.println("@@@ " + g.getOpponent().getName());
							System.out.println("@@@ " + g.getOpponent().getImageUrl());
							System.out.println("@@@ " + u.getId());

							saveMatchOpponentDetails(g.getOpponent().getId(), acronym, g.getOpponent().getName(),
									g.getOpponent().getImageUrl(), match_id);
						}
					}
				}
				System.out.println("Saved LoL Upcoming opponents for " + match_id + " to DB");
			}

			@Override
			public void onFailure(Call<List<API_Match>> call, Throwable t) {
				// TODO Auto-generated method stub
				System.out.println("ERROR: " + t.getMessage());
			}
		});
		return dotaMatchOpponents;
	}

	// GET OPPONENTS BY GAME
	public List<API_OpponentMain> getDotaTournamentOpponentsById(int tournament_id) throws IOException {
		Call<List<API_Tournament>> call = service.listDotaTournaments(API_KEY);
		call.enqueue(new Callback<List<API_Tournament>>() {
			@Override
			public void onResponse(Call<List<API_Tournament>> call, Response<List<API_Tournament>> response) {
				dotaTournaments = response.body();
				Gson responseGson = new Gson();
				String acronym = "";
				String image_url = "";
				for (API_Tournament t : response.body()) {

					if (tournament_id == t.getId()) {
						for (API_Opponent o : t.getTeams()) {
							try {
								if (o.getAcronym().equals(null)) {
									acronym = "";
								} else {
									acronym = o.getAcronym();
								}	
								if (o.getImageUrl().equals(null)) {
									image_url = "";
								} else {
									image_url = o.getImageUrl();
								}	
								
							} catch (Exception e) {
								// TODO: handle exception
								System.out.println("ERROR: " + e.getMessage());
							}					
							saveTournamentOpponentDetails(
									o.getId(), acronym, o.getName(), image_url,
									tournament_id);
							
						}
						
					}
					
				}
				System.out.println("Saved Dota teams for " + tournament_id + " to DB");
			}

			@Override
			public void onFailure(Call<List<API_Tournament>> call, Throwable t) {
				// TODO Auto-generated method stub
				System.out.println("ERROR: " + t.getMessage());
			}
		});
		return dotaMatchOpponents;
	}

	
	public List<API_OpponentMain> getLoLPastTournamentOpponentsById(int tournament_id) throws IOException {
		Call<List<API_Tournament>> call = service.listLoLPastTournaments(API_KEY);
		call.enqueue(new Callback<List<API_Tournament>>() {
			@Override
			public void onResponse(Call<List<API_Tournament>> call, Response<List<API_Tournament>> response) {
				dotaTournaments = response.body();
				Gson responseGson = new Gson();
				String acronym = "";
				String image_url = "";
				for (API_Tournament t : response.body()) {

					if (tournament_id == t.getId()) {
						for (API_Opponent o : t.getTeams()) {
							try {
								if (o.getAcronym().equals(null)) {
									acronym = "";
								} else {
									acronym = o.getAcronym();
								}	
								if (o.getImageUrl().equals(null)) {
									image_url = "";
								} else {
									image_url = o.getImageUrl();
								}	
								
							} catch (Exception e) {
								// TODO: handle exception
								System.out.println("ERROR: " + e.getMessage());
							}					
							saveTournamentOpponentDetails(
									o.getId(), acronym, o.getName(), image_url,
									tournament_id);
							
						}
						
					}
					
				}
				System.out.println("Saved Dota teams for " + tournament_id + " to DB");
			}

			@Override
			public void onFailure(Call<List<API_Tournament>> call, Throwable t) {
				// TODO Auto-generated method stub
				System.out.println("ERROR: " + t.getMessage());
			}
		});
		return dotaMatchOpponents;
	}

	public List<API_OpponentMain> getLoLRunningTournamentOpponentsById(int tournament_id) throws IOException {
		Call<List<API_Tournament>> call = service.listLoLRunningTournaments(API_KEY);
		call.enqueue(new Callback<List<API_Tournament>>() {
			@Override
			public void onResponse(Call<List<API_Tournament>> call, Response<List<API_Tournament>> response) {
				dotaTournaments = response.body();
				Gson responseGson = new Gson();
				String acronym = "";
				String image_url = "";
				for (API_Tournament t : response.body()) {

					if (tournament_id == t.getId()) {
						for (API_Opponent o : t.getTeams()) {
							try {
								if (o.getAcronym().equals(null)) {
									acronym = "";
								} else {
									acronym = o.getAcronym();
								}	
								if (o.getImageUrl().equals(null)) {
									image_url = "";
								} else {
									image_url = o.getImageUrl();
								}	
								
							} catch (Exception e) {
								// TODO: handle exception
								System.out.println("ERROR: " + e.getMessage());
							}					
							saveTournamentOpponentDetails(
									o.getId(), acronym, o.getName(), image_url,
									tournament_id);
							
						}
						
					}
					
				}
				System.out.println("Saved Dota teams for " + tournament_id + " to DB");
			}

			@Override
			public void onFailure(Call<List<API_Tournament>> call, Throwable t) {
				// TODO Auto-generated method stub
				System.out.println("ERROR: " + t.getMessage());
			}
		});
		return dotaMatchOpponents;
	}
	
	public List<API_OpponentMain> getLoLUpcomingTournamentOpponentsById(int tournament_id) throws IOException {
		Call<List<API_Tournament>> call = service.listLoLUpcomingTournaments(API_KEY);
		call.enqueue(new Callback<List<API_Tournament>>() {
			@Override
			public void onResponse(Call<List<API_Tournament>> call, Response<List<API_Tournament>> response) {
				dotaTournaments = response.body();
				Gson responseGson = new Gson();
				String acronym = "";
				String image_url = "";
				for (API_Tournament t : response.body()) {

					if (tournament_id == t.getId()) {
						for (API_Opponent o : t.getTeams()) {
							try {
								if (o.getAcronym().equals(null)) {
									acronym = "";
								} else {
									acronym = o.getAcronym();
								}	
								if (o.getImageUrl().equals(null)) {
									image_url = "";
								} else {
									image_url = o.getImageUrl();
								}	
								
							} catch (Exception e) {
								// TODO: handle exception
								System.out.println("ERROR: " + e.getMessage());
							}					
							saveTournamentOpponentDetails(
									o.getId(), acronym, o.getName(), image_url,
									tournament_id);
							
						}
						
					}
					
				}
				System.out.println("Saved Dota teams for " + tournament_id + " to DB");
			}

			@Override
			public void onFailure(Call<List<API_Tournament>> call, Throwable t) {
				// TODO Auto-generated method stub
				System.out.println("ERROR: " + t.getMessage());
			}
		});
		return dotaMatchOpponents;
	}
	
	
	public List<Opponent> getAll() {
		// TODO Auto-generated method stub
		return opponentRepo.findAll();
	}

	public Opponent saveOpponent(Opponent opponent) {
		return opponentRepo.save(opponent);
	}

	public Opponent saveMatchOpponentDetails(int id, String acronym, String name, String image_url, int match_id) {
		Opponent opponent = new Opponent();
		opponent.setId(id);
		opponent.setAcronym(acronym);
		opponent.setName(name);
		opponent.setImage_url(image_url);
		opponent.setMatch_id(match_id);
		opponent.setTournament_id(0);
		return opponentRepo.save(opponent);
	}
	
	public Opponent saveTournamentOpponentDetails(int id, String acronym, String name,
			String image_url, int tournament_id) {
		Opponent opponent = new Opponent();
		opponent.setId(id);
		opponent.setAcronym(acronym);
		opponent.setName(name);
		opponent.setImage_url(image_url);
		opponent.setMatch_id(0);
		opponent.setTournament_id(tournament_id);
		return opponentRepo.save(opponent);
	}

}
