package hello.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.Null;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.YamlJsonParser;
import org.springframework.stereotype.Service;

import com.google.common.base.Optional;
import com.google.gson.Gson;

import hello.APIConfiguration;
import hello.RepositoryInterface;
import hello.model.API_Game;
import hello.model.API_League;
import hello.model.API_Match;
import hello.model.API_OpponentMain;
import hello.model.API_Result;
import hello.model.API_ResultMain;
import hello.model.API_Tournament;
import hello.model.Game;
import hello.model.League;
import hello.model.Match;
import hello.model.Opponent;
import hello.model.Result;
import hello.model.Tournament;
import hello.repo.LeagueRepo;
import hello.repo.GameRepo;
import hello.repo.MatchRepo;
import hello.repo.MatchView;
import hello.repo.OpponentRepo;
import hello.repo.ResultRepo;
import hello.repo.TournamentRepo;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Service
public class MatchService implements APIConfiguration {
	@Autowired
	MatchRepo matchRepo;
	@Autowired
	GameRepo gameRepo;
	@Autowired
	OpponentRepo opponentRepo;
	@Autowired
	ResultRepo resultRepo;

	private RepositoryInterface service;
	OpponentService opponentService;

	List<API_Match> dotaMatches = null;
	List<API_Game> dotaGames = null;
	List<API_Match> lolMatches = null;
	List<API_Game> lolGames = null;

	List<API_Match> lolPastMatches = null;
	List<API_Match> lolRunningMatches = null;
	List<API_Match> lolUpcomingMatches = null;

	List<API_Match> pastMatches = null;
	List<API_Match> runningMatches = null;
	List<API_Match> upcomingMatches = null;
	
	PlayerService playerService = new PlayerService();


	public MatchService() {
		Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api.pandascore.co/")
				.addConverterFactory(GsonConverterFactory.create()).build();
		service = retrofit.create(RepositoryInterface.class);
	}

	public List<API_Match> getAllLoLMatches() throws IOException {
		Call<List<API_Match>> call = service.listAllLoLMatches(API_KEY, 20, "2019-10-01T00:00:00Z,2019-11-30T23:59:59Z",
				"-scheduled_at");
		call.enqueue(new Callback<List<API_Match>>() {
			@Override
			public void onResponse(Call<List<API_Match>> call, Response<List<API_Match>> response) {
				lolMatches = response.body();
				Gson responseGson = new Gson();
				int uniqueKey = 0;
				for (API_Match u : response.body()) {

					if (u.getVideogame().getName().equals("LoL")) {

						System.out.println(u.getId());
						String match_id = u.getId().toString();
						String begin_at = "";
						String end_at = "";
						String match_type = u.getMatchType();
						String match_name = u.getName();
						int num_of_games = u.getNumberOfGames();
						int league_id = u.getLeagueId();
						String series_id = u.getSerieId().toString();
						int tournament_id = u.getTournamentId();
						String winner_id = "";
						String videogame = u.getVideogame().getName();
						String scheduled_at = u.getScheduledAt();
//						try {
						try {							
							winner_id = playerService.checkNullString(u.getWinnerId().toString());
						} catch (Exception e) {							
							winner_id = NULL_STRING;
						}
						try {
							begin_at = playerService.checkNullString(u.getBeginAt().toString());
						} catch (Exception e) {
							begin_at = NULL_STRING;
						}
						try {
							end_at = playerService.checkNullString(u.getEndAt().toString());
						} catch (Exception e) {
							end_at = NULL_STRING;
						}
						try {
							scheduled_at = playerService.checkNullString(u.getScheduledAt());
						} catch (Exception e) {
							scheduled_at = NULL_STRING;
						}
//							winner_id = Optional.fromNullable(u.getWinnerId().toString()).or(NULL_STRING);
//							begin_at = Optional.fromNullable(u.getBeginAt()).or(NULL_STRING);
//							end_at = Optional.fromNullable(u.getEndAt()).or(NULL_STRING);
//							scheduled_at = Optional.fromNullable(u.getScheduledAt()).or(NULL_STRING);						

//						} catch (Exception e) {
//							System.out.println("ERROR getAllLoLMatches: " + e.getMessage());
//						}
						saveMatchDetails(Integer.parseInt(match_id), begin_at, end_at, match_type, match_name,
								num_of_games, league_id, series_id, tournament_id, winner_id, videogame, scheduled_at);

						for (API_OpponentMain g : u.getOpponents()) {
							int opponent_id = 0;
							String acronym = "";
							String opponent_name = "";
							String image_url = "";

//							try {
							try {
								opponent_id = playerService.checkNullInt(g.getOpponent().getId());
							} catch (Exception e) {
								opponent_id = NULL_INT;
							}
							try {
								opponent_name = playerService.checkNullString(g.getOpponent().getName());
							} catch (Exception e) {
								opponent_name = NULL_STRING;
							}
							try {
								acronym = playerService.checkNullString(g.getOpponent().getAcronym());
							} catch (Exception e) {
								acronym = NULL_STRING;
							}
							try {
								image_url = playerService.checkNullImage(g.getOpponent().getImageUrl());
							} catch (Exception e) {
								image_url = NULL_IMAGE;
							}
//								opponent_id = Optional.fromNullable(g.getOpponent().getId()).or(NULL_INT);
//								opponent_name = Optional.fromNullable(g.getOpponent().getName()).or(NULL_STRING);
//								acronym = Optional.fromNullable(g.getOpponent().getAcronym()).or(NULL_STRING);
//								image_url = Optional.fromNullable(g.getOpponent().getImageUrl()).or(NULL_IMAGE);
//								
//							} catch (Exception e) {
//								// TODO: handle exception
//								System.out.println(e.getMessage());
//							}
							
								saveMatchOpponentDetails(opponent_id, acronym, opponent_name, image_url, u.getId());
							

						}

					}
				}
				System.out.println("Saved all Past matches to DB");

			}

			@Override
			public void onFailure(Call<List<API_Match>> call, Throwable t) {
				// TODO Auto-generated method stub
				System.out.println("ERROR: " + t.getMessage());
			}
		});

		return lolMatches;
	}

