package skiddedclient.module.render;

import java.awt.Color;

import skiddedclient.module.Mod;
import skiddedclient.module.settings.ModeSetting;
import skiddedclient.module.settings.NumberSetting;

public class GUI extends Mod {
	public static int MainColorRGB = new Color(12,12,12).getRGB();
	public static int MainColorEnabledRGB = new Color(249,125,1).getRGB();
//	public static int MainColorEnabledRGB = new Color(229,1,1).getRGB();

	public static Color MainColor = new Color(12,12,12);
	public static Color MainColorEnabled = new Color(249,125,1);
//	public static Color MainColorEnabled = new Color(229,1,1);
	public static ModeSetting theme = new ModeSetting("Theme", "Midnight", "Midnight", "Light");
	public NumberSetting rounded = new NumberSetting("Corners", 0, 8, 3, 1);
	
	public GUI() {
		super("ClickGUI", "GUI customization", Category.RENDER);
		addSettings(theme, rounded);
	}
	
	public static void themeColor() {
		if (theme.is("Midnight")) {
			MainColorRGB = new Color(12,12,12).getRGB();
			MainColorEnabledRGB = new Color(249,125,1).getRGB();
			MainColor = new Color(12,12,12);
			MainColorEnabled = new Color(249,125,1);
		} else if (theme.is("Light")) {
			MainColorRGB = new Color(38,38,38).getRGB();
			MainColorEnabledRGB = new Color(249,125,1).getRGB();
			MainColor = new Color(38,38,38);
			MainColorEnabled = new Color(249,125,1);
		}
	}
	//Midnight theme
	//Color theme
	//Vape theme
	//DF9 theme
}