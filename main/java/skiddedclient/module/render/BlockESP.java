package skiddedclient.module.render;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Box;
import skiddedclient.module.Mod;
import skiddedclient.module.settings.NumberSetting;
import skiddedclient.utils.misc.QuadColor;
import skiddedclient.utils.render.RenderUtils;
import skiddedclient.utils.world.WorldUtils;

public class BlockESP extends Mod {
	
	public NumberSetting width;
	
	float[] color  = new float[] {127f, 0f, 127f};
	
	public BlockESP() {
		super("BlockESP", "Allows you to see storage shit better", Category.RENDER);
       this.width = new NumberSetting("Width", 0.0, 20.0, 2.0, 1.0);
	}
	
	  @Override
	  public void onWorldRender(MatrixStack matrices) {
	        for (BlockEntity block : WorldUtils.blockEntities()) {
	            if (block instanceof BarrelBlockEntity || 
	            		block instanceof BlastFurnaceBlockEntity || 
	            		block instanceof ChestBlockEntity || 
	            		block instanceof DispenserBlockEntity || 
	            		block instanceof DropperBlockEntity || 
	            		block instanceof EnderChestBlockEntity ||
	            		block instanceof FurnaceBlockEntity ||
	            		block instanceof HopperBlockEntity ||
	            		block instanceof ShulkerBoxBlockEntity ||
	            		block instanceof SmokerBlockEntity) {
	            	if (block instanceof ChestBlockEntity || block instanceof EnderChestBlockEntity) {
	            		RenderUtils.drawBoxFill(
		            			new Box(
		            					block.getPos().getX()+0.06, 
		            					block.getPos().getY(), 
		            					block.getPos().getZ()+0.06,
		            					block.getPos().getX()+0.94, 
		            					block.getPos().getY()+0.88, 
		            					block.getPos().getZ()+0.94),
		            			QuadColor.single(1f, 0f, 0f, 0.5f));
	            	}else if (block instanceof HopperBlockEntity) {
//	            		RenderUtils.drawBoxFill(
//		            			new Box(
//		            					block.getPos().getX()+0.25, 
//		            					block.getPos().getY()+0, 
//		            					block.getPos().getZ()+0.22,
//		            					block.getPos().getX()+0.75, 
//		            					block.getPos().getY()+0.625, 
//		            					block.getPos().getZ()+0.75),
//		            			QuadColor.single(1f, 0f, 0f, 0.5f));
	            		RenderUtils.drawBoxFill(
		            			new Box(
		            					block.getPos().getX()+0, 
		            					block.getPos().getY()+0.625, 
		            					block.getPos().getZ()+0,
		            					block.getPos().getX()+1, 
		            					block.getPos().getY()+1, 
		            					block.getPos().getZ()+1),
		            			QuadColor.single(1f, 0f, 0f, 0.5f));
	            		
	            	}else RenderUtils.drawBoxFill(
	            			new Box(
	            					block.getPos().getX(), 
	            					block.getPos().getY(), 
	            					block.getPos().getZ(),
	            					block.getPos().getX()+1, 
	            					block.getPos().getY()+1, 
	            					block.getPos().getZ()+1),
	            			QuadColor.single(1f, 0f, 0f, 0.5f));
	            }
	        }
	  }
}

