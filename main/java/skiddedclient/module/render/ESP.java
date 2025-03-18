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

import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.Vec3d;
import skiddedclient.event.events.EventRender3D;
import skiddedclient.module.Mod;
import skiddedclient.module.settings.ModeSetting;
import skiddedclient.module.settings.PasteSetting;
import skiddedclient.utils.render.ColorUtils;
import skiddedclient.utils.render.RenderUtils;
public class ESP extends Mod {

	public static ModeSetting mode = new ModeSetting("Mode", "Rect", "Rect", "Box");
	public PasteSetting color = new PasteSetting("Color", false);

	public ESP() {
		super("ESP", "Renders a rect/box around entities", Category.RENDER);
		addSettings(mode, color);
	}
	
	@SuppressWarnings("static-access")
	@Override
	public void onWorldRender(MatrixStack matrices) {
		if (this.isEnabled()) {
			for (Entity entity  : mc.world.getEntities()) {
				if (!(entity instanceof ClientPlayerEntity)) {
					if (mode.is("Rect")) {
						RenderUtils.renderOutlineRect(entity, ColorUtils.hexToRgb(color.checked), matrices);
					}
					Vec3d renderPos = RenderUtils.getEntityRenderPosition(entity, EventRender3D.getTickDelta());
					if (mode.is("Box")) RenderUtils.drawEntityBox(matrices, entity, renderPos.x, renderPos.y, renderPos.z, ColorUtils.hexToRgb(color.checked));
				}
			}
		}
		super.onWorldRender(matrices);
	}
}
