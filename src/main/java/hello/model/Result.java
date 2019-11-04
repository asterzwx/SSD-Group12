package hello.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.lang.Nullable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "result")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = { "createdAt", "updatedAt" }, allowGetters = true, allowSetters = true)
public class Result implements Serializable {

	public Result() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public Result(String id, int match_id, int team_id, int score) {
		super();
		this.id = id;
		this.match_id = match_id;
		this.team_id = team_id;
		this.score = score;
	}


	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private String id;

	@Column(name = "match_id")
	@Nullable
	private int match_id;

	@Column(name = "team_id")
	@Nullable
	private int team_id;

	@Column(name = "score")
	@Nullable
	private int score;

	public int getMatch_id() {
		return match_id;
	}

	public void setMatch_id(int match_id) {
		this.match_id = match_id;
	}

	public int getTeam_id() {
		return team_id;
	}

	public void setTeam_id(int team_id) {
		this.team_id = team_id;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	

}
