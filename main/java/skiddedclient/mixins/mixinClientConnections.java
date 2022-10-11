package skiddedclient.mixins;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import io.netty.channel.ChannelHandlerContext;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.decoration.EndCrystalEntity;
import net.minecraft.network.ClientConnection;
import net.minecraft.network.Packet;
import net.minecraft.network.packet.c2s.play.PlayerInteractEntityC2SPacket;
import skiddedclient.event.events.EventReceivePacket;
import skiddedclient.event.events.EventSendPacket;
import skiddedclient.module.ModuleManager;
import skiddedclient.module.combat.Criticals;
import skiddedclient.module.combat.Criticals.InteractType;
import skiddedclient.module.combat.Velocity;

@Mixin({ ClientConnection.class })
public class mixinClientConnections{
	
	
	
	 @Inject(method = "send(Lnet/minecraft/network/Packet;Lio/netty/util/concurrent/GenericFutureListener;)V", at = @At("HEAD"), cancellable = true)
	 public void send(Packet<?> packet, GenericFutureListener<? extends Future<? super Void>> callback, CallbackInfo ci) {
		 EventSendPacket event = new EventSendPacket(packet);
		 event.call();
		 if (event.isCancelled()) ci.cancel();
	    	
		 if(ModuleManager.INSTANCE.getModule(Criticals.class).isEnabled()) {
			 if (event.getPacket() instanceof PlayerInteractEntityC2SPacket packet1) {
				 if (!(Criticals.instance.getInteractType(packet1) == InteractType.ATTACK && Criticals.instance.getEntity(packet1) instanceof LivingEntity)) return;
				 if (Criticals.instance.getEntity(packet1) instanceof EndCrystalEntity) return;
				 Criticals.instance.doCritical();
			 }
		 }
	 }
	 
	 @Inject(method = { "channelRead0" }, at = { @At("HEAD") }, cancellable = true)
	 public void receive(final ChannelHandlerContext channelHandlerContext, final Packet<?> packet, final CallbackInfo ci) {
		 final EventReceivePacket event = new EventReceivePacket(packet);
		 event.call();
		 if (event.isCancelled()) {
			 ci.cancel();
		 }
        
		 if(ModuleManager.INSTANCE.getModule(Velocity.class).isEnabled()) {
			 Velocity.get.onReceivePacket(event);
		 }
	 }
}