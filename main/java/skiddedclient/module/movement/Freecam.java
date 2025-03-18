package skiddedclient.module.movement;

import net.minecraft.world.GameMode;
import skiddedclient.module.Mod;

public class Freecam extends Mod{

	public Freecam() {
		super("Freecam", "Freemcam", Category.MOVEMENT);
	}
	
	@Override
	public void onTick() {
		mc.player.getAbilities().allowFlying = true;
		mc.player.setInvisible(true);
		 mc.interactionManager.setGameMode(GameMode.SPECTATOR);
		super.onTick();
	}
	
	@Override
	public void onDisable() {
		 mc.interactionManager.setGameMode(GameMode.SURVIVAL);
		 mc.player.setInvisible(false);
		super.onDisable();
	}

}
