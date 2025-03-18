package skiddedclient.module.render;

import skiddedclient.module.Mod;
import skiddedclient.module.settings.ISimpleOption;

public class Fullbright extends Mod {

    public Fullbright() {
        super("Fullbright", "night vision", Category.RENDER);
    }
    @SuppressWarnings("unchecked")
	@Override
    public void onTick() {
        if (this.isEnabled()) {
            ((ISimpleOption<Double>) (Object) mc.options.getGamma()).setValueUnrestricted(100.0d);
        }
    }
    @Override
    public void onDisable() {
    	mc.options.getGamma().setValue(0.0);
    	super.onDisable();
    }
}