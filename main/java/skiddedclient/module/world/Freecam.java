package skiddedclient.module.world;

import net.minecraft.entity.Entity;
import skiddedclient.module.Mod;
import skiddedclient.utils.player.FakePlayerEntity2;


//xracer on top
public class Freecam extends Mod {
    public Freecam() {
        super("Freecam","2", Category.WORLD);
    }

    private Entity entity;



    public void onEnable(){
       if(mc.player == null)return;
      Entity fakeEntity = new FakePlayerEntity2(mc.world, mc.player.getGameProfile());
      fakeEntity.setPos(mc.player.getX(), mc.player.getY(), mc.player.getZ());
      mc.world.addEntity(69420, fakeEntity);
      mc.setCameraEntity(fakeEntity);
      entity = fakeEntity;
      super.onEnable();
    }

    public void onTick(){

     if(entity == null)return;

      entity.setYaw(mc.player.getYaw());
      entity.setPitch(mc.player.getPitch());
      entity.setHeadYaw(entity.getYaw());


     //    System.out.println("Yaw: "+entity.getYaw()+ " | Pitch: "+entity.getPitch());
            if(mc.options.backKey.isPressed()){
                back(entity);
            }
             if(mc.options.forwardKey.isPressed()){
                foward(entity);
             }
             if(mc.options.jumpKey.isPressed()){
                 up(entity);
             }
             if(mc.options.sneakKey.isPressed()){
                 down(entity);
             }
             if(mc.options.leftKey.isPressed()){
                 left(entity);
             }
             if(mc.options.rightKey.isPressed()){
                 right(entity);
             }



    }

    public void up(Entity entity){
        entity.setPos(entity.getX(), entity.getY()+1, entity.getZ());
    }

    public void down(Entity entity){
        entity.setPos(entity.getX(), entity.getY()-1, entity.getZ());
    }

    public void left(Entity entity){
        if(entity.getHorizontalFacing().getName() == "west"){
            entity.setPos(entity.getX(), entity.getY(), entity.getZ()+1);
        }
        if(entity.getHorizontalFacing().getName() == "east"){
            entity.setPos(entity.getX(), entity.getY(), entity.getZ()-1);
        }
        if(entity.getHorizontalFacing().getName() == "north"){
            entity.setPos(entity.getX()-1, entity.getY(), entity.getZ());
        }
        if(entity.getHorizontalFacing().getName() == "south"){
            entity.setPos(entity.getX()+1, entity.getY(), entity.getZ());
        }
    }

    public void right(Entity entity){
        if(entity.getHorizontalFacing().getName() == "west"){
            entity.setPos(entity.getX(), entity.getY(), entity.getZ()-1);
        }
        if(entity.getHorizontalFacing().getName() == "east"){
            entity.setPos(entity.getX(), entity.getY(), entity.getZ()+1);
        }
        if(entity.getHorizontalFacing().getName() == "north"){
            entity.setPos(entity.getX()+1, entity.getY(), entity.getZ());
        }
        if(entity.getHorizontalFacing().getName() == "south"){
            entity.setPos(entity.getX()-1, entity.getY(), entity.getZ());
        }
    }

    public void back(Entity entity){
           if(entity.getHorizontalFacing().getName() == "west"){
               entity.setPos(entity.getX()+1, entity.getY(), entity.getZ());
           }
           if(entity.getHorizontalFacing().getName() == "east"){
               entity.setPos(entity.getX()-1, entity.getY(), entity.getZ());
           }
           if(entity.getHorizontalFacing().getName() == "south"){
               entity.setPos(entity.getX(), entity.getY(), entity.getZ()-1);
           }
        if(entity.getHorizontalFacing().getName() == "north"){
            entity.setPos(entity.getX(), entity.getY(), entity.getZ()+1);
        }
    }

    public void foward(Entity entity){
        if(entity.getHorizontalFacing().getName() == "west"){
            entity.setPos(entity.getX()-1, entity.getY(), entity.getZ());
        }
        if(entity.getHorizontalFacing().getName() == "east"){
            entity.setPos(entity.getX()+1, entity.getY(), entity.getZ());
        }
        if(entity.getHorizontalFacing().getName() == "south"){
            entity.setPos(entity.getX(), entity.getY(), entity.getZ()+1);
        }
        if(entity.getHorizontalFacing().getName() == "north"){
            entity.setPos(entity.getX(), entity.getY(), entity.getZ()-1);
        }
    }



    public void onDisable(){
         if(mc.player == null)return;
         if(mc.gameRenderer == null)return;
          mc.setCameraEntity(mc.player);
          mc.world.removeEntity(69420, Entity.RemovalReason.DISCARDED);
          super.onDisable();
    }
}
