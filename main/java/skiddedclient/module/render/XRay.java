package skiddedclient.module.render;
//
//
//
//
//
//			Taken from DF9 Client
//
//
//
//
//
import java.util.ArrayList;


import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.OreBlock;
import net.minecraft.block.RedstoneOreBlock;
import net.minecraft.util.registry.Registry;
import skiddedclient.module.Mod;

public class XRay extends Mod {

	public boolean enabledXray;
	public static ArrayList<Block> blocks = new ArrayList<>();
    
    public XRay() {
        super("Xray", "Only render ore blocks", Category.RENDER);
		Registry.BLOCK.forEach(block -> {
            if (isGoodBlock(block)) blocks.add(block);
        });
    }
    
    boolean isGoodBlock(Block block) {
        boolean c1 = block == Blocks.LAVA || block == Blocks.CHEST || block == Blocks.FURNACE || block == Blocks.END_GATEWAY || block == Blocks.COMMAND_BLOCK || block == Blocks.ANCIENT_DEBRIS || block == Blocks.NETHER_PORTAL;
        boolean c2 = block instanceof OreBlock || block instanceof RedstoneOreBlock;
        return c1 || c2;
    }
    
    @Override
    public void onEnable() {
		enabledXray = true;
		mc.options.getGamma().setValue(10d);
        mc.worldRenderer.reload();
        super.onEnable();
    }

    @Override
    public void onDisable() {
		enabledXray = true;
		mc.options.getGamma().setValue(1d);
        mc.worldRenderer.reload();
        super.onDisable();
    }
	
}