package game;

import game.entity.Match;
import game.entity.Player;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Game {

	public static List<Match> allGames = new ArrayList<Match>();
	
	private static List<Player> allPlayers;
	
	private List<Player> getAllPlayeres(){
		if(allPlayers == null){
			allPlayers = new ArrayList<Player>();
		}
		return allPlayers;
	}
	
	public static void main(String[] args) {
		Game gm = new Game();
		try {
			gm.startFile("resources/example.txt");
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public void startFile(String file) throws Exception{
		if(file == null || file.isEmpty()){
			return;
		}
		
		File gameFile = new File(file);
		if(!gameFile.exists()){
			return;
		}
		
		FileReader fr = new FileReader( gameFile );
		BufferedReader br = new BufferedReader( fr );
		
		boolean roundStart = false;
		Match match = new Match();
		
		while( br.ready() ){
			String line = br.readLine();
			if(!roundStart){
				if(line.indexOf("New match") <= -1){
					continue;
				}
				match = startMatch(line);
				System.out.println("the match "+match.getId() +" started");
				roundStart = true;
			} else if(line.indexOf("has ended") > -1){
				closeMatch(match);
				roundStart = false;
			} else if(line.indexOf("New match") > -1) {
				closeMatch(match);
				match = startMatch(line);
			} else {
				Event.processEvent(line, match, getAllPlayeres());
			}
		}
		
		if(roundStart){
			closeMatch(match);
		}
		
		for(Match m :allGames){
			System.out.println("Match "+m.getId()+"  --->  Killed most: "+m.getBestPlayer()+" Best Strak: "+m.getBestStreakPlayer());
		}
		
		br.close();
		fr.close();
			
	}
	
	private Match startMatch(String line){
		return new Match((line.substring(line.indexOf("New match")+9).trim()).split(" ")[0]);
	}
	
	private void closeMatch(Match m){
		allGames.add(m);
		Event.processFinishMatch(m);
		System.out.println("the match "+m.getId()+" is closed");
	}
	
}
