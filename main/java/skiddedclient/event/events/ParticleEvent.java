package skiddedclient.event.events;

import net.minecraft.particle.ParticleEffect;
import skiddedclient.event.Event;

public class ParticleEvent extends Event {
    public ParticleEffect particle;

    public ParticleEvent(ParticleEffect particle) {
        this.setCancelled(false);
        this.particle = particle;
    }
}