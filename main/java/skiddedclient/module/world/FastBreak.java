package skiddedclient.module.world;

import com.google.common.eventbus.Subscribe;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import skiddedclient.event.events.EventBlockBreakingCooldown;
//import xipe.event.events.EventBlockBreakingCooldown;
//import xipe.module.Mod;
//import xipe.module.settings.BooleanSetting;
//import xipe.module.settings.ModeSetting;
//import xipe.module.settings.NumberSetting;
import skiddedclient.module.Mod;
import skiddedclient.module.settings.BooleanSetting;
import skiddedclient.module.settings.ModeSetting;
import skiddedclient.module.settings.NumberSetting;

public class FastBreak extends Mod{

    BooleanSetting creative = new BooleanSetting("creative", true);
    NumberSetting delay = new NumberSetting("Delay", 0, 1, 0.2, 0.1);
    ModeSetting mode = new ModeSetting("Mode", "Haste", "Haste");

    public FastBreak() {
        super("FastBreak", "Breaks blocks faster", Category.WORLD);
        addSettings(creative,delay,mode);
    }

    private StatusEffectInstance haste;

    @Override
    public void onEnable() {
        haste = new StatusEffectInstance(StatusEffects.HASTE, Integer.MAX_VALUE, (int) 3 - 1);
        super.onEnable();
    }

    @Override
    public void onDisable() {
        if (mode.is("Haste")) {
            mc.player.removeStatusEffect(StatusEffects.HASTE);
        }
        super.onDisable();
    }

    @Override
    public void onTick() {
        if (mode.is("Haste")) mc.player.addStatusEffect(haste);
        else mc.player.removeStatusEffect(StatusEffects.HASTE);
        super.onTick();
    }

    @Subscribe
    public void onBlockBreakingCooldown(EventBlockBreakingCooldown event) {
        if (mc.player.isCreative() && !creative.isEnabled()) return;
        if (delay.getValue() != 1) event.setCooldown((int) (delay.getValue() * 5));
    }
}