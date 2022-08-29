package skiddedclient.module.movement;

import net.minecraft.util.math.Vec3d;
import skiddedclient.module.Mod;
import skiddedclient.module.settings.NumberSetting;

public class FastClimb extends Mod {
	
	public NumberSetting speed = new NumberSetting("Speed", 0.1, 2, 0.3, 0.1);

    public FastClimb() {
        super("FastClimb", "YSpeed", Category.MOVEMENT);
        addSetting(speed);
    }
    
	@Override
	public void onTick() {
		
		if (!mc.player.isClimbing() || !mc.player.horizontalCollision) return;
        if (mc.player.input.movementForward == 0 && mc.player.input.movementSideways == 0) return;
        Vec3d velocity = mc.player.getVelocity();
        mc.player.setVelocity(velocity.x, speed.getValue(), velocity.z);
		
		super.onTick();
	}
}