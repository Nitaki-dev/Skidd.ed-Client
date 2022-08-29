package skiddedclient.module.movement;

import net.minecraft.util.math.Vec3d;
import skiddedclient.module.Mod;
import skiddedclient.module.settings.ModeSetting;
import skiddedclient.module.settings.NumberSetting;

public class IceSpeed extends Mod {
	
	public NumberSetting speed = new NumberSetting("Speed", 0, 10, 2, 1);
	public ModeSetting mode = new ModeSetting("Mode", "Ice", "Ice");
	
	
	public IceSpeed() {
		super("IceSpeed", "Walk, but faster", Category.MOVEMENT);
		addSettings(speed);
	}
	
	@Override
	public void onTick() {
		if (mode.getMode().equalsIgnoreCase("Ice")) {
			if(mc.player.isSneaking() || mc.player.forwardSpeed == 0 && mc.player.sidewaysSpeed == 0)
				return;
			if(mc.player.forwardSpeed > 0 && !mc.player.horizontalCollision)
				mc.player.setSprinting(true);
			if(!mc.player.isOnGround())
				return;
			Vec3d v = mc.player.getVelocity();
			mc.player.setVelocity(v.x * (1 + (speed.getValueFloat() / 10)), v.y + 0.1, v.z * (1 + (speed.getValueFloat() / 10)));
			v = mc.player.getVelocity();
			double currentSpeed = Math.sqrt(Math.pow(v.x, 2) + Math.pow(v.z, 2));
			double maxSpeed = 1.66F;
			if(currentSpeed > maxSpeed)
				mc.player.setVelocity(v.x / currentSpeed * maxSpeed, v.y,
					v.z / currentSpeed * maxSpeed);
		}	
	}
}