	public List<API_Match> getAllDotaMatches() throws IOException {
		Call<List<API_Match>> call = service.listAllDotaMatches(API_KEY, 100,
				"2019-10-01T00:00:00Z,2019-11-30T23:59:59Z", "-scheduled_at");
		call.enqueue(new Callback<List<API_Match>>() {
			@Override
			public void onResponse(Call<List<API_Match>> call, Response<List<API_Match>> response) {
				lolMatches = response.body();
				Gson responseGson = new Gson();
				int uniqueKey = 0;

				for (API_Match u : response.body()) {
					if (u.getVideogame().getName().equals("Dota 2")) {

						System.out.println(u.getId());
						String match_id = u.getId().toString();
						String begin_at = "";
						String end_at = "";
						String match_type = u.getMatchType();
						String match_name = u.getName();
						int num_of_games = u.getNumberOfGames();
						int league_id = u.getLeagueId();
						String series_id = u.getSerieId().toString();
						int tournament_id = u.getTournamentId();
						String winner_id = "";
						String videogame = u.getVideogame().getName();
						String scheduled_at = u.getScheduledAt();
						System.out.println("-----------");
						System.out.println(u.getBeginAt());
//						try {
//							winner_id = Optional.fromNullable(u.getWinnerId().toString()).or(NULL_STRING);
//							begin_at = Optional.fromNullable(u.getBeginAt()).or(NULL_STRING);
//							end_at = Optional.fromNullable(u.getEndAt()).or(NULL_STRING);
//							scheduled_at = Optional.fromNullable(u.getScheduledAt()).or(NULL_STRING);	
//							
//						} catch (Exception e) {
//							System.out.println("ERROR: getAllDotaMatches " + e.getMessage());
//						}
						try {							
							winner_id = playerService.checkNullString(u.getWinnerId().toString());
						} catch (Exception e) {							
							winner_id = NULL_STRING;
						}
						try {
							begin_at = playerService.checkNullString(u.getBeginAt().toString());
						} catch (Exception e) {
							begin_at = NULL_STRING;
						}
						try {
							end_at = playerService.checkNullString(u.getEndAt().toString());
						} catch (Exception e) {
							end_at = NULL_STRING;
						}
						try {
							scheduled_at = playerService.checkNullString(u.getScheduledAt());
						} catch (Exception e) {
							scheduled_at = NULL_STRING;
						}
						try {
							saveMatchDetails(Integer.parseInt(match_id), begin_at, end_at, match_type, match_name,
									num_of_games, league_id, series_id, tournament_id, winner_id, videogame, scheduled_at);	
						} catch (Exception e) {
							System.out.println("ERROR getAllDotaMatches " + e.getMessage() );
						}
						

						for (API_OpponentMain g : u.getOpponents()) {
							int opponent_id = 0;
							String acronym = "";
							String opponent_name = "";
							String image_url = "";

//							try {
//								opponent_id = Optional.fromNullable(g.getOpponent().getId()).or(NULL_INT);
//								opponent_name = Optional.fromNullable(g.getOpponent().getName()).or(NULL_STRING);
//								acronym = Optional.fromNullable(g.getOpponent().getAcronym()).or(NULL_STRING);
//								image_url = Optional.fromNullable(g.getOpponent().getImageUrl()).or(NULL_IMAGE);
//								
//							} catch (Exception e) {
//								// TODO: handle exception
//								System.out.println(e.getMessage());
//							}
							try {
								opponent_id = playerService.checkNullInt(g.getOpponent().getId());
							} catch (Exception e) {
								opponent_id = NULL_INT;
							}
							try {
								opponent_name = playerService.checkNullString(g.getOpponent().getName());
							} catch (Exception e) {
								opponent_name = NULL_STRING;
							}
							try {
								acronym = playerService.checkNullString(g.getOpponent().getAcronym());
							} catch (Exception e) {
								acronym = NULL_STRING;
							}
							try {
								image_url = playerService.checkNullImage(g.getOpponent().getImageUrl());
							} catch (Exception e) {
								image_url = NULL_IMAGE;
							}
							try {
								saveMatchOpponentDetails(opponent_id, acronym, opponent_name, image_url, u.getId());
							} catch (Exception e) {
								// TODO: handle exception
								System.out.println("ERROR getAllDotaMatches Opponents " + e.getMessage());
							}

						}

					}
				}
				System.out.println("Saved all Past matches to DB");

			}

			@Override
			public void onFailure(Call<List<API_Match>> call, Throwable t) {
				// TODO Auto-generated method stub
				System.out.println("ERROR: " + t.getMessage());
			}
		});

		return lolMatches;
	}

