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
@Table(name = "game")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, 
        allowGetters = true, allowSetters = true)
public class Game implements Serializable{
	
	public Game() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Game(int game_id, String begin_at, String end_at, int match_id, int position, String status,
			String winner_id, String videogame) {
		super();
		this.game_id = game_id;
		this.begin_at = begin_at;
		this.end_at = end_at;
		this.match_id = match_id;
		this.position = position;
		this.status = status;
		this.winner_id = winner_id;
		this.videogame = videogame;
	}

	@Id
	@Column(name = "game_id")
	private int game_id;
	
	@Column(name = "begin_at")
	@Nullable
	private String begin_at;
	
	@Column(name = "end_at")
	@Nullable
	private String end_at;
	
	@Column(name = "match_id")
	@Nullable
	private int match_id;
	
	@Column(name = "position")
	@Nullable
	private int position;
	
	@Column(name = "status")
	@Nullable
	private String status;
	
	@Column(name = "winner_id")
	@Nullable
	private String winner_id;
	
	@Column(name = "videogame")
	@Nullable
	private String videogame;

	public int getGame_id() {
		return game_id;
	}

	public void setGame_id(int game_id) {
		this.game_id = game_id;
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

	public int getMatch_id() {
		return match_id;
	}

	public void setMatch_id(int match_id) {
		this.match_id = match_id;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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
	

}
