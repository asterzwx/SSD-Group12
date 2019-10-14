package hello.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Poll {
	
	public Poll() {}
	
	
	public Poll(int poll_id, int match_id, Date match_datetime, String status) {
		super();
		this.poll_id = poll_id;
		this.match_id = match_id;
		this.match_datetime = match_datetime;
		this.status = status;
	}


	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name = "item_id", unique = true)
	@Column(name = "poll_id")
	private int poll_id;

	@Column(name = "match_id")
	private int match_id;
	
	@Column(name = "match_datetime")
	private Date match_datetime;
	
	@Column(name = "status")
	private String status;

	public int getPoll_id() {
		return poll_id;
	}

	public void setPoll_id(int poll_id) {
		this.poll_id = poll_id;
	}

	public int getMatch_id() {
		return match_id;
	}

	public void setMatch_id(int match_id) {
		this.match_id = match_id;
	}

	public Date getMatch_datetime() {
		return match_datetime;
	}

	public void setMatch_datetime(Date match_datetime) {
		this.match_datetime = match_datetime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	

}
