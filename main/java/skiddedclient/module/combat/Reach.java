package skiddedclient.module.combat;

import net.minecraft.util.Formatting;
import skiddedclient.module.Mod;
import skiddedclient.module.settings.NumberSetting;

public class Reach extends Mod{
	
	public NumberSetting reach = new NumberSetting("Slider",  4, 10, 3, 1);
	
    public Reach() {
        super("Reach", "Long arms", Category.COMBAT);
        addSettings(reach);
    }    
    
    private static final Formatting Gray = Formatting.GRAY;

    @Override
	public void onTick() {
		this.setDisplayName("Reach" + Gray + " [R:"+reach.getValue()+"]");
    }
}
