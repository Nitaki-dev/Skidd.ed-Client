package skiddedclient.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import skiddedclient.Client;
import skiddedclient.module.ModuleManager;
import skiddedclient.module.render.ESP;

@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {
	
	@Inject(method = "tick", at = @At("HEAD"), cancellable = true)
	public void onTick(CallbackInfo ci) {
		Client.INSTANCE.onTick();
	}
	
	@Inject(method = "getWindowTitle", at = @At("HEAD"), cancellable = true)
    public void getWindowTitle(CallbackInfoReturnable<String> ci) {
        ci.setReturnValue("Skidd.ed client | Private beta");
    }
	
	@SuppressWarnings("static-access")
	@Inject(method = "hasOutline", at = @At("HEAD"), cancellable = true)
	private void onHasOutline(Entity entity, CallbackInfoReturnable<Boolean> info){
		if(ModuleManager.INSTANCE.getModule(ESP.class).isEnabled() && ModuleManager.INSTANCE.getModule(ESP.class).mode.is("Glow") && ModuleManager.INSTANCE.getModule(ESP.class).shouldRenderEntity(entity)) {
			info.setReturnValue(true);
		}
	}
}
