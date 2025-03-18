package skiddedclient.module.world;

import net.minecraft.client.MinecraftClient;
import skiddedclient.module.Mod;

public class AutoRespawn extends Mod {
	public static MinecraftClient mc = MinecraftClient.getInstance();

	public AutoRespawn() {
		super("AutoRespawn", "This is an Example module", Category.WORLD);
	}

	@Override
	public void onTick() {
		if (this.isEnabled()) {
			if(mc.player.isDead()) {
				mc.player.requestRespawn();
				mc.player.closeScreen();;
			}
		}
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