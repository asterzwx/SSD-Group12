package hello.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.lang.Nullable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@Entity
@Table(name = "matches")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, 
        allowGetters = true, allowSetters = true)
public class Match implements Serializable {	
	
	public Match() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public Match(int match_id, String begin_at, String end_at, String match_type, String match_name, int num_of_games,
			int league_id, String series_id, int tournament_id, String winner_id, String videogame,
			String scheduled_at) {
		super();
		this.match_id = match_id;
		this.begin_at = begin_at;
		this.end_at = end_at;
		this.match_type = match_type;
		this.match_name = match_name;
		this.num_of_games = num_of_games;
		this.league_id = league_id;
		this.series_id = series_id;
		this.tournament_id = tournament_id;
		this.winner_id = winner_id;
		this.videogame = videogame;
		this.scheduled_at = scheduled_at;
	}



	@Id
	@Column(name = "match_id")
	private int match_id;
	
	@Column(name = "begin_at")
	@Nullable
	private String begin_at;
	
	@Column(name = "end_at")
	@Nullable
	private String end_at;
	
	@Column(name = "match_type")
	private String match_type;
	
	@Column(name = "match_name")
	private String match_name;
	
	@Column(name = "num_of_games")
	private int num_of_games;
	
	@Column(name = "league_id")
	private int league_id;
	
	@Column(name = "series_id")
	private String series_id;
	
	@Column(name = "tournament_id")
	private int tournament_id;
	
	@Column(name = "winner_id")
	@Nullable
	private String winner_id;
	
	@Column(name = "videogame")
	private String videogame;
	

	@Column(name = "scheduled_at")
	@Nullable
	private String scheduled_at;

	public int getMatch_id() {
		return match_id;
	}

	public void setMatch_id(int match_id) {
		this.match_id = match_id;
	}

	public String getBegin_at() {
		return begin_at;
	}

	public void setBegin_at(String begin_at) {
		this.begin_at = begin_at;
	}

	public String getEnd_at() {
		return end_at;
	}

	public void setEnd_at(String end_at) {
		this.end_at = end_at;
	}

	public String getMatch_type() {
		return match_type;
	}

	public void setMatch_type(String match_type) {
		this.match_type = match_type;
	}

	public String getMatch_name() {
		return match_name;
	}

	public void setMatch_name(String match_name) {
		this.match_name = match_name;
	}

	public int getNum_of_games() {
		return num_of_games;
	}

	public void setNum_of_games(int num_of_games) {
		this.num_of_games = num_of_games;
	}

	public int getLeague_id() {
		return league_id;
	}

	public void setLeague_id(int league_id) {
		this.league_id = league_id;
	}

	public String getSeries_id() {
		return series_id;
	}

	public void setSeries_id(String series_id) {
		this.series_id = series_id;
	}

	public int getTournament_id() {
		return tournament_id;
	}

	public void setTournament_id(int tournament_id) {
		this.tournament_id = tournament_id;
	}

	public String getWinner_id() {
		return winner_id;
	}

	public void setWinner_id(String winner_id) {
		this.winner_id = winner_id;
	}

	public String getVideogame() {
		return videogame;
	}

	public void setVideogame(String videogame) {
		this.videogame = videogame;
	}

	public String getScheduled_at() {
		return scheduled_at;
	}

	public void setScheduled_at(String scheduled_at) {
		this.scheduled_at = scheduled_at;
	}
	
	

}
