package skiddedclient.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.At;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.entity.LivingEntity;
import skiddedclient.utils.RotationUtils;

@Mixin(LivingEntityRenderer.class)
public class LivingEntityRendererMixin {
	private static MinecraftClient mc = MinecraftClient.getInstance();

    @ModifyVariable(method = "render", ordinal = 5, at = @At(value = "STORE", ordinal = 3))
    public float changePitch(float oldValue, LivingEntity entity) {
        if (entity.equals(mc.player) && RotationUtils.isCustomPitch) return RotationUtils.serverPitch;
        return oldValue;
    }
    
    @ModifyVariable(method = "render", ordinal = 2, at = @At(value = "STORE", ordinal = 0))
    public float changeYaw(float oldValue, LivingEntity entity) {
        if (entity.equals(mc.player) && RotationUtils.isCustomYaw) return RotationUtils.serverYaw;
        return oldValue;
    }

    @ModifyVariable(method = "render", ordinal = 3, at = @At(value = "STORE", ordinal = 0))
    public float changeHeadYaw(float oldValue, LivingEntity entity) {
        if (entity.equals(mc.player) && RotationUtils.isCustomYaw) return RotationUtils.serverYaw;
        return oldValue;
    }
}