package skiddedclient.event.events;

import net.minecraft.network.Packet;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import skiddedclient.event.Event;

public class EventSendPacket extends Event {

	private Packet<?> packet;
	
	public EventSendPacket(Packet<?> packet) {
		this.packet = packet;
	}
	
	public Packet<?> getPacket() {
		return packet;
	}

	public void setPacket(PlayerMoveC2SPacket packet) {
		this.packet = packet;
	}
}
