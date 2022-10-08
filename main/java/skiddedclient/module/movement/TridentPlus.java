package skiddedclient.module.movement;

import net.minecraft.util.Formatting;
import net.minecraft.util.math.MathHelper;
import skiddedclient.module.Mod;
import skiddedclient.module.settings.NumberSetting;

public class TridentPlus extends Mod{
	
	NumberSetting factor = new NumberSetting("Factor", 1, 10, 3, 1);
	NumberSetting upFactor = new NumberSetting("Up Factor", 1, 10, 3, 1);

	public TridentPlus() {
		super("TridentPlus", "Better trident", Category.MOVEMENT);
		addSettings(factor,upFactor);
	}
	
	private static final Formatting Gray = Formatting.GRAY;
	@Override
	public void onTick() {
		this.setDisplayName("Flight" + Gray + " [F"+factor.getValue()+" H:"+upFactor.getValue()+"]");
		float yaw = (float) Math.toRadians(mc.player.getYaw());
		float pitch = (float) Math.toRadians(mc.player.getPitch());
		double vSpeed = factor.getValue() / 5;
		if(mc.player.isUsingRiptide()) {
			mc.player.setVelocity(-MathHelper.sin(yaw) * vSpeed, -MathHelper.sin(pitch) * vSpeed * upFactor.getValue(), MathHelper.cos(yaw) * vSpeed);
		}
		super.onTick();
	}	

}
