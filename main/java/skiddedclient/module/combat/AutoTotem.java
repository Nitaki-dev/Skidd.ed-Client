package skiddedclient.module.combat;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.AirBlock;
import net.minecraft.entity.Entity;
import net.minecraft.item.Items;
import net.minecraft.screen.slot.SlotActionType;
import net.minecraft.util.math.BlockPos;
import skiddedclient.module.Mod;
import skiddedclient.module.settings.BooleanSetting;
import skiddedclient.module.settings.ModeSetting;
import skiddedclient.module.settings.NumberSetting;


public class AutoTotem extends Mod{

	List<Entity> entities;
	ModeSetting item = new ModeSetting("Item", "Gapple", "Gapple", "Totem", "Crystal", "Shield", "Nether Sword");
	BooleanSetting safetyCheck = new BooleanSetting("SafteyMode", true);
	NumberSetting health = new NumberSetting("Health", 3, 20, 10, 1);
	NumberSetting offset = new NumberSetting("Offset", 1, 5, 3, 1);
	
	public AutoTotem() {
		super("OffHand", "Puts the shit in your offhand", Category.COMBAT);
		addSettings(item,safetyCheck,health);
	}

	@Override
	public void onTick() {
		if(mc.world == null || mc.player == null || mc.getNetworkHandler() == null || mc.getBufferBuilders() == null) {
			return;
		}
	        int i;
	        Boolean found = false;
	        if(item.is("Totem")) {
	        if (!mc.player.getOffHandStack().getItem().equals(Items.TOTEM_OF_UNDYING)) {
	            for (i = 9; i <= 36; i++) {
	                if (mc.player.getInventory().getStack(i).getItem().equals(Items.TOTEM_OF_UNDYING)) {
	                    found = true;
	                    break;
	                }
	            }
	            if (!(mc.player.getOffHandStack().getItem().equals(Items.TOTEM_OF_UNDYING)) && found) {
	                mc.interactionManager.clickSlot(mc.player.currentScreenHandler.syncId, i, 0, SlotActionType.PICKUP, mc.player);
	                mc.interactionManager.clickSlot(mc.player.currentScreenHandler.syncId, 45, 0, SlotActionType.PICKUP, mc.player);
	            }
	        }
	        }else if (item.is("Gapple")) {
	        	if (!mc.player.getOffHandStack().getItem().equals(Items.GOLDEN_APPLE)) {
		            for (i = 9; i <= 36; i++) {
		                if (mc.player.getInventory().getStack(i).getItem().equals(Items.GOLDEN_APPLE)) {
		                    found = true;
		                    break;
		                }
		            }
		            if (!(mc.player.getOffHandStack().getItem().equals(Items.GOLDEN_APPLE)) && found) {
		                mc.interactionManager.clickSlot(mc.player.currentScreenHandler.syncId, i, 0, SlotActionType.PICKUP, mc.player);
		                mc.interactionManager.clickSlot(mc.player.currentScreenHandler.syncId, 45, 0, SlotActionType.PICKUP, mc.player);
		            }
		        }
	        }
	        else if (item.is("Crystal")) {
	        	if (!mc.player.getOffHandStack().getItem().equals(Items.END_CRYSTAL)) {
		            for (i = 9; i <= 36; i++) {
		                if (mc.player.getInventory().getStack(i).getItem().equals(Items.END_CRYSTAL)) {
		                    found = true;
		                    break;
		                }
		            }
		            if (!(mc.player.getOffHandStack().getItem().equals(Items.END_CRYSTAL)) && found) {
		                mc.interactionManager.clickSlot(mc.player.currentScreenHandler.syncId, i, 0, SlotActionType.PICKUP, mc.player);
		                mc.interactionManager.clickSlot(mc.player.currentScreenHandler.syncId, 45, 0, SlotActionType.PICKUP, mc.player);
		            }
		        }
	        }
	        else if (item.is("Shield")) {
	        	if (!mc.player.getOffHandStack().getItem().equals(Items.SHIELD)) {
		            for (i = 9; i <= 36; i++) {
		                if (mc.player.getInventory().getStack(i).getItem().equals(Items.SHIELD)) {
		                    found = true;
		                    break;
		                }
		            }
		            if (!(mc.player.getOffHandStack().getItem().equals(Items.SHIELD)) && found) {
		                mc.interactionManager.clickSlot(mc.player.currentScreenHandler.syncId, i, 0, SlotActionType.PICKUP, mc.player);
		                mc.interactionManager.clickSlot(mc.player.currentScreenHandler.syncId, 45, 0, SlotActionType.PICKUP, mc.player);
		            }
		        }
	        }
	        else if (item.is("Nether Sword")) {
	        	if (!mc.player.getOffHandStack().getItem().equals(Items.NETHERITE_SWORD)) {
		            for (i = 9; i <= 36; i++) {
		                if (mc.player.getInventory().getStack(i).getItem().equals(Items.NETHERITE_SWORD)) {
		                    found = true;
		                    break;
		                }
		            }
		            if (!(mc.player.getOffHandStack().getItem().equals(Items.NETHERITE_SWORD)) && found) {
		                mc.interactionManager.clickSlot(mc.player.currentScreenHandler.syncId, i, 0, SlotActionType.PICKUP, mc.player);
		                mc.interactionManager.clickSlot(mc.player.currentScreenHandler.syncId, 45, 0, SlotActionType.PICKUP, mc.player);
		            }
		        }
	        }
	        BlockPos bp = new BlockPos(mc.player.getBlockPos().down().getX(), mc.player.getBlockPos().getY() - offset.getValue(), mc.player.getBlockPos().down().getZ());
	        if (safetyCheck.isEnabled()) {
            if (mc.world.getBlockState(bp).getBlock() instanceof AirBlock) {
            	 if (!mc.player.getOffHandStack().getItem().equals(Items.TOTEM_OF_UNDYING)) {
 	 	            for (i = 9; i <= 36; i++) {
 	 	                if (mc.player.getInventory().getStack(i).getItem().equals(Items.TOTEM_OF_UNDYING)) {
 	 	                    found = true;
 	 	                    break;
 	 	                }
 	 	            }
 	 	            if (!(mc.player.getOffHandStack().getItem().equals(Items.TOTEM_OF_UNDYING)) && found) {
 	 	                mc.interactionManager.clickSlot(mc.player.currentScreenHandler.syncId, i, 0, SlotActionType.PICKUP, mc.player);
 	 	                mc.interactionManager.clickSlot(mc.player.currentScreenHandler.syncId, 45, 0, SlotActionType.PICKUP, mc.player);
 	 	            }
 	 	        }
	        }
	        }else {
	        	if (!mc.player.getOffHandStack().getItem().equals(Items.END_CRYSTAL)) {
		            for (i = 9; i <= 36; i++) {
		                if (mc.player.getInventory().getStack(i).getItem().equals(Items.END_CRYSTAL)) {
		                    found = true;
		                    break;
		                }
		            }
		            if (!(mc.player.getOffHandStack().getItem().equals(Items.END_CRYSTAL)) && found) {
		                mc.interactionManager.clickSlot(mc.player.currentScreenHandler.syncId, i, 0, SlotActionType.PICKUP, mc.player);
		                mc.interactionManager.clickSlot(mc.player.currentScreenHandler.syncId, 45, 0, SlotActionType.PICKUP, mc.player);
		            }
		        }
	        }
	        if(mc.player.getHealth() == health.getValue() != item.is("Totem")) {
	        	 if (!mc.player.getOffHandStack().getItem().equals(Items.TOTEM_OF_UNDYING)) {
	 	            for (i = 9; i <= 36; i++) {
	 	                if (mc.player.getInventory().getStack(i).getItem().equals(Items.TOTEM_OF_UNDYING)) {
	 	                    found = true;
	 	                    break;
	 	                }
	 	            }
	 	            if (!(mc.player.getOffHandStack().getItem().equals(Items.TOTEM_OF_UNDYING)) && found) {
	 	                mc.interactionManager.clickSlot(mc.player.currentScreenHandler.syncId, i, 0, SlotActionType.PICKUP, mc.player);
	 	                mc.interactionManager.clickSlot(mc.player.currentScreenHandler.syncId, 45, 0, SlotActionType.PICKUP, mc.player);
	 	            }
	 	        }
	        }
	        
	        entities = new ArrayList<>();
	        mc.world.getEntities().forEach(entities::add);

	        if (mc.player.getHealth() <= health.getValue()) {
	        	 if (!mc.player.getOffHandStack().getItem().equals(Items.TOTEM_OF_UNDYING)) {
		 	            for (i = 9; i <= 36; i++) {
		 	                if (mc.player.getInventory().getStack(i).getItem().equals(Items.TOTEM_OF_UNDYING)) {
		 	                    found = true;
		 	                    break;
		 	                }
		 	            }
		 	            if (!(mc.player.getOffHandStack().getItem().equals(Items.TOTEM_OF_UNDYING)) && found) {
		 	                mc.interactionManager.clickSlot(mc.player.currentScreenHandler.syncId, i, 0, SlotActionType.PICKUP, mc.player);
		 	                mc.interactionManager.clickSlot(mc.player.currentScreenHandler.syncId, 45, 0, SlotActionType.PICKUP, mc.player);
		 	            }
		 	        }
	        } 
		super.onTick();
	}
}