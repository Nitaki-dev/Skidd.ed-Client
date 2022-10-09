package skiddedclient.ui;

import java.awt.Color;
import java.util.Comparator;
import java.util.List;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import skiddedclient.module.Mod;
import skiddedclient.module.ModuleManager;
import skiddedclient.module.combat.TargetHud;
import skiddedclient.ui.screens.clickGUI.ClickGUI;
import skiddedclient.utils.font.FontRenderer;
import skiddedclient.utils.render.RenderUtils;

public class Hud {
	private static MinecraftClient mc = MinecraftClient.getInstance();
	
	protected static FontRenderer customFont = new FontRenderer("Montserrat.otf", new Identifier("skiddedclient", "fonts"), 20);
	
	
	public static void render(MatrixStack matrices, float tickDelta) {
		
		renderArrayList(matrices);
		if (ModuleManager.INSTANCE.getModule(TargetHud.class).isEnabled()) TargetHudRender(matrices);
	}
	
	@SuppressWarnings("unused")
	public static void renderArrayList(MatrixStack matrices) {
		if (mc.currentScreen != ClickGUI.INSTANCE) {
			
			RenderUtils.renderRoundedQuad(matrices, new Color(12,12,12), 10, 7, 56, 19, 2, 100);
			customFont.drawWithShadow(matrices, "Skidd.ed", 11, 7, -1, false);
						
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
				DrawableHelper.fill(matrices
						, sWidth - fWidth-5
						, index*(fHeight)+1
						, sWidth
						, fHeight  + index*(fHeight)+1
						, 0x70000000);
				
//				DrawableHelper.fill(matrices, sWidth-3-fWidth, 4+index, ((fHeight)*(index))+sWidth, fHeight*index, 0x70000000);
				customFont.draw(matrices, mod.getDisplayName(), sWidth-3-fWidth, ((fHeight)*(index))-4, -1, false);
				index++;
			}
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
