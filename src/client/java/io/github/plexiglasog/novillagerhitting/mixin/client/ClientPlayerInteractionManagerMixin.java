package io.github.plexiglasog.novillagerhitting.mixin.client;

import io.github.plexiglasog.novillagerhitting.NoVIllagerHittingClient;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.multiplayer.MultiPlayerGameMode;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin({MultiPlayerGameMode.class})
public class ClientPlayerInteractionManagerMixin {
    @Inject(
            at = {@At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/multiplayer/ClientPacketListener;send(Lnet/minecraft/network/protocol/Packet;)V",
                    ordinal = 0
            )},
            method = {"attack(Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/entity/Entity;)V"},
            cancellable = true
    )
    public void attackEntity(Player player, Entity entity, CallbackInfo info) {
        if(entity instanceof Villager && NoVIllagerHittingClient.modConfig.blockVillagerHits){
            info.cancel();
        }
    }
}