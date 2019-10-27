package hello.service;

import java.io.IOException;
import java.util.List;

import javax.validation.constraints.Null;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import hello.APIConfiguration;
import hello.RepositoryInterface;
import hello.model.API_League;
import hello.model.API_Opponent;
import hello.model.API_Tournament;
import hello.model.League;
import hello.model.Tournament;
import hello.repo.DotaLeagueRepo;
import hello.repo.TournamentRepo;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Service
public class TournamentService implements APIConfiguration {
	@Autowired
	TournamentRepo tournamentRepo;
	private RepositoryInterface service;
	List<API_Tournament> tournaments = null;

	public TournamentService() {
		Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api.pandascore.co/")
				.addConverterFactory(GsonConverterFactory.create()).build();
		service = retrofit.create(RepositoryInterface.class);
	}

	public List<API_Tournament> getDotaTournaments() throws IOException {
		Call<List<API_Tournament>> call = service.listDotaTournaments(API_KEY);
		call.enqueue(new Callback<List<API_Tournament>>() {
			@Override
			public void onResponse(Call<List<API_Tournament>> call, Response<List<API_Tournament>> response) {

				tournaments = response.body();
				Gson responseGson = new Gson();
				responseGson.toJson(response.body());

//				if (getAll().size() == 0) {
					for (API_Tournament u : response.body()) {
						int id = u.getId();
						String name = u.getName();
						String begin_at = u.getBeginAt();
						String end_at = "";
						int league_id = u.getLeagueId();
						String series_id = u.getSerieId().toString();
						String winner_id = "";
						String videogame = u.getVideogame().getName();

						try {
							if (u.getWinnerId().toString().equals(null)) {
								winner_id = "";
							} else {
								winner_id = u.getWinnerId().toString();
							}
							if (u.getEndAt().toString().equals(null)) {
								end_at = "";
							} else {
								end_at = u.getEndAt();
							}
						} catch (Exception e) {
							System.out.println("ERROR: " + e.getMessage());
						}
						saveTournamentDetails(id, name, begin_at, end_at, league_id, series_id, winner_id, videogame);
					}
					System.out.println("Saved all tournament details to DB");
//				}
			}

			@Override
			public void onFailure(Call<List<API_Tournament>> call, Throwable t) {
				// TODO Auto-generated method stub
				System.out.println("ERROR: " + t.getMessage());
			}
		});
		return tournaments;
	}

	public List<API_Tournament> getLoLPastTournaments() throws IOException {
		Call<List<API_Tournament>> call = service.listLoLPastTournaments(API_KEY);
		call.enqueue(new Callback<List<API_Tournament>>() {
			@Override
			public void onResponse(Call<List<API_Tournament>> call, Response<List<API_Tournament>> response) {

				tournaments = response.body();
				Gson responseGson = new Gson();
				responseGson.toJson(response.body());

				for (API_Tournament u : response.body()) {
					int id = u.getId();
					String name = u.getName();
					String begin_at = u.getBeginAt();
					String end_at = "";
					int league_id = u.getLeagueId();
					String series_id = u.getSerieId().toString();
					String winner_id = "";
					String videogame = u.getVideogame().getName();

					try {
						if (u.getWinnerId().toString().equals(null)) {
							winner_id = "";
						} else {
							winner_id = u.getWinnerId().toString();
						}
						if (u.getEndAt().toString().equals(null)) {
							end_at = "";
						} else {
							end_at = u.getEndAt();
						}
					} catch (Exception e) {
						System.out.println("ERROR: " + e.getMessage());
					}
					saveTournamentDetails(id, name, begin_at, end_at, league_id, series_id, winner_id, videogame);
				}
				System.out.println("Saved all tournament details to DB");

			}

			@Override
			public void onFailure(Call<List<API_Tournament>> call, Throwable t) {
				// TODO Auto-generated method stub
				System.out.println("ERROR: " + t.getMessage());
			}
		});
		return tournaments;
	}

