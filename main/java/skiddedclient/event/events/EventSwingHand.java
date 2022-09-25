package skiddedclient.event.events;

import net.minecraft.util.Hand;
import skiddedclient.event.Event;

public class EventSwingHand extends Event {

	private Hand hand;
	
	public EventSwingHand(Hand hand) {
		this.hand = hand;
	}
	
	public Hand getHand() {
		return hand;
	}
}