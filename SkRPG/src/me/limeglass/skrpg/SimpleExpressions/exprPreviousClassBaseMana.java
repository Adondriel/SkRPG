package me.limeglass.skrpg.SimpleExpressions;

import org.bukkit.event.Event;

import com.sucy.skill.api.event.PlayerClassChangeEvent;

import ch.njol.skript.ScriptLoader;
import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.skript.log.ErrorQuality;
import ch.njol.skript.registrations.Classes;
import ch.njol.util.Kleenean;

public class exprPreviousClassBaseMana extends SimpleExpression<Double> {
	
	@Override
	public boolean init(final Expression<?>[] vars, final int matchedPattern, final Kleenean isDelayed, final ParseResult parser) {
		if (!ScriptLoader.isCurrentEvent(PlayerClassChangeEvent.class)) {
			Skript.error("Cannot use 'SkillAPI previous class base mana' outside of a PlayerClassChangeEvent event", ErrorQuality.SEMANTIC_ERROR);
			return false;
		}
		return true;
	}
	
	@Override
	protected Double[] get(final Event e) {
		return new Double[] {getPreviousClass(e)};
	}
	
	private static Double getPreviousClass(final Event e) {
		if (e == null)
			return null;
		if (e instanceof PlayerClassChangeEvent) {
			
			final Object o = ((PlayerClassChangeEvent) e).getPreviousClass().getBaseMana();
			
			return (Double) o;
		}
		return null;
	}
	
	@Override
	public Class<? extends Double> getReturnType() {
		return Double.class;
	}
	
	@Override
	public String toString(final Event e, final boolean debug) {
		if (e == null)
			return "SkillAPI previous class base mana";
		return Classes.getDebugMessage(getSingle(e));
	}
	
	@Override
	public boolean isSingle() {
		return true;
	}
	
}