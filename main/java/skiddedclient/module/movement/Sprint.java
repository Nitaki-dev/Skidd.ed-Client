package skiddedclient.module.movement;

import skiddedclient.module.Mod;

public class Sprint extends Mod {


    public Sprint() {
        super("Sprint", "Auto sprint", Category.MOVEMENT);
    }

    @Override
    public void onTick() {
    	if (this.isEnabled()) {
    		mc.player.setSprinting(true);
    	}
        super.onTick();
    }

    @Override
    public void onDisable() {
        mc.player.stepHeight = 0.6f;
        super.onDisable();
    }
	
}
