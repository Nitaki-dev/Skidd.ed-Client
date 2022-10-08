package skiddedclient.module.render;
//
//
//
//
//
//			Taken Box mode from Hypnotic client and Rect mode from df9
//
//
//
//
//
import java.awt.Color;

import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.Monster;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.Vec3d;
import skiddedclient.event.events.EventRender3D;
import skiddedclient.module.Mod;
import skiddedclient.module.settings.BooleanSetting;
import skiddedclient.module.settings.ModeSetting;
import skiddedclient.module.settings.NumberSetting;
import skiddedclient.utils.render.RenderUtils;

public class ESP extends Mod {

	public static ModeSetting mode = new ModeSetting("Mode", "Rect", "Rect", "Box", "Glow");

	public BooleanSetting players = new BooleanSetting("Players", true);
	public BooleanSetting monsters = new BooleanSetting("Monsters", true);
	public BooleanSetting passives = new BooleanSetting("Passives", true);
	public BooleanSetting invisibles = new BooleanSetting("Invisibles", true);
	
	public NumberSetting red = new NumberSetting("Color",  0, 255, 255, 1);
	public NumberSetting blue = new NumberSetting("Color",  0, 255, 0, 1);
	public NumberSetting green = new NumberSetting("Color",  0, 255, 0, 1);
	
	public ESP() {
		super("ESP", "Renders a rect/box around entities", Category.RENDER);
		addSettings(mode, players,monsters,passives,invisibles, red,green,blue);
	}
	
	private static final Formatting Gray = Formatting.GRAY;
	
	@Override
	public void onTick() {
		this.setDisplayName("ESP" + Gray + " ["+mode.getMode()+"]");
	}
	
	@Override
	public void onWorldRender(MatrixStack matrices) {
		if (this.isEnabled()) {
			for (Entity e  : mc.world.getEntities()) {
				if (!(e instanceof ClientPlayerEntity)) {
					
					if (shouldRenderEntity(e)) {
						if (mode.is("Rect")) {
							RenderUtils.renderOutlineRect(e, new Color(red.getValueInt(), blue.getValueInt(), green.getValueInt()), matrices);
						}
						Vec3d renderPos = RenderUtils.getEntityRenderPosition(e, EventRender3D.getTickDelta());
						if (mode.is("Box")) {
							RenderUtils.drawEntityBox(matrices, e, renderPos.x, renderPos.y, renderPos.z, new Color(red.getValueInt(), blue.getValueInt(), green.getValueInt()));
						}
						if (mode.is("Glow")) {
						}
					}
				}
			}
		}
		super.onWorldRender(matrices);
	}
	
	@Override
	public void onEnable() {
		
		super.onEnable();
	}
	
	@Override
	public void onDisable() {
		
		super.onDisable();
	}
	
	public boolean shouldRenderEntity(Entity entity) {
		if (players.isEnabled() && entity instanceof PlayerEntity) return true;
		if (monsters.isEnabled() && entity instanceof Monster) return true;
		if (passives.isEnabled() && entity instanceof PassiveEntity) return true;
		if (invisibles.isEnabled() && entity.isInvisible()) return true;
		return false;
	}
}
