package skiddedclient.module.world;

import java.util.Objects;

import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec2f;
import net.minecraft.util.math.Vec3d;
import skiddedclient.module.Mod;
import skiddedclient.module.settings.ModeSetting;
import skiddedclient.module.settings.NumberSetting;
import skiddedclient.utils.RotationUtils;

public class Scaffold extends Mod {
	
	public ModeSetting mode = new ModeSetting("Mode", "Extend", "Extend");
	public NumberSetting extend = new NumberSetting("Extend",  0, 5, 3, 1);
	
    public Scaffold() {
        super("Scaffold", "fuck you bedlessnoobs", Category.WORLD);
        addSettings(extend);
    }

	@Override
	public void onTick() {
		if (mode.getMode().equalsIgnoreCase("Extend")) {
        	Vec3d ppos = Objects.requireNonNull(mc.player).getPos().add(0, -1, 0);
        	BlockPos bp = new BlockPos(ppos);
        	int selIndex = mc.player.getInventory().selectedSlot;
        	if (!(mc.player.getInventory().getStack(selIndex).getItem() instanceof BlockItem)) {
        		for (int i = 0; i < 9; i++) {
        			ItemStack is = mc.player.getInventory().getStack(i);
        			if (is.getItem() == Items.AIR) {
        				continue;
        			}
        			if (is.getItem() instanceof BlockItem) {
        				selIndex = i;
        				break;
        			}
        		}
        	}
        	if (mc.player.getInventory().getStack(selIndex).getItem() != Items.AIR) {
        		boolean rshift = mc.player.isSneaking();
        		if (rshift) {
        			bp = bp.down();
        		}
        		// fucking multithreading moment
        		int finalSelIndex = selIndex;
        		BlockPos finalBp = bp;
        		mc.execute(() -> placeBlockWithSlot(finalSelIndex, finalBp));
        		if (extend.getValue() != 0) {
        			Vec3d direction1 = mc.player.getVelocity().multiply(3);
        			Vec3d direction2 = new Vec3d(MathHelper.clamp(direction1.getX(), -1, 1), 0, MathHelper.clamp(direction1.getZ(), -1, 1));
        			Vec3d v = ppos;
        			for (double i = 0; i < extend.getValue(); i += 0.5) {
        				v = v.add(direction2);
        				if (v.distanceTo(mc.player.getPos()) >= Objects.requireNonNull(mc.interactionManager).getReachDistance()) {
        					break;
        				}
        				if (rshift) {
        					v = v.add(0, -1, 0);
        				}
        				BlockPos bp1 = new BlockPos(v);
        				mc.execute(() -> placeBlockWithSlot(finalSelIndex, bp1));
        			}
        			
        		}
        	}
		}
		super.onTick();
    }

    void placeBlockWithSlot(int s, BlockPos bp) {
        BlockState st = Objects.requireNonNull(mc.world).getBlockState(bp);
        if (!st.getMaterial().isReplaceable()) {
            return;
        }
        Vec2f py = RotationUtils.getPitchYaw(new Vec3d(bp.getX() + .5, bp.getY() + .5, bp.getZ() + .5));
        RotationUtils.setClientPitch(py.x);
        RotationUtils.setClientYaw(py.y);
        int c = Objects.requireNonNull(mc.player).getInventory().selectedSlot;
        mc.player.getInventory().selectedSlot = s;
        BlockHitResult bhr = new BlockHitResult(new Vec3d(bp.getX(), bp.getY(), bp.getZ()), Direction.DOWN, bp, false);

        Objects.requireNonNull(mc.interactionManager).interactBlock(mc.player, Hand.MAIN_HAND, bhr);
        mc.player.getInventory().selectedSlot = c;
    }
}