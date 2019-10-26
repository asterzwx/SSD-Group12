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
import hello.model.API_Serie;
import hello.model.API_Serie;
import hello.model.API_Tournament;
import hello.model.Game;
import hello.model.League;
import hello.model.Match;
import hello.model.Serie;
import hello.model.Tournament;
import hello.repo.DotaLeagueRepo;
import hello.repo.GameRepo;
import hello.repo.MatchRepo;
import hello.repo.SerieRepo;
import hello.repo.TournamentRepo;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Service
public class SerieService implements APIConfiguration {
	@Autowired
	SerieRepo serieRepo;
	
	private RepositoryInterface service;
	List<API_Serie> series = null;
	

	public SerieService() {
		Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api.pandascore.co/")
				.addConverterFactory(GsonConverterFactory.create()).build();
		service = retrofit.create(RepositoryInterface.class);
	}

	public List<API_Serie> getDotaSeries() throws IOException {
		Call<List<API_Serie>> call = service.listDotaSeries(API_KEY);
		call.enqueue(new Callback<List<API_Serie>>() {
			@Override
			public void onResponse(Call<List<API_Serie>> call, Response<List<API_Serie>> response) {

				series = response.body();
				Gson responseGson = new Gson();
				String winner_id = "";
				String begin_at = "";
				String end_at = "";				
				
				for (API_Serie u : response.body()) {
					System.out.println(u.getId());
					int serie_id = u.getId();					
					int league_id = u.getLeagueId();
					int year = u.getYear();
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
					saveSerieDetails(serie_id, begin_at, end_at, league_id, winner_id, year);
				}
				System.out.println("Saved all Dota series to DB");

			}

			@Override
			public void onFailure(Call<List<API_Serie>> call, Throwable t) {
				// TODO Auto-generated method stub
				System.out.println("ERROR: " + t.getMessage());
			}
		});
		return series;
	}
	
	public List<Serie> getAll() {
		// TODO Auto-generated method stub
		return serieRepo.findAll();
	}

	public Serie saveSerie(Serie serie) {
		return serieRepo.save(serie);
	}
	
	public Serie saveSerieDetails(int serie_id, String begin_at, String end_at,
			int league_id, String winner_id, int year) {
		Serie serie = new Serie();
		serie.setSerie_id(serie_id);
		serie.setBegin_at(begin_at);
		serie.setEnd_at(end_at);
		serie.setLeague_id(league_id);
		serie.setWinner_id(winner_id);
		serie.setYear(year);
		return serieRepo.save(serie);
	}


}
