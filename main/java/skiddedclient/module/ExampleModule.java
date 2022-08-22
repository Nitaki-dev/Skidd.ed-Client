package skiddedclient.module;

import net.minecraft.client.MinecraftClient;
import skiddedclient.module.settings.BooleanSetting;
import skiddedclient.module.settings.ColorSetting;
import skiddedclient.module.settings.ModeSetting;
import skiddedclient.module.settings.NumberSetting;
import skiddedclient.utils.render.ColorUtils;

public class ExampleModule extends Mod {
	
	public static MinecraftClient mc = MinecraftClient.getInstance();
	
	public NumberSetting speed = new NumberSetting("Slider",  0.2, 10, 1, 0.1);
	public BooleanSetting bool = new BooleanSetting("Toggle", true);
	public ModeSetting mode = new ModeSetting("Mode", "Mode 1", "Mode 1", "Mode 2", "Mode 3");
	public ColorSetting color = new ColorSetting("Color", ColorUtils.red);
	
    public ExampleModule() {
        super("TestModule", "This is an Example module!", Category.WORLD);
        addSettings(speed, bool, mode, color);
//        addSetting(color);

    }

	@Override
	public void onTick() {
//		mc.world.getServer().getServerIp();
		super.onTick();
	}
	
	@Override
	public void onEnable() {
		
		super.onEnable();
	}
	
	@Override
	public void onDisable() {
		
		super.onDisable();
	}
}