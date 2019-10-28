package hello;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import hello.controller.LeagueController;
import hello.controller.MatchController;
import hello.controller.PlayerController;
import hello.controller.SerieController;
import hello.controller.TournamentController;
import hello.controller.UserAccountController;
import hello.repo.LeagueRepo;
import hello.repo.SerieRepo;
import hello.repo.UserAccountRepo;

@Component
public class ScheduledTasks {
	private static final Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);
	private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

//	UserAccountController userAccountController;
//	LeagueRepo leagueRepo;
//	SerieRepo serieRepo;

	SerieController serieController;
	LeagueController leagueController;
	TournamentController tournamentController;
	MatchController matchController;
	PlayerController playerController;

	public ScheduledTasks(SerieController serieController, LeagueController leagueController,
			TournamentController tournamentController, MatchController matchController,
			PlayerController playerController) {
		super();
		this.serieController = serieController;
		this.leagueController = leagueController;
		this.tournamentController = tournamentController;
		this.matchController = matchController;
		this.playerController = playerController;
	}

	@Scheduled(fixedRate = 36000000)
//	@Scheduled(fixedRate = 3000)
//	@Transactional
	public void create() {
		final LocalDateTime start = LocalDateTime.now();
//		userAccountRepo.banUser("ast97", "inactive");
		try {
			serieController.getDotaSeries();
			serieController.getLoLSeries();
			
			leagueController.getLeagues();
			
			tournamentController.getDotaTournaments();
			tournamentController.getLoLPastTournaments();
			tournamentController.getLoLRunningTournaments();
			tournamentController.getLoLUpcomingTournaments();
			
			matchController.getDotaMatches();
			matchController.getLoLPastMatches();
			matchController.getLoLRunningMatches();
			matchController.getLoLUpcomingMatches();
			
			playerController.getDotaPlayers();
			playerController.getLoLPlayers();
			
			System.out.println("\n Functions executed!\n");

			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
}
