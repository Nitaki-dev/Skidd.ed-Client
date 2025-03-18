package skiddedclient.module;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import skiddedclient.module.settings.KeyBindSetting;
import skiddedclient.module.settings.Setting;
import skiddedclient.utils.font.FontRenderer;

public class Mod {

	private String name;
	private String displayName;
	private String description;
	private Category category;
	public int key;
	public String key2;
	private boolean enabled;
	protected static FontRenderer customFont = new FontRenderer("Montserrat.otf", new Identifier("skiddedclient", "fonts"), 20);

	
	private List<Setting> settings = new ArrayList<>();
	
	protected MinecraftClient mc = MinecraftClient.getInstance();
	
	public Mod(String name, String description, Category category) {
		this.name = name;
		this.displayName = name;
		this.description = description;
		this.category = category;
		
		addSetting(new KeyBindSetting("Key", 0));
	}
	
	public List<Setting> getSetting() {
		return settings;
	}
	
	public void addSetting(Setting setting) {
		settings.add(setting);
	}
	
	public void addSettings(Setting...settings) {
		for (Setting setting : settings) addSetting(setting);
	}
	
	
	public void toggle() {
		this.enabled = !this.enabled;
		
		if (enabled) onEnable();
		else onDisable();
	}
	
	public void onEnable() {
		if(mc.options != null){
		}
	}
	
    public static void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (Exception ignored) {
        }
    }
    
	public Setting getSettingByName(Mod mod, String settingName) {
        for(Setting setting : mod.getSetting()) {
            if (setting.getName().trim().equalsIgnoreCase(settingName)) {
                return setting;
            }
        }
        return null;
    }
	
	public void onDisable() {}
	public void onTick() {}
	public void onTickDisabled() {}
	public void onMotion() {};
	public String getDisplayName() {return displayName;}
	public void setDisplayName(String displayName) {this.displayName = displayName;}
	public String getName() {return name;}
	public void setName(String name) {this.name = name;}
	public String getDescription() {return description;}
	public void setDescription(String description) {this.description = description;}
	public int getKey() {return key;}
	public void setKey(int key) {this.key = key;}
	public boolean isEnabled() {return enabled;}
    public void onWorldRender(MatrixStack matrices) {}

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
        
        if (mc.player != null) {  
              if (enabled) onEnable();
              else onDisable();
        }
 }

	public Category getCategory() {return category;}

	public enum Category {
		MOVEMENT("Movement"),
		COMBAT("Combat"),
		RENDER("Render"),
		EXPLOIT("Exploit"),
		WORLD("World");
		
		public String name;
		public Color color;
		
		private Category(String name) {
			this.name = name;
		}
	}
	

	public void nullCheck() {
		if(mc.world == null || mc.player == null || mc.getNetworkHandler() == null || mc.getBufferBuilders() == null) {
			return;
		}
	}
}
