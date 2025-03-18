package skiddedclient.event.events;

import net.minecraft.network.Packet;
import skiddedclient.event.Event;

public class EventReceivePacket extends Event {

	private Packet<?> packet;
	
	public EventReceivePacket(Packet<?> packet) {
		this.packet = packet;
	}
	
	public Packet<?> getPacket() {
		return packet;
	}
}
