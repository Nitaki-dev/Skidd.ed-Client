package skiddedclient.module.combat;

import skiddedclient.module.Mod;
import skiddedclient.module.settings.NumberSetting;

public class Reach extends Mod{
	
	public NumberSetting reach = new NumberSetting("Slider",  3, 10, 3, 1);
	
    public Reach() {
        super("Reach", "Long arms", Category.COMBAT);
        addSettings(reach);
    }
}
