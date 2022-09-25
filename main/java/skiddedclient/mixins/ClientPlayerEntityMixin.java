package skiddedclient.mixins;

import com.mojang.authlib.GameProfile;

import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.util.ClientPlayerTickable;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.MovementType;
import net.minecraft.network.encryption.PlayerPublicKey;
import net.minecraft.network.packet.c2s.play.ClientCommandC2SPacket;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3d;
import skiddedclient.event.events.EventMotionUpdate;
import skiddedclient.event.events.EventSwingHand;
import skiddedclient.module.Mod;
import skiddedclient.module.ModuleManager;
import skiddedclient.event.Event;

import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

import static skiddedclient.utils.misc.GetReferenceMC.mc;

@Mixin(ClientPlayerEntity.class)
public abstract class ClientPlayerEntityMixin extends AbstractClientPlayerEntity {

    @Shadow protected abstract boolean isCamera();
    @Unique
    private boolean lastSneaking;
    @Unique private boolean lastSprinting;
    @Unique private double lastX;
    @Unique private double lastBaseY;
    @Unique private double lastZ;
    @Unique private float lastYaw;
    @Unique private float lastPitch;
    @Unique private boolean lastOnGround;
    @Unique private int ticksSinceLastPositionPacketSent;
    @Shadow private boolean autoJumpEnabled = true;
    @Shadow @Final private List<ClientPlayerTickable> tickables;
    @SuppressWarnings("unused")
	private boolean ignoreMessage = false;

    public ClientPlayerEntityMixin(ClientWorld world, GameProfile profile, @Nullable PlayerPublicKey publicKey) {
        super(world, profile, publicKey);
    }

    @Inject(method = "move", at = @At(value = "HEAD"), cancellable = true)
    public void onMotion(MovementType type, Vec3d movement, CallbackInfo ci) {
        for (Mod mod : ModuleManager.INSTANCE.getEnabledModules()) {
            mod.onMotion();
        }
    }

    @Inject(method = "swingHand", at = @At("HEAD"), cancellable = true)
    public void onSwingHand(Hand hand, CallbackInfo ci) {
        EventSwingHand event = new EventSwingHand(hand);
        event.call();
        if (event.isCancelled()) ci.cancel();
    }

    @Inject(method = "sendMovementPackets", at = @At("HEAD"), cancellable = true)
    private void sendMovementPackets(CallbackInfo ci) {
            this.sendMovementPacketsWithEvent();
            EventMotionUpdate event = new EventMotionUpdate(mc.player.getX(), mc.player.getY(), mc.player.getZ(), mc.player.getYaw(), mc.player.getPitch(), this.lastYaw, this.lastPitch, mc.player.isOnGround(), this.isSneaking(), Event.State.POST);
            event.call();
            ci.cancel();
    }

    private void sendMovementPacketsWithEvent() {
        EventMotionUpdate event = new EventMotionUpdate(mc.player.getX(), mc.player.getY(), mc.player.getZ(), mc.player.getYaw(), mc.player.getPitch(), this.lastYaw, this.lastPitch, mc.player.isOnGround(), this.isSneaking(), Event.State.PRE);
        event.call();
        boolean bl = this.isSprinting();
        if (bl != this.lastSprinting) {
            ClientCommandC2SPacket.Mode mode = bl ? ClientCommandC2SPacket.Mode.START_SPRINTING : ClientCommandC2SPacket.Mode.STOP_SPRINTING;
            mc.player.networkHandler.sendPacket(new ClientCommandC2SPacket(this, mode));
            this.lastSprinting = bl;
        }

        boolean bl2 = this.isSneaking();
        if (bl2 != this.lastSneaking) {
            ClientCommandC2SPacket.Mode mode2 = bl2 ? ClientCommandC2SPacket.Mode.PRESS_SHIFT_KEY : ClientCommandC2SPacket.Mode.RELEASE_SHIFT_KEY;
            mc.player.networkHandler.sendPacket(new ClientCommandC2SPacket(this, mode2));
            this.lastSneaking = bl2;
        }

        if (this.isCamera()) {
            double d = event.getX() - this.lastX;
            double e = this.getY() - this.lastBaseY;
            double f = event.getZ() - this.lastZ;
            double g = (double)(event.getYaw() - this.lastYaw);
            double h = (double)(event.getPitch() - this.lastPitch);
            ++this.ticksSinceLastPositionPacketSent;
            boolean bl3 = d * d + e * e + f * f > 9.0E-4D || this.ticksSinceLastPositionPacketSent >= 20;
            boolean bl4 = g != 0.0D || h != 0.0D;
            if (this.hasVehicle()) {
                Vec3d vec3d = this.getVelocity();
                mc.player.networkHandler.sendPacket(new PlayerMoveC2SPacket.Full(vec3d.x, -999.0D, vec3d.z, this.getYaw(), this.getPitch(), this.onGround));
                bl3 = false;
            } else if (bl3 && bl4) {
                mc.player.networkHandler.sendPacket(new PlayerMoveC2SPacket.Full(event.getX(), this.getY(), event.getZ(), event.getYaw(), event.getPitch(), event.isOnGround()));
            } else if (bl3) {
                mc.player.networkHandler.sendPacket(new PlayerMoveC2SPacket.PositionAndOnGround(event.getX(), this.getY(), event.getZ(), event.isOnGround()));
            } else if (bl4) {
                mc.player.networkHandler.sendPacket(new PlayerMoveC2SPacket.LookAndOnGround(event.getYaw(), event.getPitch(), event.isOnGround()));
            } else if (this.lastOnGround != this.onGround) {
                mc.player.networkHandler.sendPacket(new PlayerMoveC2SPacket.OnGroundOnly(event.isOnGround()));
            }

            if (bl3) {
                this.lastX = event.getX();
                this.lastBaseY = this.getY();
                this.lastZ = event.getZ();
                this.ticksSinceLastPositionPacketSent = 0;
            }

            if (bl4) {
                this.lastYaw = event.getYaw();
                this.lastPitch = event.getPitch();
            }

            this.lastOnGround = event.isOnGround();
            this.autoJumpEnabled = mc.options.getAutoJump().getValue();
        }
    }
}