	public List<API_Tournament> getLoLRunningTournaments() throws IOException {
		Call<List<API_Tournament>> call = service.listLoLRunningTournaments(API_KEY);
		call.enqueue(new Callback<List<API_Tournament>>() {
			@Override
			public void onResponse(Call<List<API_Tournament>> call, Response<List<API_Tournament>> response) {

				tournaments = response.body();
				Gson responseGson = new Gson();
				responseGson.toJson(response.body());

//				if (getAll().size() == 0) {
					for (API_Tournament u : response.body()) {
						int id = u.getId();
						String name = u.getName();
						String begin_at = u.getBeginAt();
						String end_at = "";
						int league_id = u.getLeagueId();
						String series_id = u.getSerieId().toString();
						String winner_id = "";
						String videogame = u.getVideogame().getName();

						try {
							if (u.getWinnerId().toString().equals(null)) {
								winner_id = "";
							} else {
								winner_id = u.getWinnerId().toString();
							}
							if (u.getEndAt().toString().equals(null)) {
								end_at = "";
							} else {
								end_at = u.getEndAt();
							}
						} catch (Exception e) {
							System.out.println("ERROR: " + e.getMessage());
						}
						saveTournamentDetails(id, name, begin_at, end_at, league_id, series_id, winner_id, videogame);
					}
					System.out.println("Saved all tournament details to DB");
//				}
			}

			@Override
			public void onFailure(Call<List<API_Tournament>> call, Throwable t) {
				// TODO Auto-generated method stub
				System.out.println("ERROR: " + t.getMessage());
			}
		});
		return tournaments;
	}

	public List<API_Tournament> getLoLUpcomingTournaments() throws IOException {
		Call<List<API_Tournament>> call = service.listLoLUpcomingTournaments(API_KEY);
		call.enqueue(new Callback<List<API_Tournament>>() {
			@Override
			public void onResponse(Call<List<API_Tournament>> call, Response<List<API_Tournament>> response) {

				tournaments = response.body();
				Gson responseGson = new Gson();
				responseGson.toJson(response.body());

//				if (getAll().size() == 0) {
					for (API_Tournament u : response.body()) {
						int id = u.getId();
						String name = u.getName();
						String begin_at = u.getBeginAt();
						String end_at = "";
						int league_id = u.getLeagueId();
						String series_id = u.getSerieId().toString();
						String winner_id = "";
						String videogame = u.getVideogame().getName();

						try {
							if (u.getWinnerId().toString().equals(null)) {
								winner_id = "";
							} else {
								winner_id = u.getWinnerId().toString();
							}
							if (u.getEndAt().toString().equals(null)) {
								end_at = "";
							} else {
								end_at = u.getEndAt();
							}
						} catch (Exception e) {
							System.out.println("ERROR: " + e.getMessage());
						}
						saveTournamentDetails(id, name, begin_at, end_at, league_id, series_id, winner_id, videogame);
					}
					System.out.println("Saved all tournament details to DB");
//				}
			}

			@Override
			public void onFailure(Call<List<API_Tournament>> call, Throwable t) {
				// TODO Auto-generated method stub
				System.out.println("ERROR: " + t.getMessage());
			}
		});
		return tournaments;
	}

	public List<Tournament> getAll() {
		// TODO Auto-generated method stub
		return tournamentRepo.findAll();
	}

	public Tournament saveTournament(Tournament tournament) {
		return tournamentRepo.save(tournament);
	}

	public Tournament saveTournamentDetails(int id, String name, String begin_at, String end_at, int league_id,
			String series_id, String winner_id, String videogame) {
		Tournament tournament = new Tournament();
		tournament.setTournament_id(id);
		tournament.setTournament_name(name);
		tournament.setBegin_at(begin_at);
		tournament.setEnd_at(end_at);
		tournament.setLeague_id(league_id);
		tournament.setSeries_id(series_id);
		tournament.setWinner_id(winner_id);
		tournament.setVideogame(videogame);
		return tournamentRepo.save(tournament);
	}

}
