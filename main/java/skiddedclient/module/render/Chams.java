package skiddedclient.module.render;

import net.minecraft.entity.Entity;
import net.minecraft.entity.decoration.EndCrystalEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.StriderEntity;
import net.minecraft.entity.player.PlayerEntity;
import skiddedclient.module.Mod;
import skiddedclient.module.settings.BooleanSetting;

public class Chams extends Mod{

    public BooleanSetting players = new BooleanSetting("Players", true);
    public BooleanSetting crystals = new BooleanSetting("Crystals", true);
    public BooleanSetting passive = new BooleanSetting("Passive", true);
    public BooleanSetting hostile = new BooleanSetting("Hostile", true);

    public Chams() {
        super("Chams", "Basically better esp",Category.RENDER);
        addSettings(players,crystals,passive,hostile);
        get = this;
    }

     public boolean shouldRender(Entity entity) {
            if (!isEnabled()) return false;
            if (entity == null) return false;
            if (entity instanceof StriderEntity) return false;
            if (entity instanceof PassiveEntity) return passive.isEnabled();
            if (entity instanceof HostileEntity) return hostile.isEnabled();
            if (entity instanceof EndCrystalEntity) return crystals.isEnabled();
            if (entity instanceof PlayerEntity && entity != mc.player) return players.isEnabled();

            return false;
        }

        public static Chams get;

}