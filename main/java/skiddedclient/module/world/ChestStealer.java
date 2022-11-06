package skiddedclient.module.world;

import net.minecraft.client.gui.screen.ingame.GenericContainerScreen;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.screen.slot.SlotActionType;
import skiddedclient.module.Mod;
import skiddedclient.module.settings.NumberSetting;
import skiddedclient.utils.player.InventoryUtils;
import skiddedclient.utils.misc.Timer;

public class ChestStealer extends Mod {

    public NumberSetting delay = new NumberSetting("Delay", 0, 500, 100, 10);

    private static Timer delayTimer = new Timer();

    public ChestStealer() {
        super("ChestStealer", "Steals items from chests", Category.WORLD);
        addSetting(delay);
    }

    @Override
    public void onTick() {
        if (mc.currentScreen instanceof GenericContainerScreen) {
        	if (InventoryUtils.isContainerEmpty(mc.player.currentScreenHandler)) mc.player.closeScreen();
            if (!InventoryUtils.isInventoryFull() && !InventoryUtils.isContainerEmpty(mc.player.currentScreenHandler)) {
                ScreenHandler handler = mc.player.currentScreenHandler;

                for (int i = 0; i < handler.slots.size() - InventoryUtils.MAIN_END; i++) {
                    Slot slot = handler.slots.get(i);
                    ItemStack stack = slot.getStack();
                    if (stack.getItem() != Items.AIR) {
                        if (delayTimer.hasTimeElapsed(delay.getValueInt(), true)) {
                            mc.interactionManager.clickSlot(handler.syncId, slot.id, 0, SlotActionType.QUICK_MOVE, mc.player);
                        }
                    }
                }
            } else {
                mc.player.closeHandledScreen();
            }
        }
        super.onTick();
    }
}