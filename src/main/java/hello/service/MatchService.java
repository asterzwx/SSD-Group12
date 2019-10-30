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
						for (API_Result r : u.getResults()) {
							try {
								String tempString = u.getId() + "" + r.getTeam_id();
								uniqueKey = Integer.parseInt(tempString);
								saveMatchResults(uniqueKey, u.getId(), r.getTeam_id(), r.getScore());
							} catch (Exception e) {
								// TODO: handle exception
								System.out.println("%%%% " + e.getMessage());
							}

						}

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
						try {
							if (u.getWinnerId().toString().equals(null)) {
								winner_id = "";
							} else {
								winner_id = u.getWinnerId().toString();
							}
							if (u.getBeginAt().toString().equals(null)) {
								begin_at = "";
							} else {
								begin_at = u.getBeginAt();
							}
							if (u.getEndAt().toString().equals(null)) {
								end_at = "";
							} else {
								end_at = u.getEndAt();
							}
						} catch (Exception e) {
							System.out.println("ERROR: " + e.getMessage());
						}
						saveMatchDetails(match_id, begin_at, end_at, match_type, match_name, num_of_games, league_id,
								series_id, tournament_id, winner_id, videogame);

						for (API_OpponentMain g : u.getOpponents()) {
							int opponent_id = 0;
							String acronym = "";
							String opponent_name = "";
							String image_url = "";

							try {
								if (g.getOpponent().getId().toString().equals(null)) {
									opponent_id = 0;
								} else {
									opponent_id = g.getOpponent().getId();
								}
								if (g.getOpponent().getName().equals(null)) {
									opponent_name = "";
								} else {
									opponent_name = g.getOpponent().getName();
								}
								if (g.getOpponent().getAcronym().equals(null)) {
									acronym = "";
								} else {
									acronym = g.getOpponent().getAcronym();
								}
								if (g.getOpponent().getImageUrl().equals(null)) {
									image_url = "";
								} else {
									image_url = g.getOpponent().getImageUrl();
								}
							} catch (Exception e) {
								// TODO: handle exception
								System.out.println(e.getMessage());
							}
							try {
								saveMatchOpponentDetails(opponent_id, acronym, opponent_name, image_url, u.getId());
							} catch (Exception e) {
								// TODO: handle exception
								System.out.println("WTFFFFFFF " + e.getMessage());
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

	public List<API_Match> getAllDotaMatches() throws IOException {
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
						for (API_Result r : u.getResults()) {
							try {
								String tempString = u.getId() + "" + r.getTeam_id();
								uniqueKey = Integer.parseInt(tempString);
								saveMatchResults(uniqueKey, u.getId(), r.getTeam_id(), r.getScore());
							} catch (Exception e) {
								// TODO: handle exception
								System.out.println("%%%% " + e.getMessage());
							}
						}
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
						try {
							if (u.getWinnerId().toString().equals(null)) {
								winner_id = "";
							} else {
								winner_id = u.getWinnerId().toString();
							}
							if (u.getBeginAt().toString().equals(null)) {
								begin_at = "";
							} else {
								begin_at = u.getBeginAt();
							}
							if (u.getEndAt().toString().equals(null)) {
								end_at = "";
							} else {
								end_at = u.getEndAt();
							}
						} catch (Exception e) {
							System.out.println("ERROR: " + e.getMessage());
						}
						saveMatchDetails(match_id, begin_at, end_at, match_type, match_name, num_of_games, league_id,
								series_id, tournament_id, winner_id, videogame);

						for (API_OpponentMain g : u.getOpponents()) {
							int opponent_id = 0;
							String acronym = "";
							String opponent_name = "";
							String image_url = "";

							try {
								if (g.getOpponent().getId().toString().equals(null)) {
									opponent_id = 0;
								} else {
									opponent_id = g.getOpponent().getId();
								}
								if (g.getOpponent().getName().equals(null)) {
									opponent_name = "";
								} else {
									opponent_name = g.getOpponent().getName();
								}
								if (g.getOpponent().getAcronym().equals(null)) {
									acronym = "";
								} else {
									acronym = g.getOpponent().getAcronym();
								}
								if (g.getOpponent().getImageUrl().equals(null)) {
									image_url = "";
								} else {
									image_url = g.getOpponent().getImageUrl();
								}
							} catch (Exception e) {
								// TODO: handle exception
								System.out.println(e.getMessage());
							}
							try {
								saveMatchOpponentDetails(opponent_id, acronym, opponent_name, image_url, u.getId());
							} catch (Exception e) {
								// TODO: handle exception
								System.out.println("WTFFFFFFF " + e.getMessage());
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

//
//	public List<API_Match> getAllRunningMatches() throws IOException {
//		for (int i = 1; i < 5; i++) {
//			Call<List<API_Match>> call = service.listAllRunningMatches(API_KEY, 100, i);
//			call.enqueue(new Callback<List<API_Match>>() {
//				@Override
//				public void onResponse(Call<List<API_Match>> call, Response<List<API_Match>> response) {
//
//					runningMatches = response.body();
//					Gson responseGson = new Gson();
//
//					for (API_Match u : response.body()) {
//						if (u.getVideogame().getName().equals("Dota 2") || u.getVideogame().getName().equals("LoL")) {
//							System.out.println(u.getId());
//							String match_id = u.getId().toString();
//							String begin_at = "";
//							String end_at = "";
//							String match_type = u.getMatchType();
//							String match_name = u.getName();
//							int num_of_games = u.getNumberOfGames();
//							int league_id = u.getLeagueId();
//							String series_id = u.getSerieId().toString();
//							int tournament_id = u.getTournamentId();
//							String winner_id = "";
//							String videogame = u.getVideogame().getName();
//							try {
//								if (u.getWinnerId().toString().equals(null)) {
//									winner_id = "";
//								} else {
//									winner_id = u.getWinnerId().toString();
//								}
//								if (u.getBeginAt().toString().equals(null)) {
//									begin_at = "";
//								} else {
//									begin_at = u.getBeginAt();
//								}
//								if (u.getEndAt().toString().equals(null)) {
//									end_at = "";
//								} else {
//									end_at = u.getEndAt();
//								}
//							} catch (Exception e) {
//								System.out.println("ERROR: " + e.getMessage());
//							}
//							saveMatchDetails(match_id, begin_at, end_at, match_type, match_name, num_of_games,
//									league_id, series_id, tournament_id, winner_id, videogame);
//							for (API_OpponentMain g : u.getOpponents()) {
//								int opponent_id = 0;
//								String acronym = "";
//								String opponent_name = "";
//								String image_url = "";
//
//								try {
//									if (g.getOpponent().getId().toString().equals(null)) {
//										opponent_id = 0;
//									} else {
//										opponent_id = g.getOpponent().getId();
//									}
//									if (g.getOpponent().getName().equals(null)) {
//										opponent_name = "";
//									} else {
//										opponent_name = g.getOpponent().getName();
//									}
//									if (g.getOpponent().getAcronym().equals(null)) {
//										acronym = "";
//									} else {
//										acronym = g.getOpponent().getAcronym();
//									}
//									if (g.getOpponent().getImageUrl().equals(null)) {
//										image_url = "";
//									} else {
//										image_url = g.getOpponent().getImageUrl();
//									}
//								} catch (Exception e) {
//									// TODO: handle exception
//									System.out.println(e.getMessage());
//								}
//								try {
//									saveMatchOpponentDetails(opponent_id, acronym, opponent_name, image_url, u.getId());
//								} catch (Exception e) {
//									// TODO: handle exception
//									System.out.println("WTFFFFFFF " + e.getMessage());
//								}
//							}
//						}
//					}
//					System.out.println("Saved all Running matches to DB");
//
//				}
//
//				@Override
//				public void onFailure(Call<List<API_Match>> call, Throwable t) {
//					// TODO Auto-generated method stub
//					System.out.println("ERROR: " + t.getMessage());
//				}
//			});
//		}
//
//		return runningMatches;
//	}
//
//	public List<API_Match> getAllUpcomingMatches() throws IOException {
//		for (int i = 1; i < 5; i++) {
//			Call<List<API_Match>> call = service.listAllUpcomingMatches(API_KEY, 100, i);
//			call.enqueue(new Callback<List<API_Match>>() {
//				@Override
//				public void onResponse(Call<List<API_Match>> call, Response<List<API_Match>> response) {
//
//					upcomingMatches = response.body();
//					Gson responseGson = new Gson();
//
//					for (API_Match u : response.body()) {
//						if (u.getVideogame().getName().equals("Dota 2") || u.getVideogame().getName().equals("LoL")) {
//							System.out.println(u.getId());
//							String match_id = u.getId().toString();
//							String begin_at = "";
//							String end_at = "";
//							String match_type = u.getMatchType();
//							String match_name = u.getName();
//							int num_of_games = u.getNumberOfGames();
//							int league_id = u.getLeagueId();
//							String series_id = u.getSerieId().toString();
//							int tournament_id = u.getTournamentId();
//							String winner_id = "";
//							String videogame = u.getVideogame().getName();
//							try {
//								if (u.getWinnerId().toString().equals(null)) {
//									winner_id = "";
//								} else {
//									winner_id = u.getWinnerId().toString();
//								}
//								if (u.getBeginAt().toString().equals(null)) {
//									begin_at = "";
//								} else {
//									begin_at = u.getBeginAt();
//								}
//								if (u.getEndAt().toString().equals(null)) {
//									end_at = "";
//								} else {
//									end_at = u.getEndAt();
//								}
//							} catch (Exception e) {
//								System.out.println("ERROR: " + e.getMessage());
//							}
//							saveMatchDetails(match_id, begin_at, end_at, match_type, match_name, num_of_games,
//									league_id, series_id, tournament_id, winner_id, videogame);
//
//							for (API_OpponentMain g : u.getOpponents()) {
//								int opponent_id = 0;
//								String acronym = "";
//								String opponent_name = "";
//								String image_url = "";
//
//								try {
//									if (g.getOpponent().getId().toString().equals(null)) {
//										opponent_id = 0;
//									} else {
//										opponent_id = g.getOpponent().getId();
//									}
//									if (g.getOpponent().getName().equals(null)) {
//										opponent_name = "";
//									} else {
//										opponent_name = g.getOpponent().getName();
//									}
//									if (g.getOpponent().getAcronym().equals(null)) {
//										acronym = "";
//									} else {
//										acronym = g.getOpponent().getAcronym();
//									}
//									if (g.getOpponent().getImageUrl().equals(null)) {
//										image_url = "";
//									} else {
//										image_url = g.getOpponent().getImageUrl();
//									}
//								} catch (Exception e) {
//									// TODO: handle exception
//									System.out.println(e.getMessage());
//								}
//								try {
//									saveMatchOpponentDetails(opponent_id, acronym, opponent_name, image_url, u.getId());
//								} catch (Exception e) {
//									// TODO: handle exception
//									System.out.println("WTFFFFFFF " + e.getMessage());
//								}
//
//							}
//
//						}
//
//					}
//					System.out.println("Saved all Upcoming matches to DB");
//				}
//
//				@Override
//				public void onFailure(Call<List<API_Match>> call, Throwable t) {
//					// TODO Auto-generated method stub
//					System.out.println("ERROR: " + t.getMessage());
//				}
//			});
//		}
//
//		return upcomingMatches;
//	}

//	public List<API_Game> getDotaGamesById(int match_id) throws IOException {
//		Call<List<API_Match>> call = service.listDotaGamesByMatchId(API_KEY);
//		call.enqueue(new Callback<List<API_Match>>() {
//			@Override
//			public void onResponse(Call<List<API_Match>> call, Response<List<API_Match>> response) {
//				dotaMatches = response.body();
//				Gson responseGson = new Gson();
//				String winner_id;
//				String begin_at;
//				String end_at;
//
//				for (API_Match u : response.body()) {
//
//					if (match_id == u.getId()) {
//						System.out.println("@@@ " + u.getVideogame().getName());
////						Game game = new Game();
//						GameService gameService = new GameService();
//						for (API_Game g : u.getGames()) {
//							try {
//
//								if (g.getWinner().getId().toString().equals(null)) {
//									winner_id = "";
//								} else {
//									winner_id = g.getWinner().getId().toString();
//								}
//								if (g.getBeginAt().toString().equals(null)) {
//									begin_at = "";
//								} else {
//									begin_at = g.getBeginAt();
//								}
//								if (g.getEndAt().toString().equals(null)) {
//									end_at = "";
//								} else {
//									end_at = g.getEndAt();
//								}
//							} catch (Exception e) {
//								winner_id = "";
//								begin_at = "";
//								end_at = "";
//							}
//							saveGameDetails(g.getId(), begin_at, end_at, u.getId(), g.getPosition(), g.getStatus(),
//									g.getWinner().getId().toString(), u.getVideogame().getName());
//						}
//					}
//				}
//				System.out.println("Saved Dota games for " + match_id + " to DB");
//			}
//
//			@Override
//			public void onFailure(Call<List<API_Match>> call, Throwable t) {
//				// TODO Auto-generated method stub
//				System.out.println("ERROR: " + t.getMessage());
//			}
//		});
//		return dotaGames;
//	}
//
//	public List<API_Game> getLoLGamesById(int match_id) throws IOException {
//		Call<List<API_Match>> call = service.listLoLGamesByMatchId(API_KEY);
//		call.enqueue(new Callback<List<API_Match>>() {
//			@Override
//			public void onResponse(Call<List<API_Match>> call, Response<List<API_Match>> response) {
//				lolMatches = response.body();
//				Gson responseGson = new Gson();
//				String winner_id;
//				String begin_at;
//				String end_at;
//				for (API_Match u : response.body()) {
//
//					if (match_id == u.getId()) {
//						System.out.println("@@@ " + u.getVideogame().getName());
////						Game game = new Game();
//						GameService gameService = new GameService();
//
//						for (API_Game g : u.getGames()) {
//							try {
//
//								if (g.getWinner().getId().toString().equals(null)) {
//									winner_id = "";
//								} else {
//									winner_id = g.getWinner().getId().toString();
//								}
//								if (g.getBeginAt().toString().equals(null)) {
//									begin_at = "";
//								} else {
//									begin_at = g.getBeginAt();
//								}
//								if (g.getEndAt().toString().equals(null)) {
//									end_at = "";
//								} else {
//									end_at = g.getEndAt();
//								}
//							} catch (Exception e) {
//								winner_id = "";
//								begin_at = "";
//								end_at = "";
//							}
//							saveGameDetails(g.getId(), begin_at, end_at, u.getId(), g.getPosition(), g.getStatus(),
//									winner_id, u.getVideogame().getName());
//						}
//					}
//				}
//				System.out.println("Saved LoL games for " + match_id + " to DB");
//			}
//
//			@Override
//			public void onFailure(Call<List<API_Match>> call, Throwable t) {
//				// TODO Auto-generated method stub
//				System.out.println("ERROR: " + t.getMessage());
//			}
//		});
//		return lolGames;
//	}
//
////	
//
//	public List<API_Match> getLoLPastMatches() throws IOException {
//		Call<List<API_Match>> call = service.listLoLPastMatches(API_KEY);
//		call.enqueue(new Callback<List<API_Match>>() {
//			@Override
//			public void onResponse(Call<List<API_Match>> call, Response<List<API_Match>> response) {
//
//				lolPastMatches = response.body();
//				Gson responseGson = new Gson();
//
//				for (API_Match u : response.body()) {
//					System.out.println(u.getId());
//					String match_id = u.getId().toString();
//					String begin_at = "";
//					String end_at = "";
//					String match_type = u.getMatchType();
//					String match_name = u.getName();
//					int num_of_games = u.getNumberOfGames();
//					int league_id = u.getLeagueId();
//					String series_id = u.getSerieId().toString();
//					int tournament_id = u.getTournamentId();
//					String winner_id = "";
//					String videogame = u.getVideogame().getName();
//					try {
//						if (u.getWinnerId().toString().equals(null)) {
//							winner_id = "";
//						} else {
//							winner_id = u.getWinnerId().toString();
//						}
//						if (u.getBeginAt().toString().equals(null)) {
//							begin_at = "";
//						} else {
//							begin_at = u.getBeginAt();
//						}
//						if (u.getEndAt().toString().equals(null)) {
//							end_at = "";
//						} else {
//							end_at = u.getEndAt();
//						}
//					} catch (Exception e) {
//						System.out.println("ERROR: " + e.getMessage());
//					}
//					saveMatchDetails(match_id, begin_at, end_at, match_type, match_name, num_of_games, league_id,
//							series_id, tournament_id, winner_id, videogame);
//				}
//				System.out.println("Saved all Past LoL matches to DB");
//
//			}
//
//			@Override
//			public void onFailure(Call<List<API_Match>> call, Throwable t) {
//				// TODO Auto-generated method stub
//				System.out.println("ERROR: " + t.getMessage());
//			}
//		});
//		return lolPastMatches;
//	}
//
//	public List<API_Match> getLoLRunningMatches() throws IOException {
//		Call<List<API_Match>> call = service.listLoLRunningMatches(API_KEY);
//		call.enqueue(new Callback<List<API_Match>>() {
//			@Override
//			public void onResponse(Call<List<API_Match>> call, Response<List<API_Match>> response) {
//
//				lolRunningMatches = response.body();
//				Gson responseGson = new Gson();
//				responseGson.toJson(response.body());
//
//				for (API_Match u : response.body()) {
//					System.out.println(u.getId());
//					String match_id = u.getId().toString();
//					String begin_at = "";
//					String end_at = "";
//					String match_type = u.getMatchType();
//					String match_name = u.getName();
//					int num_of_games = u.getNumberOfGames();
//					int league_id = u.getLeagueId();
//					String series_id = u.getSerieId().toString();
//					int tournament_id = u.getTournamentId();
//					String winner_id = "";
//					String videogame = u.getVideogame().getName();
//					try {
//						if (u.getWinnerId().toString().equals(null)) {
//							winner_id = "";
//						} else {
//							winner_id = u.getWinnerId().toString();
//						}
//						if (u.getBeginAt().toString().equals(null)) {
//							begin_at = "";
//						} else {
//							begin_at = u.getBeginAt();
//						}
//						if (u.getEndAt().toString().equals(null)) {
//							end_at = "";
//						} else {
//							end_at = u.getEndAt();
//						}
//					} catch (Exception e) {
//						System.out.println("ERROR: " + e.getMessage());
//					}
//					saveMatchDetails(match_id, begin_at, end_at, match_type, match_name, num_of_games, league_id,
//							series_id, tournament_id, winner_id, videogame);
//				}
//				System.out.println("Saved all Running LoL matches to DB");
//
//			}
//
//			@Override
//			public void onFailure(Call<List<API_Match>> call, Throwable t) {
//				// TODO Auto-generated method stub
//				System.out.println("ERROR: " + t.getMessage());
//			}
//		});
//		return lolRunningMatches;
//	}
//
//	public List<API_Match> getLoLUpcomingMatches() throws IOException {
//		Call<List<API_Match>> call = service.listLoLUpcomingMatches(API_KEY);
//		call.enqueue(new Callback<List<API_Match>>() {
//			@Override
//			public void onResponse(Call<List<API_Match>> call, Response<List<API_Match>> response) {
//
//				lolUpcomingMatches = response.body();
//				Gson responseGson = new Gson();
//
//				for (API_Match u : response.body()) {
//					System.out.println(u.getId());
//					String match_id = u.getId().toString();
//					String begin_at = "";
//					String end_at = "";
//					String match_type = u.getMatchType();
//					String match_name = u.getName();
//					int num_of_games = u.getNumberOfGames();
//					int league_id = u.getLeagueId();
//					String series_id = u.getSerieId().toString();
//					int tournament_id = u.getTournamentId();
//					String winner_id = "";
//					String videogame = u.getVideogame().getName();
//					try {
//						if (u.getWinnerId().toString().equals(null)) {
//							winner_id = "";
//						} else {
//							winner_id = u.getWinnerId().toString();
//						}
//						if (u.getBeginAt().toString().equals(null)) {
//							begin_at = "";
//						} else {
//							begin_at = u.getBeginAt();
//						}
//						if (u.getEndAt().toString().equals(null)) {
//							end_at = "";
//						} else {
//							end_at = u.getEndAt();
//						}
//					} catch (Exception e) {
//						System.out.println("ERROR: " + e.getMessage());
//					}
//					saveMatchDetails(match_id, begin_at, end_at, match_type, match_name, num_of_games, league_id,
//							series_id, tournament_id, winner_id, videogame);
//				}
//				System.out.println("Saved all Upcoming LoL matches to DB");
//
//			}
//
//			@Override
//			public void onFailure(Call<List<API_Match>> call, Throwable t) {
//				// TODO Auto-generated method stub
//				System.out.println("ERROR: " + t.getMessage());
//			}
//		});
//		return lolUpcomingMatches;
//	}

	public List<API_Match> getMatchesByTournamentId(int tournament_id) throws IOException {
		Call<List<API_Tournament>> call = service.listTournaments(API_KEY);
		call.enqueue(new Callback<List<API_Tournament>>() {
			@Override
			public void onResponse(Call<List<API_Tournament>> call, Response<List<API_Tournament>> response) {

//				lolUpcomingMatches = response.body();
//				Gson responseGson = new Gson();

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
							try {
								if (u.getWinnerId().toString().equals(null)) {
									winner_id = "";
								} else {
									winner_id = u.getWinnerId().toString();
								}
								if (u.getBeginAt().toString().equals(null)) {
									begin_at = "";
								} else {
									begin_at = u.getBeginAt();
								}
								if (u.getEndAt().toString().equals(null)) {
									end_at = "";
								} else {
									end_at = u.getEndAt();
								}
							} catch (Exception e) {
								System.out.println("ERROR: " + e.getMessage());
							}
							saveMatchDetails(match_id, begin_at, end_at, match_type, match_name, num_of_games,
									league_id, series_id, tournament_id, winner_id, videogame);
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

	public Match saveMatch(Match match) {
		return matchRepo.save(match);
	}

	public Match saveMatchDetails(String match_id, String begin_at, String end_at, String match_type, String match_name,
			int num_of_games, int league_id, String series_id, int tournament_id, String winner_id, String videogame) {
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

	public Result saveMatchResults(int id, int match_id, int team_id, int score) {
		Result result = new Result();
		result.setId(id);
		result.setMatch_id(match_id);
		result.setTeam_id(team_id);
		result.setScore(score);
		return resultRepo.save(result);
	}

}
