package skiddedclient.module;

import net.minecraft.client.MinecraftClient;
import skiddedclient.module.settings.BooleanSetting;
import skiddedclient.module.settings.ModeSetting;
import skiddedclient.module.settings.NumberSetting;

public class ExampleModule extends Mod {
	
	public static MinecraftClient mc = MinecraftClient.getInstance();
	
	public NumberSetting speed = new NumberSetting("Slider",  0.2, 10, 1, 0.1);
	public BooleanSetting bool = new BooleanSetting("Toggle", true);
	public ModeSetting mode = new ModeSetting("Mode", "Mode 1", "Mode 1", "Mode 2", "Mode 3");
	public NumberSetting color = new NumberSetting("Color",  0, 360, 1, 1);

	
    public ExampleModule() {
        super("Test_Module", "This is an Example module", Category.EXPLOIT);
        addSettings(speed, bool, mode, color);

    }
    
	@Override
	public void onTick() {
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