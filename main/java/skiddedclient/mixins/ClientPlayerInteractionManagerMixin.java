package skiddedclient.mixins;

import net.minecraft.client.network.ClientPlayerInteractionManager;
import skiddedclient.module.ModuleManager;
import skiddedclient.module.combat.Reach;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Mixin(ClientPlayerInteractionManager.class)
public class ClientPlayerInteractionManagerMixin {

    @Inject(method = "getReachDistance", at = @At("HEAD"), cancellable = true)
    private void DF9_overwriteReach(CallbackInfoReturnable<Float> cir) {
        if (ModuleManager.INSTANCE.getModule(Reach.class).isEnabled()) {
            cir.setReturnValue((float) ModuleManager.INSTANCE.getModule(Reach.class).reach.getValueFloat());
        }
    }

    @Inject(method = "hasExtendedReach", at = @At("HEAD"), cancellable = true)
    private void DF9_setExtendedReach(CallbackInfoReturnable<Boolean> cir) {
        if (ModuleManager.INSTANCE.getModule(Reach.class).isEnabled()) {
            cir.setReturnValue(true);
        }
    }
	
}
