package skiddedclient.event.events;

import net.minecraft.client.particle.Particle;
import net.minecraft.particle.ParticleEffect;
import skiddedclient.event.Event;

public class EventParticle extends Event {

	public static class Normal extends EventParticle {

		private Particle particle;

		public Normal(Particle particle) {
			this.particle = particle;
		}

		public Particle getParticle() {
			return particle;
		}
	}

	public static class Emitter extends EventParticle {

		private ParticleEffect effect;

		public Emitter(ParticleEffect effect) {
			this.effect = effect;
		}

		public ParticleEffect getEffect() {
			return effect;
		}
	}
}
