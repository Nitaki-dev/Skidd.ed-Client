package skiddedclient.module.movement;

import net.minecraft.client.option.GameOptions;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.Vec3d;
import skiddedclient.module.Mod;
import skiddedclient.module.settings.ModeSetting;
import skiddedclient.module.settings.NumberSetting;

public class ElytraFly extends Mod {

	public ModeSetting mode = new ModeSetting("Mode", "Static", "Static", "Boost");
	public NumberSetting speed = new NumberSetting("Speed", 0, 10, 2, 1);

	public ElytraFly() {
		super("ElytraFly", "zoomer", Category.MOVEMENT);
		addSettings(mode);
	}

    private boolean wearingElytra() {
        ItemStack equippedStack = mc.player.getEquippedStack(EquipmentSlot.CHEST);
        return equippedStack != null && equippedStack.getItem() == Items.ELYTRA;
    }
    
    @Override
    public void onTick() {
    	if (mc.player.isFallFlying() && wearingElytra()) {
    		if (mode.getMode().equalsIgnoreCase("Static")) {
            	GameOptions go = mc.options;
            	float y = mc.player.getYaw();
            	int mx = 0, my = 0, mz = 0;
     
            	if (go.jumpKey.isPressed()) {
            		my++;
            	}
            	if (go.backKey.isPressed()) {
            		mz++;
            	}
            	if (go.leftKey.isPressed()) {
            		mx--;
            	}
            	if (go.rightKey.isPressed()) {
            		mx++;
            	}
            	if (go.sneakKey.isPressed()) {
            		my--;
            	}
            	if (go.forwardKey.isPressed()) {
            		mz--;
            	}
            	double ts = speed.getValueFloat() / 2;
                double s = Math.sin(Math.toRadians(y));
                double c = Math.cos(Math.toRadians(y));
                double nx = ts * mz * s;
                double nz = ts * mz * -c;
                double ny = ts * my;
                nx += ts * mx * -c;
                nz += ts * mx * -s;
                Vec3d nv3 = new Vec3d(nx, ny, nz);
                mc.player.setVelocity(nv3);
            }
    	}
    	super.onTick();
    }

}