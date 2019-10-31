package hello.repo;

import java.util.List;

import hello.model.UserAccount;

public interface MatchView {

	int getMatch_id();

	String getBegin_at();

	String getMatch_name();

	ResultView getResultView();
	OpponentView getOpponentView();

	public interface ResultView {

		int getScore();

		int getTeam_id();

	}

	public interface OpponentView {

		int getId();

		String getAcronym();

	}

}
