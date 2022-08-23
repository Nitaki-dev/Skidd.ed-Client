package skiddedclient.module.movement;

import skiddedclient.module.Mod;
import skiddedclient.module.settings.BooleanSetting;

public class Sprint extends Mod {

	BooleanSetting smart = new BooleanSetting("Smart", false);

    public Sprint() {
        super("Sprint", "Automatically sprints for you", Category.MOVEMENT);
        addSetting(smart);
    }

    @Override
    public void onTick() {
    	if (this.isEnabled()) {
    		if (smart.isEnabled()) {
    			if (mc.player.forwardSpeed>0) mc.player.setSprinting(true);
    		}
    		else mc.player.setSprinting(true);
    	}
        super.onTick();
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }
	
}
