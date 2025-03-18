package skiddedclient.ui;

import java.awt.Color;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

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
import skiddedclient.utils.font.FontRenderer;
import skiddedclient.utils.render.ColorUtils;
import skiddedclient.utils.render.RenderUtils;

public class Hud extends Mod{
	
	private static MinecraftClient mc = MinecraftClient.getInstance();
	
	protected static FontRenderer customFont = new FontRenderer("Montserrat.otf", new Identifier("skiddedclient", "fonts"), 20);
	
	public Hud() {
		super("hud", "hud", Category.RENDER);
	}

	
	public static void render(MatrixStack matrices, float tickDelta) {
		
		renderArrayList(matrices);
		if (ModuleManager.INSTANCE.getModule(TargetHud.class).isEnabled()) TargetHudRender(matrices);
	}
	

	@SuppressWarnings("unused")
	public static void renderArrayList(MatrixStack matrices) {
			int index = 0;
			int sWidth = mc.getWindow().getScaledWidth();
			int sHeight = mc.getWindow().getScaledHeight();
			
			CopyOnWriteArrayList<Mod> modules = new CopyOnWriteArrayList<Mod>(ModuleManager.INSTANCE.getModules());
			List<Mod> enabled = ModuleManager.INSTANCE.getEnabledModules();
		
			modules.sort(Comparator.comparingInt(m -> (int)customFont.getStringWidth(((Mod)m).getDisplayName(), false)).reversed());
			
			for (Mod mod : modules) {
				int fWidth = (int) customFont.getStringWidth(mod.getDisplayName(), false);
				int fHeight = (int) customFont.getStringHeight(mod.getDisplayName(), false);
	            
//				mod.state = mod.isEnabled() ? Math.min(mod.state+0.5, mod.tstate) : Math.max(mod.state-0.5, mod.tstate);

//                matrices.push();
//                matrices.translate((sWidth-3-fWidth) * mod.state , ((fHeight)*(index))-5 , 0);
//                customFont.draw(matrices, mod.getDisplayName(), 0, 0, -1, false);
//                matrices.pop();
            	matrices.push();
        		matrices.scale((float) mod.state, 1, 1);
				DrawableHelper.fill(matrices, 0, index*(fHeight), fWidth+2, fHeight  + index*(fHeight), 0x99000000);
				DrawableHelper.fill(matrices, fWidth+4, index*(fHeight), fWidth+2, fHeight  + index*(fHeight), ColorUtils.rainbow(10, 0.8f, 1, 400*index));
				customFont.draw(matrices, mod.getDisplayName(), 0, ((fHeight)*(index))-5, ColorUtils.rainbow(10, 0.8f, 1, 400*index), false);
             	matrices.pop();
                
				if (mod.isEnabled()) {
                	index++;
                	mod.state = Math.min(mod.state+0.08, mod.tstate);
//					int offset = index*(fHeight);
//					DrawableHelper.fill(matrices, sWidth - fWidth-5, index*(fHeight), sWidth, fHeight  + index*(fHeight), 0x99000000);
//					customFont.draw(matrices, mod.getDisplayName(), sWidth-3-fWidth, ((fHeight)*(index))-5, new Color(230,0,0).getRGB(), false);
				} else if (!mod.isEnabled()){
					mod.state = Math.max(mod.state-0.08, mod.tstate);
				}
			}

//		}
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
