package skiddedclient.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.Keyboard;
import skiddedclient.Client;

@Mixin(Keyboard.class)
public class KeyboardMixin {

	@SuppressWarnings("static-access")
	@Inject(method = "onKey", at = @At("HEAD"), cancellable = true)
	public void onKey(long window, int key, int scancode, int action, int modifiers, CallbackInfo ci) {
//		Client.INSTANCE.logger.info(key);
		Client.INSTANCE.onKeyPress(key, action);
	}
}
