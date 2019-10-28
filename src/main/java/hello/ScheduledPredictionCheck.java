package hello;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import hello.controller.LeagueController;
import hello.controller.MatchController;
import hello.controller.SerieController;
import hello.controller.TournamentController;
import hello.controller.UserAccountController;
import hello.controller.UserPredictionController;
import hello.model.API_Match;
import hello.model.UserInventory;
import hello.model.UserPrediction;
import hello.repo.LeagueRepo;
import hello.repo.SerieRepo;
import hello.repo.UserAccountRepo;
import hello.repo.UserInventoryRepo;
import hello.repo.UserPredictionRepo;

@Component
public class ScheduledPredictionCheck {
	private static final Logger logger = LoggerFactory.getLogger(ScheduledPredictionCheck.class);
	private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

	MatchController matchController;

	UserPredictionController userPredictionController;
	UserPredictionRepo userPredictionRepo;
	UserInventoryRepo userInventoryRepo;

	boolean resultUpdated = false;

	public ScheduledPredictionCheck(MatchController matchController, UserPredictionController userPredictionController,
			UserPredictionRepo userPredictionRepo, UserInventoryRepo userInventoryRepo) {
		super();
		this.matchController = matchController;
		this.userPredictionController = userPredictionController;
		this.userPredictionRepo = userPredictionRepo;
		this.userInventoryRepo = userInventoryRepo;
	}

	List<String> usernameList = new ArrayList<String>();
	int matchid;
	int points;

//	@Scheduled(fixedRate = 1200000)
	@Scheduled(fixedRate = 3000)
	@Transactional
	public void create() {
		final LocalDateTime start = LocalDateTime.now();
		try {
			// retrieve all match id from db userprediction
			List<UserPrediction> predictions = userPredictionController.getAllUserPredictions();
			List<API_Match> pastLoLMatches = matchController.getLoLPastMatches();
			for (int i = 0; i < predictions.size(); i++) {
				// retrieve all match id from api
				for (int j = 0; j < pastLoLMatches.size(); j++) {
					if (predictions.get(i).getMatch_id() == pastLoLMatches.get(j).getId()) {
						System.out.println("### MATCH_ID: " + pastLoLMatches.get(j).getId());
						// see if winner id in api is null or not
						// if not null, update the db's result
						if(!pastLoLMatches.get(j).getWinnerId().equals(null)) {
							System.out.println("####### RESULT: " + pastLoLMatches.get(j).getWinnerId());
							userPredictionRepo.updatePredictionResult(
									pastLoLMatches.get(j).getWinnerId(), 
									pastLoLMatches.get(j).getId());
							resultUpdated = true;
							matchid = pastLoLMatches.get(j).getId();
							//and award points
							// get list of usernames where match id equals the above
							// check if these users' result == prediction and points_allocated = 0
							// if yes, then award points
							for(UserPrediction p : userPredictionRepo.getUsernamesByMatchId(matchid)) {
								if(p.getMatch_id() == matchid) {
									System.out.println("@@@@@@@@@@@@@ " + p.getUsername());
									if(p.getPrediction() == p.getResult() && 
											p.isPoints_allocated() == false) {										
										//get everyone's current points from userinventory table
										for(UserInventory u : userInventoryRepo.getUsernamesById(p.getUsername())) {
											points = u.getPoints();
										}
										//update everyone's points in userinventory table
										userInventoryRepo.updateUserPoints(points+1, p.getUsername());
										//update points_allocated to be 1
										userPredictionRepo.updatePointsAllocatedForMatch(matchid);
									}
								}
								
							}
						}
						
					}

				}

			}
//			
//			if(resultUpdated == true) {
//				
//				
//				
//			}
//			

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}



	}

}
