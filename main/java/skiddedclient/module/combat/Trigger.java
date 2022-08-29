package skiddedclient.module.combat;

import java.util.Objects;

import net.minecraft.util.Hand;
import net.minecraft.util.hit.EntityHitResult;
import skiddedclient.module.Mod;

public class Trigger extends Mod{
	
	public Trigger() {
		super("Trigger", "Automaticly attack the entity you are looking at", Category.COMBAT);
		//this.setKey(GLFW.GLFW_KEY_O);
	}
	
	
	@Override
	public void onTick() {
		if (!(mc.crosshairTarget instanceof EntityHitResult) || Objects.requireNonNull(mc.player)
            	.getAttackCooldownProgress(0) < 1) {
    		return;
    	}
    
    //mc.player.swingHand(Hand.MAIN_HAND);
		if (mc.player.getAttackCooldownProgress(0.5F) == 1) {
    		Objects.requireNonNull(mc.interactionManager)
        	.attackEntity(mc.player, ((EntityHitResult) mc.crosshairTarget).getEntity());
    		mc.player.swingHand(Hand.MAIN_HAND);
    	}
	}
}
