package hello.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.micrometer.core.lang.Nullable;

@Entity
@Table(name = "tournament")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, 
        allowGetters = true, allowSetters = true)
public class Tournament implements Serializable {
	
	public Tournament() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Tournament(int tournament_id, String tournament_name, String begin_at, String end_at, int league_id,
			String series_id, String winner_id, String type) {
		super();
		this.tournament_id = tournament_id;
		this.tournament_name = tournament_name;
		this.begin_at = begin_at;
		this.end_at = end_at;
		this.league_id = league_id;
		this.series_id = series_id;
		this.winner_id = winner_id;
		this.type = type;
	}

	@Id
//  @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "tournament_id")
	private int tournament_id;

	@Column(name = "tournament_name")
	private String tournament_name;
	
	@Column(name = "begin_at")
	@Nullable
	private String begin_at;
	
	@Column(name = "end_at")
	@Nullable
	private String end_at;
	
	@Column(name = "league_id")
	private int league_id;
	
	@Column(name = "series_id")
	private String series_id;
	
	@Column(name = "winner_id")
	@Nullable
	private String winner_id;
	
	@Column(name = "type")
	@Nullable
	private String type;

	public int getTournament_id() {
		return tournament_id;
	}

	public void setTournament_id(int tournament_id) {
		this.tournament_id = tournament_id;
	}

	public String getTournament_name() {
		return tournament_name;
	}

	public void setTournament_name(String tournament_name) {
		this.tournament_name = tournament_name;
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

	public String getWinner_id() {
		return winner_id;
	}

	public void setWinner_id(String winner_id) {
		this.winner_id = winner_id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	

}
