package game.entity;
import java.math.BigDecimal;
import java.util.HashMap;


public class Player {
	
	public Player(String name){
		this.name = name;
		score = BigDecimal.ZERO;
		kills = 0l;
		deaths = 0l;
		games =  new HashMap<String, Score>();
	}
	
	private String name;
	private BigDecimal score;
	private Long kills;
	private Long deaths;
	private HashMap<String, Score> games;
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @return the score
	 */
	public BigDecimal getScore() {
		return score;
	}
	/**
	 * @return the kills
	 */
	public Long getKills() {
		return kills;
	}
	/**
	 * @return the deaths
	 */
	public Long getDeaths() {
		return deaths;
	}
	/**
	 * @return the games
	 */
	public HashMap<String, Score> getGames() {
		return games;
	}
	
	@Override
    public boolean equals(Object object)
    {
        boolean sameSame = false;

        if (object != null && object instanceof String)
        {
            sameSame = this.name.equals((String) object);
        } else if (object != null && object instanceof Player && (Player) object != null){
        	sameSame = this.name.equals(((Player) object).getName());
        }

        return sameSame;
    }
	
	public void addKill(String game, String weapon){
		this.kills++;
		Score score = null;
		if(games.containsKey(game)){
			score = games.get(game);
		} else {
			score = new Score();
			games.put(game, score);
		}
		this.score.add(score.addKill(weapon));
	}
	public void addDeath(String game){
		this.deaths++;
		Score score = null;
		if(games.containsKey(game)){
			score = games.get(game);
		} else {
			score = new Score();
			games.put(game, score);
		}
		score.addDeath();
	}
	
	
}
