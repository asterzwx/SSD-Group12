package hello.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.lang.Nullable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "opponent")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, 
        allowGetters = true, allowSetters = true)
public class Opponent {
	
	public Opponent() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Opponent(int id, String acronym, String name, String image_url, int match_id, int tournament_id) {
		super();
		this.id = id;
		this.acronym = acronym;
		this.name = name;
		this.image_url = image_url;
		this.match_id = match_id;
		this.tournament_id = tournament_id;
	}

	@Id
	@Column(name = "id")
	private int id;
	
	@Column(name = "acronym")
	@Nullable
	private String acronym;
	
	@Column(name = "name")
	@Nullable
	private String name;
	
	@Column(name = "image_url")
	@Nullable
	private String image_url;
	
	@Column(name = "match_id")
	@Nullable
	private int match_id;
	
	@Column(name = "tournament_id")
	@Nullable
	private int tournament_id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAcronym() {
		return acronym;
	}

	public void setAcronym(String acronym) {
		this.acronym = acronym;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImage_url() {
		return image_url;
	}

	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}

	public int getMatch_id() {
		return match_id;
	}

	public void setMatch_id(int match_id) {
		this.match_id = match_id;
	}

	public int getTournament_id() {
		return tournament_id;
	}

	public void setTournament_id(int tournament_id) {
		this.tournament_id = tournament_id;
	}


	
	

}
