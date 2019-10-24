package hello.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "league")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, 
        allowGetters = true, allowSetters = true)

public class League implements Serializable{
	
	public League() {}
	
	public League(int league_id, String league_name, String league_slug, String league_img, Date createdAt,
			Date updatedAt) {
		super();
		this.league_id = league_id;
		this.league_name = league_name;
		this.league_slug = league_slug;
		this.league_img = league_img;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	@Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "league_id")
    private int league_id;
	
	@Column(name = "league_name")
	private String league_name;
	
	@Column(name = "league_slug")
	private String league_slug;
	
	@Column(name = "league_img")
	private String league_img;
	
	@Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updatedAt;

	public int getLeague_id() {
		return league_id;
	}

	public void setLeague_id(int league_id) {
		this.league_id = league_id;
	}

	public String getLeague_name() {
		return league_name;
	}

	public void setLeague_name(String league_name) {
		this.league_name = league_name;
	}

	public String getLeague_slug() {
		return league_slug;
	}

	public void setLeague_slug(String league_slug) {
		this.league_slug = league_slug;
	}

	public String getLeague_img() {
		return league_img;
	}

	public void setLeague_img(String league_img) {
		this.league_img = league_img;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
    
    
    
    
}
