package game.entity;

import java.util.ArrayList;
import java.util.List;

public class Match {
	
	public Match(){
		
	}
	
	public Match(String id){
		this.id = id;
		listOfPlayers = new ArrayList<Player>();
		bestPlayer = "";
		bestStreakPlayer = "";
	}
	
	private String id;
	private List<Player> listOfPlayers;
	private String bestPlayer;
	private String bestStreakPlayer;
	
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @return the listOfPlayers
	 */
	public List<Player> getListOfPlayers() {
		return listOfPlayers;
	}
	/**
	 * @return the bestPlayer
	 */
	public String getBestPlayer() {
		return bestPlayer;
	}
	/**
	 * @param bestPlayer the bestPlayer to set
	 */
	public void setBestPlayer(String bestPlayer) {
		this.bestPlayer = bestPlayer;
	}
	/**
	 * @return the bestStreakPlayer
	 */
	public String getBestStreakPlayer() {
		return bestStreakPlayer;
	}
	/**
	 * @param bestStreakPlayer the bestStreakPlayer to set
	 */
	public void setBestStreakPlayer(String bestStreakPlayer) {
		this.bestStreakPlayer = bestStreakPlayer;
	}
	
}
