package skiddedclient.module.render;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.hit.HitResult.Type;
import net.minecraft.util.math.BlockPos;
import skiddedclient.module.Mod;
import skiddedclient.module.settings.NumberSetting;
import skiddedclient.utils.render.QuadColor;
import skiddedclient.utils.render.RenderUtil;

public class BlockOutline extends Mod{

	final List<BlockPos> renders;
    public NumberSetting width;
	
	public BlockOutline() {
		super("BlockOutline", "Outlines the block you're looking at", Category.RENDER);
		this.renders = new ArrayList<BlockPos>();
	        this.width = new NumberSetting("Width", 0.0, 20.0, 2.0, 1.0);
	        this.addSettings(this.width);
	}
	
	@Override
	public void onWorldRender(MatrixStack matrices)  {
		HitResult target = mc.crosshairTarget;
		if (target != null) {
			if (target.getType() == Type.BLOCK) {
				BlockPos pos = (BlockPos) ((BlockHitResult) target).getBlockPos();
				{
					RenderUtil.drawBoxOutline1(pos, QuadColor.single(0xffff0000), (int) width.getValue());
				}
			}
		}
	}

}
