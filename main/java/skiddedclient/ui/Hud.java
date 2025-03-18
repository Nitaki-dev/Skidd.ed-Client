package skiddedclient.ui;

import java.awt.Color;
import java.util.Comparator;
import java.util.List;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import skiddedclient.module.Mod;
import skiddedclient.module.ModuleManager;
import skiddedclient.module.render.TargetHud;
import skiddedclient.utils.font.FontRenderer;
import skiddedclient.utils.render.RenderUtils;

public class Hud {
	private static MinecraftClient mc = MinecraftClient.getInstance();
	
	protected static FontRenderer customFont = new FontRenderer("Montserrat.otf", new Identifier("skiddedclient", "fonts"), 20);

	public static void render(MatrixStack matrices,	 float tickDelta) {
		renderArrayList(matrices);
		if (ModuleManager.INSTANCE.getModule(TargetHud.class).isEnabled()) TargetHudRender(matrices);
	}
	
	@SuppressWarnings("unused")
	public static void renderArrayList(MatrixStack matrices) {
		if (mc.player.currentScreenHandler == null) {
			customFont.drawWithShadow(matrices, "Skidd.ed", 11, 2, -1, false);
			RenderUtils.renderRoundedQuad(matrices, new Color(32,32,32), 10, 7, 55, 19, 3, 20);
		}

		int index = 0;
		int sWidth = mc.getWindow().getScaledWidth();
		int sHeight = mc.getWindow().getScaledHeight();
		
		List<Mod> enabled = ModuleManager.INSTANCE.getEnabledModules();
		
		enabled.sort(Comparator.comparingInt(m -> (int)customFont.getStringWidth(((Mod)m).getDisplayName(), false)).reversed());

		for (Mod mod : enabled) {
			int fWidth = (int) customFont.getStringWidth(mod.getDisplayName(), false);
			int fHeight = (int) customFont.getStringHeight(mod.getDisplayName(), false);
			int offset = index*(fHeight);
			int slideroption = 4;
			customFont.draw(matrices, mod.getDisplayName(), sWidth-3-fWidth, ((fHeight)*(index))-4, -1, false);
//			DrawableHelper.fill(matrices, sWidth-3, fHeight-fHeight, sWidth, fHeight*index+14, -1);
//			DrawableHelper.fill(matrices, sWidth, 9, sWidth - 2,  20 + (index * 13), -1);

		index++;
		}
	}
	
	static PlayerEntity target = null;
	public static void TargetHudRender(MatrixStack matrices) {
		HitResult hit = mc.crosshairTarget;
		int sWidth = mc.getWindow().getScaledWidth();
		int sHeight = mc.getWindow().getScaledHeight();
		
		if (mc.player != null) {
			if (hit != null && hit.getType() == HitResult.Type.ENTITY) {
			    if (((EntityHitResult) hit).getEntity() instanceof PlayerEntity player) {
			        target = player;
			    }
			} else if (target == null) return;
			
			int maxDistance = 32;
			if (!(target == null)) {
				if (target.isDead() || mc.player.squaredDistanceTo(target) > maxDistance) target = null;
			}

			if (target != null) {
				RenderUtils.renderRoundedQuad(matrices, new Color(41,41,41), 10+sWidth/2, 10+sHeight/2, 10+sWidth/2+70, 5+sHeight/2+40, 5, 10);
				customFont.draw(matrices, target.getName().getString(), (int) 10+sWidth/2+5, (int) 10+sHeight/2-2, -1, false);
				RenderUtils.renderRoundedQuad(matrices, new Color(24,24,24), 10+sWidth/2+5, 10+sHeight/2+18, 10+sWidth/2+65, 10+sHeight/2 + 28, 3, 10);
				RenderUtils.renderRoundedQuad(matrices, new Color(249,125,1), 10+sWidth/2+5, 10+sHeight/2+18, 10+sWidth/2 + (target.getHealth()*3) + 5, 10+sHeight/2+ 28, 3, 10);
				customFont.draw(matrices, "" + (int) target.getHealth(), (int) 17+sWidth/2, (int) 22+sHeight/2, -1, false);
			}
		}
	}
}
