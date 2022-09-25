package skiddedclient.module.movement;

import net.minecraft.client.option.GameOptions;
import net.minecraft.util.math.Vec3d;
import skiddedclient.module.Mod;
import skiddedclient.module.settings.ModeSetting;
import skiddedclient.module.settings.NumberSetting;

public class Jesus extends Mod{
	
	ModeSetting mode = new ModeSetting("Mode", "Legit", "Legit", "Dolphin");
	NumberSetting speed = new NumberSetting("Factor", 1, 10, 2, 1);

	public Jesus() {
		super("Jesus", "Makes you water go boom", Category.MOVEMENT);
		addSettings(mode,speed);
	}
	
	@Override
	public void onTick() {
		if(mode.is("Dolphin")) {
			if(mc.player.isSwimming() && mc.player.isSubmergedInWater()) {
				mc.player.jump();
			}
		}else if(mode.is("Legit")){
			if (mc.player.isTouchingWater()) {
				mc.player.setVelocity(0, 0.1, 0);
				GameOptions go = mc.options;
	        	float y = mc.player.getYaw();
	        	int mx = 0, mz = 0;
	        	if (go.backKey.isPressed()) {
	        		mz++;
	        	}
	        	if (go.leftKey.isPressed()) {
	        		mx--;
	        	}
	        	if (go.rightKey.isPressed()) {
	        		mx++;
	        	}
	        	if (go.forwardKey.isPressed()) {
	        		mz--;
	        	}
	        	double ts = speed.getValueFloat() / 2;
	            double s = Math.sin(Math.toRadians(y));
	            double c = Math.cos(Math.toRadians(y));
	            double nx = ts * mz * s;
	            double nz = ts * mz * -c;
	            nx += ts * mx * -c;
	            nz += ts * mx * -s;
	            Vec3d nv3 = new Vec3d(nx, mc.player.getVelocity().y, nz);
	            mc.player.setVelocity(nv3);
	        	
			}
		}
		super.onTick();
	}

}
