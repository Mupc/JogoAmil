package game;

import java.util.List;

import game.entity.Match;
import game.entity.Player;
import game.entity.Score;

public class Event {

	public static void processFinishMatch(Match match){
		
		Long killPlayer = 0l;
		Long streakill = 0l;
		String bestPlayer = "";
		String streakPlayer = "";
		
		for(Player p : match.getListOfPlayers()){
			Score s = p.getGames().get(match.getId());
			s.endScore();
			if(s.getKills().longValue() > killPlayer.longValue()){
				killPlayer = s.getKills().longValue();
				bestPlayer = p.getName();
			}
			if(s.getBestStreak().longValue() > streakill.longValue()){
				streakill = s.getBestStreak().longValue();
				streakPlayer = p.getName();
			}
		}
		if(bestPlayer.isEmpty()){
			bestPlayer = "No Score";
		}
		if(streakPlayer.isEmpty()){
			streakPlayer = "No Score";
		}
		match.setBestPlayer(bestPlayer);
		match.setBestStreakPlayer(streakPlayer);
		
	}
	
	public static void processEvent(String line, Match match, List<Player> allPlayers){
		if(line.indexOf("<WORLD>") > -1){
			precessOnlyDeath(line, match, allPlayers);
		} else {
			precessKill(line, match, allPlayers);
		}
	}
	
	private static void precessKill(String line, Match match, List<Player> allPlayers){
		//23/04/2013 15:36:04 - Roman killed Nick using M16
		String userKilled = line.substring(line.indexOf("killed")+6).trim().split(" ")[0];
		String user = line.substring(line.indexOf("-")+1).trim().split(" ")[0];
		String weapon = line.substring(line.indexOf("using")+5).trim().split(" ")[0];
		
		Player p = CommonUtils.conteinsPlayer(userKilled, match.getListOfPlayers());
		if(p != null){
			p.addDeath(match.getId());
		} else { 
			p = CommonUtils.conteinsPlayer(userKilled, allPlayers);
			if(p != null){
				match.getListOfPlayers().add(p);
				p.addDeath(match.getId());
			} else {
				p = new Player(userKilled);
				allPlayers.add(p);
				match.getListOfPlayers().add(p);
				p.addDeath(match.getId());
			}
		}
		
		p = CommonUtils.conteinsPlayer(user, match.getListOfPlayers());
		if(p != null){
			p.addKill(match.getId(), weapon);
		} else {
			p = CommonUtils.conteinsPlayer(user, allPlayers);
			if(p != null){
				match.getListOfPlayers().add(p);
				p.addKill(match.getId(), weapon);
			} else {
				p = new Player(user);
				allPlayers.add(p);
				match.getListOfPlayers().add(p);
				p.addKill(match.getId(), weapon);
			}
		}
		
	}
	
	private static void precessOnlyDeath(String line, Match match, List<Player> allPlayers){
		//23/04/2013 15:36:33 - <WORLD> killed Nick by DROWN
		String userId = line.substring(line.indexOf("killed")+6).trim().split(" ")[0];
		Player p = CommonUtils.conteinsPlayer(userId, match.getListOfPlayers());
		if(p != null){
			p.addDeath(match.getId());
		} else {
			p = CommonUtils.conteinsPlayer(userId, allPlayers);
			if(p != null){
				match.getListOfPlayers().add(p);
				p.addDeath(match.getId());
			} else {
				p = new Player(userId);
				allPlayers.add(p);
				match.getListOfPlayers().add(p);
				p.addDeath(match.getId());
			}
		}
	}
	
	
}
