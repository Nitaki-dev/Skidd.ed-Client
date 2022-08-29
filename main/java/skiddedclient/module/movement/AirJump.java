package skiddedclient.module.movement;

import skiddedclient.module.Mod;

public class AirJump extends Mod {

	public AirJump() {
		super("AirJump", "Auto sprint", Category.MOVEMENT);
	}
	
	@Override
	public void onTick() {
		
		if (mc.player == null) {
			return;
		}
		if (mc.options.jumpKey.isPressed()) {
			mc.player.setOnGround(true);
			mc.player.fallDistance = 0f;
		}
		super.onTick();
	}
}
