package skiddedclient.module.combat;

import net.minecraft.item.Items;
import skiddedclient.module.Mod;
import skiddedclient.module.settings.BooleanSetting;
import skiddedclient.module.settings.NumberSetting;

public class AutoSoup extends Mod {

	NumberSetting health = new NumberSetting("Min health", 1, 20, 6, 1);
	BooleanSetting instantEat = new BooleanSetting("IntaEat", false);
	BooleanSetting hungry = new BooleanSetting("Hunger", true);
	BooleanSetting swap = new BooleanSetting("Swap", true);
	
	public AutoSoup() {
		super("AutoSoup", "Autoeat soup", Category.MOVEMENT);
		addSettings(health, instantEat, hungry, swap);
	}
	int cooldown = 42;
	@Override
	public void onTick() {
		if (this.isEnabled()) {
			if (hungry.isEnabled()) {
				if (mc.player.getHealth() < health.getValue() && mc.player.getHungerManager().isNotFull()) {
					if (mc.player.getMainHandStack().getItem() == Items.MUSHROOM_STEW) {
						
						mc.options.useKey.setPressed(true);
						if (instantEat.isEnabled()) mc.options.useKey.setPressed(false);
						else if (!instantEat.isEnabled() && cooldown<0) {
							mc.options.useKey.setPressed(false);
							cooldown=42;
						}
						
					} else if (mc.player.getMainHandStack().getItem() != Items.MUSHROOM_STEW && swap.isEnabled()) {
						int soupSlot = mc.player.getInventory().getSlotWithStack(Items.MUSHROOM_STEW.getDefaultStack());
						if (soupSlot != -1) mc.player.getInventory().selectedSlot = soupSlot;
					}
				}
			} else if (!hungry.isEnabled()) {
				if (mc.player.getHealth() < health.getValue()) {
					if (mc.player.getMainHandStack().getItem() == Items.MUSHROOM_STEW) {
						
						mc.options.useKey.setPressed(true);
						if (instantEat.isEnabled()) mc.options.useKey.setPressed(false);
						else if (!instantEat.isEnabled() && cooldown<0) {
							mc.options.useKey.setPressed(false);
							cooldown=42;
						}
						
					} else if (mc.player.getMainHandStack().getItem() != Items.MUSHROOM_STEW && swap.isEnabled()) {
						int soupSlot = mc.player.getInventory().getSlotWithStack(Items.MUSHROOM_STEW.getDefaultStack());
						if (soupSlot != -1) mc.player.getInventory().selectedSlot = soupSlot;
					}
				}
			}

		}
		cooldown--;
		super.onTick();
	}
}
