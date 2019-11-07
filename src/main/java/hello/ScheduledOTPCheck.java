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
import hello.controller.TeamController;
import hello.controller.TournamentController;
import hello.controller.UserAccountController;
import hello.repo.LeagueRepo;
import hello.repo.SerieRepo;
import hello.repo.UserAccountRepo;

@Component
public class ScheduledOTPCheck {
	private static final Logger logger = LoggerFactory.getLogger(ScheduledOTPCheck.class);
	private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

//	UserAccountController userAccountController;
//	LeagueRepo leagueRepo;
//	SerieRepo serieRepo;

	SerieController serieController;
	LeagueController leagueController;
	TournamentController tournamentController;
	MatchController matchController;
	PlayerController playerController;
	TeamController teamController;

	public ScheduledOTPCheck(SerieController serieController, LeagueController leagueController,
			TournamentController tournamentController, MatchController matchController,
			PlayerController playerController, TeamController teamController) {
		super();
		this.serieController = serieController;
		this.leagueController = leagueController;
		this.tournamentController = tournamentController;
		this.matchController = matchController;
		this.playerController = playerController;
		this.teamController = teamController;
	}

	@Scheduled(fixedRate = 3000)
//	@Scheduled(fixedRate = 3000)
//	@Transactional
	public void create() {
		final LocalDateTime start = LocalDateTime.now();
//		userAccountRepo.banUser("ast97", "inactive");
//		try {
//			//check current user's otp_count every 30 secs, if request more than 3 times then log out
//			//meaning, change the user status to locked
//			
//			
//			System.out.println("\n OTP check started... \n");
//
//			
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}		
	}
}
