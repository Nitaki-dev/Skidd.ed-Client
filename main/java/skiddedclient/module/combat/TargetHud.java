package skiddedclient.module.combat;

import skiddedclient.module.Mod;

public class TargetHud extends Mod {
	
    public TargetHud() {
        super("TargetHud", "Displays current target health and name", Category.COMBAT);
        addSettings();
    }
}