	public List<API_Match> getAllLoLResults() throws IOException {
		for (int i = 1; i < 10; i++) {
			Call<List<API_Match>> call = service.listAllLoLMatches(API_KEY, 20,
					"2019-10-01T00:00:00Z,2019-11-30T23:59:59Z", "-scheduled_at");
			call.enqueue(new Callback<List<API_Match>>() {
				@Override
				public void onResponse(Call<List<API_Match>> call, Response<List<API_Match>> response) {
					lolMatches = response.body();
					Gson responseGson = new Gson();

					System.out.println("$$$$$$$$$$$$$$ " + response.body().size());
					for (API_Match u : response.body()) {
						if (u.getVideogame().getName().equals("LoL")) {
							if (!u.getResults().isEmpty()) {
								for(API_Result result : u.getResults()) {
									try {
										String composite = result.getTeam_id() + "" + u.getId();
										saveMatchResults(composite, u.getId(),
												result.getTeam_id(), result.getScore());
									} catch (Exception e) {
										System.out.println("DUPLICATE PKs  getAllLoLResults: " + e.getMessage());
									}
								}

							}

						}
					}
					System.out.println("Saved all LoL Results to DB");
				}

				@Override
				public void onFailure(Call<List<API_Match>> call, Throwable t) {
					// TODO Auto-generated method stub
					System.out.println("ERROR: " + t.getMessage());
				}
			});
		}

		return lolMatches;
	}

	public List<API_Match> getAllDotaResults() throws IOException {
		Call<List<API_Match>> call = service.listAllDotaMatches(API_KEY, 20,
				"2019-10-01T00:00:00Z,2019-11-30T23:59:59Z", "-scheduled_at");
		call.enqueue(new Callback<List<API_Match>>() {
			@Override
			public void onResponse(Call<List<API_Match>> call, Response<List<API_Match>> response) {
				lolMatches = response.body();
				Gson responseGson = new Gson();
				int uniqueKey = 0;
				for (API_Match u : response.body()) {
					if (u.getVideogame().getName().equals("Dota 2")) {
						if (!u.getResults().isEmpty()) {
							for(API_Result result : u.getResults()) {
								try {
									String composite = result.getTeam_id() + "" + u.getId();
									saveMatchResults(composite, u.getId(),
											result.getTeam_id(), result.getScore());
								} catch (Exception e) {
									System.out.println("DUPLICATE PKs getAllDotaResults: " + e.getMessage());
								}
							}

						}
					}
				}
				System.out.println("Saved all Dota Results to DB");
			}

			@Override
			public void onFailure(Call<List<API_Match>> call, Throwable t) {
				// TODO Auto-generated method stub
				System.out.println("ERROR: " + t.getMessage());
			}
		});

		return lolMatches;
	}


