package net.ced7032.necromant.event;

import net.ced7032.necromant.Necromant;
import net.ced7032.necromant.playerSavedEntity.PlayerSavedEntity;
import net.ced7032.necromant.playerSavedEntity.PlayerSavedEntityProvider;
import net.ced7032.necromant.thirst.PlayerThirst;
import net.ced7032.necromant.thirst.PlayerThirstProvider;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Necromant.MOD_ID)
public class ModEvents {

    @SubscribeEvent
    public static void onAttachCapabilities(AttachCapabilitiesEvent<Entity> event) {
        //if (event.getObject() instanceof Player && !event.getObject().getCapability(PlayerThirstProvider.PLAYER_THIRST).isPresent()) {
        //    event.addCapability(new ResourceLocation(Necromant.MOD_ID, "properties"), new PlayerThirstProvider());
        //}
        if (event.getObject() instanceof Player && !event.getObject().getCapability(PlayerSavedEntityProvider.PLAYER_SAVED_ENTITY).isPresent()) {
            event.addCapability(new ResourceLocation(Necromant.MOD_ID, "properties2"), new PlayerSavedEntityProvider());
        }
    }

    @SubscribeEvent
    public static void onPlayerCloned(PlayerEvent.Clone event) {
        if(event.isWasDeath()) {
            event.getOriginal().getCapability(PlayerThirstProvider.PLAYER_THIRST).ifPresent(oldStore ->
                    event.getOriginal().getCapability(PlayerThirstProvider.PLAYER_THIRST).ifPresent(newStore ->
                            newStore.copyFrom(oldStore)));
        }
    }

    @SubscribeEvent
    public static void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
        event.register(PlayerThirst.class);
        event.register(PlayerSavedEntity.class);
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if(event.side == LogicalSide.SERVER) {
            event.player.getCapability(PlayerThirstProvider.PLAYER_THIRST).ifPresent(playerThirst -> {
                if(playerThirst.getThirst() > 0 && event.player.getRandom().nextFloat() < 0.005f) {
                    playerThirst.subThirst(1);
                    event.player.sendMessage(new TextComponent("subtracted thirst"), event.player.getUUID());
                }
            });
        }
    }
}
