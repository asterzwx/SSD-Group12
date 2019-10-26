package hello.model;

import java.util.List;

import org.springframework.lang.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class API_Match {
	
	
	public API_Match() {
		super();
		// TODO Auto-generated constructor stub
	}
	public API_Match(String beginAt, Boolean detailedStats, Boolean draw, String endAt, Boolean forfeit,
			List<API_Game> games, Integer id, API_League league, Integer leagueId, Object liveUrl, String matchType,
			String modifiedAt, String name, Integer numberOfGames, List<API_OpponentMain> opponents,
			List<Object> results, String scheduledAt, API_Serie serie, Integer serieId, String slug, String status,
			API_Tournament tournament, Integer tournamentId, API_Winner winner, Integer winnerId,
			API_Videogame videogame) {
		super();
		this.beginAt = beginAt;
		this.detailedStats = detailedStats;
		this.draw = draw;
		this.endAt = endAt;
		this.forfeit = forfeit;
		this.games = games;
		this.id = id;
		this.league = league;
		this.leagueId = leagueId;
		this.liveUrl = liveUrl;
		this.matchType = matchType;
		this.modifiedAt = modifiedAt;
		this.name = name;
		this.numberOfGames = numberOfGames;
		this.opponents = opponents;
		this.results = results;
		this.scheduledAt = scheduledAt;
		this.serie = serie;
		this.serieId = serieId;
		this.slug = slug;
		this.status = status;
		this.tournament = tournament;
		this.tournamentId = tournamentId;
		this.winner = winner;
		this.winnerId = winnerId;
		this.videogame = videogame;
	}
	@SerializedName("begin_at")
	@Expose
	public String beginAt;
	@SerializedName("detailed_stats")
	@Expose
	public Boolean detailedStats;
	@SerializedName("draw")
	@Expose
	public Boolean draw;
	@SerializedName("end_at")
	@Expose
	public String endAt;
	@SerializedName("forfeit")
	@Expose
	public Boolean forfeit;
	@SerializedName("games")
	@Expose
	public List<API_Game> games = null;
	@SerializedName("id")
	@Expose
	public Integer id;
	@SerializedName("league")
	@Expose
	public API_League league;
	@SerializedName("league_id")
	@Expose
	public Integer leagueId;
//	@SerializedName("live")
//	@Expose
//	public Live live;
	@SerializedName("live_url")
	@Expose
	public Object liveUrl;
	@SerializedName("match_type")
	@Expose
	public String matchType;
	@SerializedName("modified_at")
	@Expose
	public String modifiedAt;
	@SerializedName("name")
	@Expose
	public String name;
	@SerializedName("number_of_games")
	@Expose
	public Integer numberOfGames;
	@SerializedName("opponents")
	@Expose
	public List<API_OpponentMain> opponents = null;
	@SerializedName("results")
	@Expose
	public List<Object> results = null;
	@SerializedName("scheduled_at")
	@Expose
	public String scheduledAt;
	@SerializedName("serie")
	@Expose
	public API_Serie serie;
	@SerializedName("serie_id")
	@Expose
	public Integer serieId;
	@SerializedName("slug")
	@Expose
	public String slug;
	@SerializedName("status")
	@Expose
	public String status;
	@SerializedName("tournament")
	@Expose
	public API_Tournament tournament;
	@SerializedName("tournament_id")
	@Expose
	public Integer tournamentId;
	@SerializedName("winner")
	@Expose
	public API_Winner winner;
	@SerializedName("winner_id")
	@Expose
	@Nullable
	public Integer winnerId;
	@SerializedName("videogame")
	@Expose
	public API_Videogame videogame;
	
	
	
	public String getBeginAt() {
		return beginAt;
	}
	public void setBeginAt(String beginAt) {
		this.beginAt = beginAt;
	}
	public Boolean getDetailedStats() {
		return detailedStats;
	}
	public void setDetailedStats(Boolean detailedStats) {
		this.detailedStats = detailedStats;
	}
	public Boolean getDraw() {
		return draw;
	}
	public void setDraw(Boolean draw) {
		this.draw = draw;
	}
	public String getEndAt() {
		return endAt;
	}
	public void setEndAt(String endAt) {
		this.endAt = endAt;
	}
	public Boolean getForfeit() {
		return forfeit;
	}
	public void setForfeit(Boolean forfeit) {
		this.forfeit = forfeit;
	}
	public List<API_Game> getGames() {
		return games;
	}
	public void setGames(List<API_Game> games) {
		this.games = games;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public API_League getLeague() {
		return league;
	}
	public void setLeague(API_League league) {
		this.league = league;
	}
	public Integer getLeagueId() {
		return leagueId;
	}
	public void setLeagueId(Integer leagueId) {
		this.leagueId = leagueId;
	}
	public Object getLiveUrl() {
		return liveUrl;
	}
	public void setLiveUrl(Object liveUrl) {
		this.liveUrl = liveUrl;
	}
	public String getMatchType() {
		return matchType;
	}
	public void setMatchType(String matchType) {
		this.matchType = matchType;
	}
	public String getModifiedAt() {
		return modifiedAt;
	}
	public void setModifiedAt(String modifiedAt) {
		this.modifiedAt = modifiedAt;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getNumberOfGames() {
		return numberOfGames;
	}
	public void setNumberOfGames(Integer numberOfGames) {
		this.numberOfGames = numberOfGames;
	}
	public List<API_OpponentMain> getOpponents() {
		return opponents;
	}
	public void setOpponents(List<API_OpponentMain> opponents) {
		this.opponents = opponents;
	}
	public List<Object> getResults() {
		return results;
	}
	public void setResults(List<Object> results) {
		this.results = results;
	}
	public String getScheduledAt() {
		return scheduledAt;
	}
	public void setScheduledAt(String scheduledAt) {
		this.scheduledAt = scheduledAt;
	}
	public API_Serie getSerie() {
		return serie;
	}
	public void setSerie(API_Serie serie) {
		this.serie = serie;
	}
	public Integer getSerieId() {
		return serieId;
	}
	public void setSerieId(Integer serieId) {
		this.serieId = serieId;
	}
	public String getSlug() {
		return slug;
	}
	public void setSlug(String slug) {
		this.slug = slug;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public API_Tournament getTournament() {
		return tournament;
	}
	public void setTournament(API_Tournament tournament) {
		this.tournament = tournament;
	}
	public Integer getTournamentId() {
		return tournamentId;
	}
	public void setTournamentId(Integer tournamentId) {
		this.tournamentId = tournamentId;
	}
	public API_Winner getWinner() {
		return winner;
	}
	public void setWinner(API_Winner winner) {
		this.winner = winner;
	}
	public Integer getWinnerId() {
		return winnerId;
	}
	public void setWinnerId(Integer winnerId) {
		this.winnerId = winnerId;
	}
	public API_Videogame getVideogame() {
		return videogame;
	}
	public void setVideogame(API_Videogame videogame) {
		this.videogame = videogame;
	}
	
	

}
