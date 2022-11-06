package skiddedclient.module.movement;

import skiddedclient.module.Mod;
import skiddedclient.module.settings.ModeSetting;

public class BHop extends Mod {
	public ModeSetting mode = new ModeSetting("Mode", "Vanilla", "Vanilla", "Velocity");

	public BHop() {
		super("BHop", "BHop hack", Category.MOVEMENT);
		addSetting(mode);
	}
	
	@Override
	public void onTick() {
		nullCheck();
		if (mc.options.forwardKey.isPressed() || mc.options.leftKey.isPressed() || mc.options.rightKey.isPressed()) {
			if (mode.is("Vanilla")) {
				if (mc.player.isOnGround()) mc.player.jump();
				mc.player.airStrafingSpeed=0.05f;
			} else if (mode.is("Velocity")) {
				if (mc.player.isOnGround()) mc.player.jump();
				mc.player.addVelocity(0, -0.05, 0);
			}
		}
			
		super.onTick();
	}
	
	@Override
	public void onEnable() {
		super.onEnable();
	}
	
	@Override
	public void onDisable() {
		super.onDisable();
	}
}
