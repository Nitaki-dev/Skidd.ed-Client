package skiddedclient;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.glfw.GLFW;

import net.fabricmc.api.ModInitializer;
import net.minecraft.client.MinecraftClient;
import skiddedclient.module.Mod;
import skiddedclient.module.ModuleManager;
import skiddedclient.ui.screens.clickGUI.ClickGUI;

public class Client implements ModInitializer {
	
	public static final Client INSTANCE = new Client();
	public static Logger logger = LogManager.getLogger(Client.class);
	private MinecraftClient mc = MinecraftClient.getInstance();

	@Override
	public void onInitialize() {
		logger.info(" --- --- --- Skidd.ed started properly --- --- --- ");
	}
	
	public void onKeyPress(int key, int action) {
		if (action == GLFW.GLFW_PRESS) {
			if (mc.currentScreen == null) {
				for (Mod module : ModuleManager.INSTANCE.getModules()) {
					if (key == module.getKey()) module.toggle();
				}
			}
			
			if (key == GLFW.GLFW_KEY_RIGHT_SHIFT) mc.setScreen(ClickGUI.INSTANCE);
		}
	}
	
	public void onTick() {
		if (mc.player != null) {
			for (Mod module : ModuleManager.INSTANCE.getEnabledModules()) {
				module.onTick();
			}
		}
	}
}
