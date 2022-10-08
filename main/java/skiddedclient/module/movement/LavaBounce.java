package skiddedclient.module.movement;

import skiddedclient.module.Mod;

public class LavaBounce extends Mod{

	public LavaBounce() {
		super("LavaBounce", "Lava is now bouncy", Category.MOVEMENT);
	}
	
	@Override
	public void onTick() {
		 if (mc.player.isInLava()) {
	            mc.player.addVelocity(0, 0.3, 0);
	            mc.player.airStrafingSpeed = 0.3f;
	     }
		
		super.onTick();
	}


}
