package me.limeglass.skrpg.Expressions;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import com.sucy.skill.SkillAPI;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

public class exprHealthScale extends SimpleExpression<Double> {
	
	private Expression<Player> player;
	
	@Override
	public boolean isSingle() {
		return true;
	}

	@Override
	public Class<? extends Double> getReturnType() {
		return Double.class;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		this.player = (Expression<Player>) exprs[0];
		return true;
	}

	@Override
	public String toString(Event e, boolean debug) {
		return "SkillAPI health scale of %player%";
	}

	@Override
	protected Double[] get(Event e) {
		
		Player p = (Player)this.player.getSingle(e);
		
		try {
			
			return new Double[]{ SkillAPI.getPlayerData(p).getMainClass().getData().getHealthScale() };
			
		} catch (NullPointerException ex) {
			
			return new Double[]{ 0.0 };
		}
	}
}
