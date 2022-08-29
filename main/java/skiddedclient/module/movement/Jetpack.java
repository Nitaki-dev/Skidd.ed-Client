package skiddedclient.module.movement;

import skiddedclient.module.Mod;

public class Jetpack extends Mod {
	public Jetpack() {
		super("Jetpack", "Allows flight if in a boat", Category.MOVEMENT);
	}
	
	
	@Override
	public void onTick() {
		if(mc.options.jumpKey.isPressed()){
            mc.player.jump();
        }
		super.onTick();
	}
}