	public List<API_Match> getMatchesByTournamentId(int tournament_id) throws IOException {
		Call<List<API_Tournament>> call = service.listTournaments(API_KEY);
		call.enqueue(new Callback<List<API_Tournament>>() {
			@Override
			public void onResponse(Call<List<API_Tournament>> call, Response<List<API_Tournament>> response) {

				for (API_Tournament t : response.body()) {
					if (tournament_id == t.getId()) {
						for (API_Match u : t.getMatches()) {
							System.out.println(u.getId());
							String match_id = u.getId().toString();
							String begin_at = "";
							String end_at = "";
							String match_type = u.getMatchType();
							String match_name = u.getName();
							int num_of_games = u.getNumberOfGames();
							int league_id = u.getLeagueId();
							String series_id = u.getSerieId().toString();
							int tournament_id = u.getTournamentId();
							String winner_id = "";
							String videogame = u.getVideogame().getName();
							String scheduled_at = u.getScheduledAt();
//							try {
//								winner_id = Optional.fromNullable(u.getWinnerId().toString()).or(NULL_STRING);
//								begin_at = Optional.fromNullable(u.getBeginAt()).or(NULL_STRING);
//								end_at = Optional.fromNullable(u.getEndAt()).or(NULL_STRING);
//								scheduled_at = Optional.fromNullable(u.getScheduledAt()).or(NULL_STRING);							
//								
//							} catch (Exception e) {
//								System.out.println("ERROR: getMatchesByTournamentId " + e.getMessage());
//							}
							try {							
								winner_id = playerService.checkNullString(u.getWinnerId().toString());
							} catch (Exception e) {							
								winner_id = NULL_STRING;
							}
							try {
								begin_at = playerService.checkNullString(u.getBeginAt().toString());
							} catch (Exception e) {
								begin_at = NULL_STRING;
							}
							try {
								end_at = playerService.checkNullString(u.getEndAt().toString());
							} catch (Exception e) {
								end_at = NULL_STRING;
							}
							try {
								scheduled_at = playerService.checkNullString(u.getScheduledAt());
							} catch (Exception e) {
								scheduled_at = NULL_STRING;
							}
							try {
								saveMatchDetails(Integer.parseInt(match_id), begin_at, end_at, match_type, match_name,
										num_of_games, league_id, series_id, tournament_id, winner_id, videogame,
										scheduled_at);
							} catch (Exception e) {
								System.out.println("ERROR in getMatchesByTournamentId " + e.getMessage());
							}
							
						}
					}
				}

				System.out.println("Saved all matches for " + tournament_id + " to DB");

			}

			@Override
			public void onFailure(Call<List<API_Tournament>> call, Throwable t) {
				// TODO Auto-generated method stub
				System.out.println("ERROR: " + t.getMessage());
			}
		});
		return lolUpcomingMatches;
	}

	public List<Match> getAll() {
		// TODO Auto-generated method stub
		return matchRepo.findAll();
	}

	public List<Map<String, String>> getResultsByMatchId(int match_id) {
		return matchRepo.findResultsByMatchId(match_id);
	}

	public Match saveMatch(Match match) {
		return matchRepo.save(match);
	}

	public Match saveMatchDetails(int match_id, String begin_at, String end_at, String match_type, String match_name,
			int num_of_games, int league_id, String series_id, int tournament_id, String winner_id, String videogame,
			String scheduled_at) {
		Match match = new Match();
		match.setMatch_id(match_id);
		match.setBegin_at(begin_at);
		match.setEnd_at(end_at);
		match.setMatch_type(match_type);
		match.setMatch_name(match_name);
		match.setNum_of_games(num_of_games);
		match.setLeague_id(league_id);
		match.setSeries_id(series_id);
		match.setTournament_id(tournament_id);
		match.setWinner_id(winner_id);
		match.setVideogame(videogame);
		match.setScheduled_at(scheduled_at);
		return matchRepo.save(match);
	}

	public Game saveGame(Game game) {
		return gameRepo.save(game);
	}

	public Game saveGameDetails(int game_id, String begin_at, String end_at, int match_id, int position, String status,
			String winner_id, String videogame) {
		Game game = new Game();
		game.setGame_id(game_id);
		game.setBegin_at(begin_at);
		game.setEnd_at(end_at);
		game.setMatch_id(match_id);
		game.setPosition(position);
		game.setStatus(status);
		game.setWinner_id(winner_id);
		game.setVideogame(videogame);
		return gameRepo.save(game);
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

	public Result saveMatchResults(String id, int match_id, int team_id, int score) {
		Result result = new Result();
		result.setId(id);
		result.setMatch_id(match_id);
		result.setTeam_id(team_id);
		result.setScore(score);
		return resultRepo.save(result);
	}

}
