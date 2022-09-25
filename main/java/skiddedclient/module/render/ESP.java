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
import net.minecraft.util.math.Vec3d;
import skiddedclient.event.events.EventRender3D;
import skiddedclient.module.Mod;
import skiddedclient.module.settings.ModeSetting;
import skiddedclient.module.settings.NumberSetting;
import skiddedclient.utils.render.RenderUtils;

public class ESP extends Mod {

	public static ModeSetting mode = new ModeSetting("Mode", "Rect", "Rect", "Box");

	public NumberSetting red = new NumberSetting("Color",  0, 255, 255, 1);
	public NumberSetting blue = new NumberSetting("Color",  0, 255, 0, 1);
	public NumberSetting green = new NumberSetting("Color",  0, 255, 0, 1);
	

	public ESP() {
		super("ESP", "Renders a rect/box around entities", Category.RENDER);
		addSettings(mode, red,green,blue);
	}
	
	@Override
	public void onWorldRender(MatrixStack matrices) {
		if (this.isEnabled()) {
			for (Entity entity  : mc.world.getEntities()) {
				if (!(entity instanceof ClientPlayerEntity)) {
					if (mode.is("Rect")) {
						RenderUtils.renderOutlineRect(entity, new Color(red.getValueInt(), blue.getValueInt(), green.getValueInt()), matrices);
					}
					Vec3d renderPos = RenderUtils.getEntityRenderPosition(entity, EventRender3D.getTickDelta());
					if (mode.is("Box")) RenderUtils.drawEntityBox(matrices, entity, renderPos.x, renderPos.y, renderPos.z, new Color(red.getValueInt(), blue.getValueInt(), green.getValueInt()));
				}
			}
		}
		super.onWorldRender(matrices);
	}
}
