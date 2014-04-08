package game.entity;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map.Entry;

public class Score {

	
	public static final BigDecimal SCORE_KILL = new BigDecimal(100);
	public static final BigDecimal SCORE_OVERKILL = new BigDecimal(60);
	
	
	private Long bestStreak = 0l;
	private String bestweapon ="";
	private Long kills = 0l;
	private Long deaths= 0l;
	private BigDecimal score = BigDecimal.ZERO;
	
	private Long roundKill = 0l;
	private HashMap<String, Long> weapons = new HashMap<String, Long>();
	
	/**
	 * @return the bestStreak
	 */
	public Long getBestStreak() {
		return bestStreak;
	}
	/**
	 * @return the bestweapon
	 */
	public String getBestweapon() {
		return bestweapon;
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
	 * @return the score
	 */
	public BigDecimal getScore() {
		return score;
	}
	
	public BigDecimal addKill(String weapon){
		this.kills++;
		this.roundKill++;

		if(weapon!= null && !weapon.isEmpty()){
			if(weapons.containsKey(weapon)){
				weapons.put(weapon, weapons.get(weapon).longValue() + 1);
			} else {
				weapons.put(weapon, 1l);
			}
		}
		
		this.score.add(SCORE_KILL);
		if(this.roundKill.longValue() > 5){
			this.score.add(SCORE_OVERKILL);
			BigDecimal poits = SCORE_KILL;
			return poits.add(SCORE_OVERKILL);
		}
		return SCORE_KILL;
	}
	public void addDeath(){
		this.deaths++;
		if(this.roundKill.longValue() > this.bestStreak.longValue()){
			this.bestStreak = this.roundKill.longValue();
		}
		roundKill = 0l;
	}
	
	public void endScore(){
		if(this.roundKill.longValue() > this.bestStreak.longValue()){
			this.bestStreak = this.roundKill.longValue();
		}
		String weapon = "";
		Long kills = 0l;
		for(Entry<String, Long> entry : weapons.entrySet()) {
		    String key = entry.getKey();
		    Long value = entry.getValue();
		    if(value.longValue() > kills.longValue()){
		    	kills = value.longValue();
		    	weapon = key;
		    }
		}
		this.bestweapon = weapon;
	}
	
}
