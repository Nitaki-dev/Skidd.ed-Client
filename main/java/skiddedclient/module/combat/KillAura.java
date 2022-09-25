package skiddedclient.module.combat;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.google.common.collect.Lists;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import net.minecraft.util.Hand;
import skiddedclient.module.Mod;
import skiddedclient.module.settings.BooleanSetting;
import skiddedclient.module.settings.ModeSetting;
import skiddedclient.module.settings.NumberSetting;
import skiddedclient.utils.RotationUtils;

public class Killaura extends Mod {
    public static ArrayList<String> modes = new ArrayList<>();

    public static ModeSetting mode = new ModeSetting("Mode", "Camera", "Camera", "Packet");
    public static ModeSetting rotationmode = new ModeSetting("Rotation", "Silent", "Silent", "Legit");
    public static NumberSetting range = new NumberSetting("Range", 3, 6, 4, 0.1);
    public static BooleanSetting cooldown = new BooleanSetting("Cooldown", true);
    public static ModeSetting priority = new ModeSetting("Priority", "Random", "Random", "Random");

    
    public Killaura() {
        super("Killaura", "Automatically attacks living entities for you", Category.COMBAT);
        addSettings(mode, rotationmode, range, cooldown, priority);
    }

    public void onTick() {
        if (this.isEnabled()) {
            if (mc.world != null) {
                List<LivingEntity> targets = Lists.<LivingEntity>newArrayList();
                if (mc.world.getEntities() != null) {
                    for (Entity e : mc.world.getEntities()) {
                        if (e instanceof LivingEntity && e != mc.player && mc.player.distanceTo(e) <= range.getValue())
                            targets.add((LivingEntity) e);

                        else {
                            if (targets.contains(e)) targets.remove(e);
                        }
                        
                        if (priority.is("Distance")) targets.sort(Comparator.comparingDouble(entity -> mc.player.distanceTo(e)).reversed());
                        else if (priority.is("Health")) targets.sort(Comparator.comparingDouble(entity -> ((LivingEntity)entity).getHealth()).reversed());
                        
                        //todo Fix the priority thing
                        
                    }
                    if(targets.size() -1 >= 0) {
                        float pitch = RotationUtils.getRotations(targets.get(0))[1];
                        float yaw = RotationUtils.getRotations(targets.get(0))[0];
                        if(mode.getMode() == "Camera"){
                        	if (targets.get(0).isAlive()) {
                        		if (rotationmode.is("Legit")) {
                                    mc.player.setYaw(yaw);
                                    mc.player.setPitch(pitch);
                                    
                        		} else if (rotationmode.is("Silent")) {
                        			RotationUtils.setSilentPitch(pitch);
                        			RotationUtils.setSilentYaw(yaw);
                        		}
                                if (cooldown.isEnabled() ? mc.player.getAttackCooldownProgress(0.5F) == 1 : true) {
                                	
                                	double posX = mc.player.getX();
                            		double posY = mc.player.getY();
                               		double posZ = mc.player.getZ();
                        		    mc.player.networkHandler.sendPacket(new PlayerMoveC2SPacket.PositionAndOnGround(posX, posY + 0.0633, posZ, false));
                        		    mc.player.networkHandler.sendPacket(new PlayerMoveC2SPacket.PositionAndOnGround(posX, posY, posZ, false));
                        		    
//                                	if (targets.get(0) != null) mc.inGameHud.getChatHud().addMessage(Text.literal("[Skidd.ed] " + targets.get(0).getHealth() + ""));
                        		    
                                	mc.interactionManager.attackEntity(mc.player, targets.get(0));
                            		mc.player.swingHand(Hand.MAIN_HAND);
                            		resetRotation();
                                }
                        	}
                        }
                        if(mode.getMode() == "Packet"){
//                        	mc.inGameHud.getChatHud().addMessage(Text.literal("{[Skidd.ed] " + targets.get(0).getHealth() + "}"));
//                          mc.getNetworkHandler().sendPacket(new PlayerMoveC2SPacket.LookAndOnGround((float) yaw, (float) pitch, mc.player.isOnGround()));
                        }
                    }
                }
            }
        }
    }
    
    @Override
    public void onDisable() {
    	resetRotation();
    	super.onDisable();
    }
    public void resetRotation() {
    	RotationUtils.resetPitch();
    	RotationUtils.resetYaw();
    }
}