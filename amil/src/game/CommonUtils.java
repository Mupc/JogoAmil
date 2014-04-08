package game;

import java.util.List;

import game.entity.Player;

public class CommonUtils {

	public static Player conteinsPlayer(String id, List<Player> list){
		for(Player p : list){
			if(p.equals(id)){
				return p;
			}
		}
		return null;
	}
}
