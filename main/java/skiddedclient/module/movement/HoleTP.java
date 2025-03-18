package skiddedclient.module.movement;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.block.Blocks;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.BlockPos;
import skiddedclient.module.Mod;
import skiddedclient.module.settings.BooleanSetting;
import skiddedclient.module.settings.NumberSetting;

public class HoleTP extends Mod {
	
	public BooleanSetting bedrock = new BooleanSetting("Bedrock", true);
	public BooleanSetting mixed = new BooleanSetting("Mixed", true);
	public BooleanSetting obi = new BooleanSetting("Obsidian", true);
	public NumberSetting range = new NumberSetting("Range", 2, 6, 4, 1);
	private Map<BlockPos, float[]> holes = new HashMap<>();
	
	float[] bedrockColor = new float[] {30f, 235f, 30f};
	float[] obsidianColor = new float[] {30f, 235f, 235f};
	float[] mixedColor  = new float[] {127f, 0f, 127f};
	
	//i fucking hate float[] colors never again
	//i wasted 3 hours finding those colors
	//HOW TF DID I NOT REALISE THIS WAS FKIN CMYK 
	
// 255,0,0 = Cyan
// 0,255,0 = Pink
// 0,0,255 = Yellow
	
	public HoleTP() {
		super("HoleTP", "Render a box on safe holes", Category.COMBAT);
		addSettings(bedrock, mixed, obi, range);
	}
	
	@Override
	public void onTick() {
		if (mc.player.age % 14 == 0) {
			holes.clear();

			int dist = 10;
			
			for (BlockPos pos : BlockPos.iterateOutwards(mc.player.getBlockPos(), dist, dist, dist)) {
				if (!mc.world.isInBuildLimit(pos.down())
						|| (mc.world.getBlockState(pos.down()).getBlock() != Blocks.BEDROCK
						&& mc.world.getBlockState(pos.down()).getBlock() != Blocks.OBSIDIAN)
						|| !mc.world.getBlockState(pos).getCollisionShape(mc.world, pos).isEmpty()
						|| !mc.world.getBlockState(pos.up(1)).getCollisionShape(mc.world, pos.up(1)).isEmpty()
						|| !mc.world.getBlockState(pos.up(2)).getCollisionShape(mc.world, pos.up(2)).isEmpty()) {
					continue;
				}
				if (mc.player.getBoundingBox().getCenter().x > pos.getX() + 0.1
				&& mc.player.getBoundingBox().getCenter().x < pos.getX() + 0.9
				&& mc.player.getBoundingBox().getCenter().z > pos.getZ() + 0.1
				&& mc.player.getBoundingBox().getCenter().z < pos.getZ() + 0.9){
					
				}
				
				int bedrockCounter = 0;
				int obsidianCounter = 0;
				for (BlockPos pos1 : neighbours(pos)) {
					if (mc.world.getBlockState(pos1).getBlock() == Blocks.BEDROCK) {
						bedrockCounter++;
					} else if (mc.world.getBlockState(pos1).getBlock() == Blocks.OBSIDIAN) {
						obsidianCounter++;
					} else {
						break;
					}
				}

				if (bedrockCounter == 5 && bedrock.isEnabled()) {
					holes.put(pos.toImmutable(), bedrockColor);
				} else if (obsidianCounter == 5 && obi.isEnabled()) {
					holes.put(pos.toImmutable(), obsidianColor);
				} else if (bedrockCounter >= 1 && obsidianCounter >= 1
						&& bedrockCounter + obsidianCounter == 5 && mixed.isEnabled()) {
					holes.put(pos.toImmutable(), mixedColor);
				}
			}
		}
		super.onTick();
	}
	@Override
	public void onWorldRender(MatrixStack matrices) {
		holes.forEach((pos, color) -> {
			if (pos.getSquaredDistance(mc.player.getPos())<range.getValue()) {
				mc.player.updatePosition(pos.getX()+0.5, pos.getY(), pos.getZ()+0.5);
			}
		});
	}
	
	private BlockPos[] neighbours(BlockPos pos) {
		return new BlockPos[] {
				pos.west(), pos.east(), pos.south(), pos.north(), pos.down()
		};
	}
}