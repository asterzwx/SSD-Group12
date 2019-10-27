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
@Table(name = "serie")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = { "createdAt", "updatedAt" }, allowGetters = true, allowSetters = true)
public class Serie implements Serializable {

	public Serie() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Serie(int serie_id, String begin_at, String end_at, int league_id, String winner_id, int year,
			String videogame) {
		super();
		this.serie_id = serie_id;
		this.begin_at = begin_at;
		this.end_at = end_at;
		this.league_id = league_id;
		this.winner_id = winner_id;
		this.year = year;
		this.videogame = videogame;
	}

	@Id
//  @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "serie_id")
	private int serie_id;

	@Column(name = "begin_at")
	@Nullable
	private String begin_at;

	@Column(name = "end_at")
	@Nullable
	private String end_at;

	@Column(name = "league_id")
	@Nullable
	private int league_id;

	@Column(name = "winner_id")
	@Nullable
	private String winner_id;

	@Column(name = "year")
	@Nullable
	private int year;
	
	@Column(name = "videogame")
	@Nullable
	private String videogame;

	public int getSerie_id() {
		return serie_id;
	}

	public void setSerie_id(int serie_id) {
		this.serie_id = serie_id;
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

	public String getWinner_id() {
		return winner_id;
	}

	public void setWinner_id(String winner_id) {
		this.winner_id = winner_id;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getVideogame() {
		return videogame;
	}

	public void setVideogame(String videogame) {
		this.videogame = videogame;
	}

	
	
}
