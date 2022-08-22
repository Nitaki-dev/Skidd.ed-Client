package skiddedclient.utils.mixin;

import net.minecraft.client.network.SequencedPacketCreator;
import net.minecraft.client.world.ClientWorld;

public interface IClientPlayerInteractionManager {
    void setBlockBreakProgress(float var1);

    void setBlockBreakingCooldown(int var1);
    
    void _sendSequencedPacket(ClientWorld world, SequencedPacketCreator packetCreator);

    float getBlockBreakProgress();
    
    void syncSelected();
}