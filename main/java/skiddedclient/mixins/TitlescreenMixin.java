package skiddedclient.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.TitleScreen;
import skiddedclient.ui.SkiddedMenuScreen;

@Mixin(TitleScreen.class)
public class TitlescreenMixin {
	@Inject(at = @At("HEAD"), method = "init") 
	public void onInit(CallbackInfo ci) {
		MinecraftClient.getInstance().setScreen(new SkiddedMenuScreen());
	}
}
