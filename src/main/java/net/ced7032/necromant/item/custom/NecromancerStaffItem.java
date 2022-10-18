package net.ced7032.necromant.item.custom;

import net.ced7032.necromant.item.ModItems;
import net.ced7032.necromant.playerSavedEntity.PlayerSavedEntityProvider;
import net.ced7032.necromant.thirst.PlayerThirstProvider;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class NecromancerStaffItem extends Item {

    public NecromancerStaffItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public void releaseUsing(@NotNull ItemStack pStack, @NotNull Level pLevel, @NotNull LivingEntity pLivingEntity, int pTimeCharged) {
        if(pTimeCharged + 100 < getUseDuration(pStack)){
            /*pLivingEntity.getCapability(PlayerSavedEntityProvider.PLAYER_SAVED_ENTITY).ifPresent(entity -> {
                    if (entity.getEntity().isAlive()) {
                        pLivingEntity.getCapability(PlayerThirstProvider.PLAYER_THIRST).ifPresent(thirst -> {
                                    thirst.addThirst(entity.getEntity().getHealth() * 0.2f);
                                }
                        );
                        entity.getEntity().kill();
                        entity.setEntity(null);
                        System.out.println("below");
                    }

                    System.out.println("aelow");
                }
            );*/
        }

        super.releaseUsing(pStack, pLevel, pLivingEntity, pTimeCharged);
        System.out.println("cello");
    }

    @Override
    public InteractionResult interactLivingEntity(ItemStack pStack, Player pPlayer, LivingEntity pInteractionTarget, InteractionHand pUsedHand) {
        if (pInteractionTarget.getMobType() == MobType.UNDEAD) {
            //pPlayer.getCapability(PlayerSavedEntityProvider.PLAYER_SAVED_ENTITY).ifPresent(entity -> {
            //        entity.setEntity(pInteractionTarget);
            //    }
            //);
        }

        return super.interactLivingEntity(pStack, pPlayer, pInteractionTarget, pUsedHand);
    }

    public int getUseDuration(@NotNull ItemStack pStack) {
        return 720000;
    }

    public @NotNull UseAnim getUseAnimation(@NotNull ItemStack pStack) {
        return UseAnim.SPEAR;
    }

    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level pLevel, Player pPlayer, @NotNull InteractionHand pHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pHand);
        pPlayer.startUsingItem(pHand);
        return InteractionResultHolder.consume(itemstack);
    }

    @Override
    public boolean canPerformAction(ItemStack stack, net.minecraftforge.common.ToolAction toolAction) {
        return true;
    }
}
