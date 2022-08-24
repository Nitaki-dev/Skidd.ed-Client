package skiddedclient.module.combat;
//
//
//
//
//
//			Taken from Hypnotic client
//
//
//
//
//
import java.util.ArrayList;
import java.util.UUID;

import com.mojang.authlib.GameProfile;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import skiddedclient.module.Mod;
import skiddedclient.utils.player.FakePlayerEntity;

public class FakePlayer extends Mod {

	public ArrayList<FakePlayerEntity> fakePlayers = new ArrayList<>();
	private PlayerEntity playerEntity;
	
	public FakePlayer() {
		super("FakePlayer", "Spawns a fake player to test combat modules on", Category.COMBAT);
	}

	@Override
	public void onEnable() {
		if (mc.player != null) {
			playerEntity = new FakePlayerEntity(mc.world, new GameProfile(UUID.randomUUID(), "Dummy"));
			playerEntity.copyFrom(mc.player);
			playerEntity.copyPositionAndRotation(mc.player);
			mc.world.addEntity(42069, playerEntity);
		}
		super.onEnable();
	}
	
	@Override
	public void onDisable() {
		if (playerEntity != null) {
			playerEntity.setPos(Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
			if (mc.world != null)
				mc.world.removeEntity(playerEntity.getId(), Entity.RemovalReason.DISCARDED);
			playerEntity = null;
		}
		super.onDisable();
	}
}
