package skiddedclient.module.movement;

import skiddedclient.module.Mod;

public class Jesus extends Mod{

	//int yPose = (int) mc.player.getPos().y;
	
	public Jesus() {
		super("Jesus", "Basic jesus hack", Category.MOVEMENT);
	}
	
	@Override
	public void onTick() {
		if (mc.player.isTouchingWater()) {
			mc.player.airStrafingSpeed = (float) (0.5);
			mc.player.setVelocity(0, 0.1, 0);
		}
		super.onTick();
	}

}
