package skiddedclient.module.movement;

import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import skiddedclient.module.Mod;
import skiddedclient.module.settings.ModeSetting;

public class NoFall extends Mod {

	public ModeSetting mode = new ModeSetting("Mode", "OnGround", "OnGround", "BreakFall");
	
    public NoFall() {
        super("NoFall", "Prevent you from taking fall damage", Category.MOVEMENT);
        addSetting(mode);
    }

    @Override
    public void onTick() {
        if (mc.player == null || mc.getNetworkHandler() == null) {
            return;
        }
        if (mode.getMode().equalsIgnoreCase("BreakFall")) {
        	if (mc.player.fallDistance > 2.5) {
        		mc.getNetworkHandler().sendPacket(new PlayerMoveC2SPacket.OnGroundOnly(true));
        		mc.player.setVelocity(0, 0.1, 0);
        		mc.player.fallDistance = 0;
        	}
        } else if (mode.getMode().equalsIgnoreCase("OnGround")) {
        	if (mc.player.fallDistance > 2.5) mc.getNetworkHandler().sendPacket(new PlayerMoveC2SPacket.OnGroundOnly(true));
        }
        super.onTick();
    }
}