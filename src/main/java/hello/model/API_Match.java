package hello.model;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class API_Match {
	
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
	public List<Object> games = null;
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
	public Integer winnerId;

}
